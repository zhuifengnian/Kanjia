package com.kanjia.utils;

import java.io.*;
import java.util.Map;

/**
 * @description 与文件有关的工具类
 * @author fan
 * @create 2018/4/22 1:21
 */
public class FileUtil {
    //用于记录需要被替换的后缀
    //改变文件后缀
    public static boolean replaceSuffix(String path, Map<String, String> suffixMap){
        File dir = new File(path);
        if(!dir.isDirectory()){
            throw new RuntimeException("该path不是一个目录");
        }
        _replaceSuffix(dir, suffixMap);
        System.out.println("修改成功");
        return true;
    }

    private static void _replaceSuffix(File dir, Map<String, String> suffixMap){
        File[] files = dir.listFiles();
        for(File file: files){
            String filePath = file.getPath();
            for (Map.Entry<String, String> entry : suffixMap.entrySet()) {
                if (filePath.endsWith(entry.getKey())) {

                    String newFileName = filePath.replace(entry.getKey(), entry.getValue());
//                    file.renameTo(new File(newFileName));
                    File newFile = new File(newFileName);
                    try {
                        newFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
//                        String preEncoding = isr.getEncoding();
//                        System.out.println("之前的编码" + preEncoding);
//                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
//                        String encoding = outputStreamWriter.getEncoding();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
                    break;
                }
            }
            if(file.isDirectory()){
                _replaceSuffix(file, suffixMap);
            }
        }
    }

}