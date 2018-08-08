package com.kanjia.utils;

import com.kanjia.pojo.ActivityDescription;
import com.kanjia.service.ActivityDescriptionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;


public class JsonUtil {
    @Autowired
    private static ActivityDescriptionService activityDescriptionService;
    public static int[] checkUserIdJson(String json) {
        json = "[" + json + "]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        Object result = jsonArray.getJSONObject(0).get("detail");
        Object person = "[" + result + "]";
        JSONArray activityid = JSONArray.fromObject(person);
        Object id = activityid.getJSONObject(0).get("activityId");
        JSONArray jsonArrays = JSONArray.fromObject(person);
        int []insert=new int[jsonArrays.size()];

        for(int i=0;i<jsonArrays.size();i++) {
            Object title = jsonArrays.getJSONObject(i).get("title");
            Object text = jsonArrays.getJSONObject(i).get("text");
            ActivityDescription activityDescription=new ActivityDescription();
            activityDescription.setContent(String.valueOf(text));
            activityDescription.setTitle(String.valueOf(title));
            activityDescription.setActivityId(Integer.valueOf(String.valueOf(id)));
            insert[i]=activityDescriptionService.insertSelective(activityDescription);

        }
        return insert;
    }


}
