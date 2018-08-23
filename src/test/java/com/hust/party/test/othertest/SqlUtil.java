package com.hust.party.test.othertest;

import com.kanjia.dto.PintuanInfoDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sql工具，可直接根据bean对象生成sql语句<br/>
 * fan 2018/8/23 15:08
 */
public class SqlUtil {
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
          
        ArrayList<String> tableNames = new ArrayList<>();
        tableNames.add("pintuan_user_order");
        tableNames.add("user_order");
        tableNames.add("user");
        tableNames.add("activity");
        tableNames.add("enterprise");
        ArrayList<String> connectConditions = new ArrayList<>();
        connectConditions.add("puo.order_id = uo.id");
        connectConditions.add("uo.user_id = u.id");
        connectConditions.add("a.id = uo.activity_id");
        connectConditions.add("e.id = a.enterprise_id");
//        System.out.println(genSelectAllSql(PintuanInfoDto.class, tableNames, connectConditions, 2));
//        System.out.println(genSelectAllSql(PintuanInfoDto.class, "pintuan_user_order"));
        System.out.println(genSelectAllSql(PintuanInfoDto.class, tableNames, connectConditions));
    }

    /**
     * 生成查询语句，适用于没有多表连接的情况
     */
    public static String genSelectAllSql(Class clazz, String tableName){
        return genSelectAllSql(clazz, tableName, 2);
    }

    /**
     * 生成查询语句,参数说明见最长的
     */
    public static String genSelectAllSql(Class clazz, String tableName, int flag){
        ArrayList<String> tableNames = new ArrayList<>();
        tableNames.add(tableName);
        return genSelectAllSql(clazz, tableNames, new ArrayList<>(), null, flag);
    }

    /**
     * 生成查询语句，默认flag为2
     */
    public static String genSelectAllSql(Class clazz, List<String> tableNames,List<String> connectCondition){
        return genSelectAllSql(clazz, tableNames, connectCondition, null, 2);
    }

    public static String genSelectAllSql(Class clazz, List<String> tableNames,List<String> connectCondition, int flag){
        return genSelectAllSql(clazz, tableNames, connectCondition, null, flag);
    }

    /**
     * 生成查询语句
     * @param clazz 需要生成sql的bean类
     * @param tableNames 表名的集合如 pintuan_user_order,user_order
     * @param connectCondition 连接条件的集合，如activity.id = order.activity_id，支持缩写，目前只支持and连接
     * @param flag 生成样式标记。1为驼峰式，2为下划线式，3为下划线加驼峰式
     */
    public static String genSelectAllSql(Class clazz, List<String> tableNames,List<String> connectCondition,
                                         List<String> whereConditions,  int flag){
        //获取需要连接的表的个数，并判断连接的条件个数是否符合(表的个数-1)
        if((tableNames.size() - 1) != connectCondition.size()){
            throw new RuntimeException("表的个数与连接字段的个数不符合规律");
        }
        String filesList =  getBeanFilesList(clazz, flag);
        StringBuilder sql = new StringBuilder("select " + filesList + "\nfrom ");
        for (int i = 0; i < tableNames.size(); i++) {
            if(i == 0){
                String tableName = tableNames.get(i);
                String shrinkWord = genShrinkWord(tableName);
                sql.append(tableName + shrinkWord + "\n");
            }
            if(i + 1 < tableNames.size()){
                String nextTable = tableNames.get(i + 1);
                String nextShrinkWord = genShrinkWord(nextTable);
                sql.append(" inner join " + nextTable + nextShrinkWord);
                sql.append(" on " + connectCondition.get(i) + "\n");
            }
        }
        sql.append("where 1 = 1");
        if(whereConditions != null){
            for (String whereCondition: whereConditions){
                sql.append(" and " + whereCondition + "\n");
            }
        }
        return sql.toString();
    }

    /**
     * 根据指定bean生成对应的field串，为集合或者数组的会被排除
     * @param flag 为0时，只生成bean串，如activityType；为1时，只生成sql field串，如activity_type；为2时，两者都生成，并用as连接
     *             如activity_type as activityType
     */
    private static String getBeanFilesList(Class clazz, int flag){
        Field[] strs = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            String protype = strs[i].getType().toString();
            String fieldName = strs[i].getName();
            //为集合或者数组的需要排除
            if (protype.equals("Array") || protype.equals("List")) {
                continue;
            }
            if(flag == 0){
                sb.append(fieldName +",");
            }else if(flag == 1){
                StringBuffer sb2 = uppercaseToBlankLowercase(fieldName);
                sb.append(sb2.toString() + ", ");
            }else if(flag == 2){
                StringBuffer sb2 = uppercaseToBlankLowercase(fieldName);
                String afterName = sb2.toString();
                sb.append((fieldName.equals(afterName) ? fieldName : afterName + " as " + fieldName) + ", ");
            }
            if(i % 5 == 4){
                sb.append("\n");
            }
        }
        sb.deleteCharAt(sb.toString().lastIndexOf(","));
        return sb.toString();
    }

    /**
     * 将首字母大写的单词转成下划线连接的形式
     * @param fieldName
     * @return
     */
    private static StringBuffer uppercaseToBlankLowercase(String fieldName) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(fieldName);
        StringBuffer sb2 = new StringBuffer();
        while (matcher.find()){
            //将查找到的大写替换为_小写
            matcher.appendReplacement(sb2, "_" + matcher.group().toLowerCase());
        }
        matcher.appendTail(sb2);
        return sb2;
    }

    /**
     * 生成表名缩写，如user_order 生成 uo
     */
    private static String genShrinkWord(String tableName) {
        String[] split = tableName.split("_");
        StringBuilder sb = new StringBuilder(" ");
        Arrays.stream(split).forEach((str)->sb.append(str.charAt(0)));
        return sb.toString();
    }

    /** 
     * 生成插入语句 
     * @param bean 
     * @return 
     */  
//    public static String genInsertSql(String bean){
//        String filesList =  getBeanFilesList(bean);
//        int fl = DataUtil.getCountSonStr(filesList,",")+1;
//        String wenhao = "";
//        for (int i = 0; i < fl; i++) {
//            if(i==fl-1){
//                wenhao = wenhao+"?";
//            }else{
//                wenhao = wenhao+"?,";
//            }
//        }
//        return "insert into wnk_pdt_"+getBeanName(bean)+"("+filesList+") values("+wenhao+")";
//    }

    /**
     * 生成建表語句
     * @return
     */
    public static String genCreateTableSql(Class clazz){
        List<String> beanPropertyList =  getBeanPropertyList(clazz);
        StringBuffer sb = new StringBuffer("create table "+getBeanName(clazz)+"(\n");
        for (String string : beanPropertyList) {
            String[] propertys = string.split("`");
            if (!propertys[1].equals("tableName")&&!propertys[1].equals("param")&&!propertys[0].equals("List")) {
                if (propertys[1].equals("id")) {
                    sb.append("   id bigint primary key auto_increment,\n");
                } else {
                    if (propertys[0].equals("int")) {
                        sb.append("   " + propertys[1] + " int default 0 comment '',\n");
                    } else if (propertys[0].equals("String")) {
                        sb.append("   " + propertys[1] + " varchar(2000) default '' comment '',\n");
                    } else if (propertys[0].equals("double")) {
                        sb.append("   " + propertys[1] + " double(10,2) default 0.0 comment '',\n");
                    } else if (propertys[0].equals("Date")) {
                        sb.append("   " + propertys[1] + " datetime comment '',\n");
                    }
                }
            }
        }
        sb.append(")");
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    /**
     * 得到类名，并将其转化为表名，驼峰将转成下划线形式，如UserOrder将转成user_order
     * @param clazz
     * @return
     */
    public static String getBeanName(Class clazz){
        String clzStr = clazz.toString();
        //得到类名
        String beanName = clzStr.substring(clzStr.lastIndexOf(".")+1).toLowerCase();
        return beanName;
    }

    /**
     * 拿到field串
     */
    public static List<String> getBeanPropertyList(Class clazz){
        Field[] strs = clazz.getDeclaredFields();
        List<String> propertyList = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++) {
            String protype = strs[i].getType().toString();
            propertyList.add(protype.substring(protype.lastIndexOf(".")+1)+"`"+strs[i].getName());
        }
        return propertyList;
    }
}  