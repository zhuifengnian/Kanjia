package com.kanjia.utils;

import com.kanjia.pojo.ActivityDescription;
import com.kanjia.service.ActivityDescriptionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class JsonUtil {

    public static List<ActivityDescription> checkUserIdJson(String json) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        Object id = jsonArray.getJSONObject(0).get("aid");
        Object result = jsonArray.getJSONObject(0).get("detail");

        JSONArray jsonArrays = JSONArray.fromObject(result);
        List<ActivityDescription> activityDescriptionList =new ArrayList<>();

        for(int i=0;i<jsonArrays.size();i++) {
            Object title = jsonArrays.getJSONObject(i).get("title");
            Object text = jsonArrays.getJSONObject(i).get("content");
            ActivityDescription activityDescription=new ActivityDescription();
            activityDescription.setContent(String.valueOf(text));
            activityDescription.setTitle(String.valueOf(title));
            activityDescription.setActivityId(Integer.valueOf(String.valueOf(id)));
            activityDescriptionList.add(activityDescription);

        }
        return activityDescriptionList;
    }


}
