package com.baidu;

import com.google.gson.Gson;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test(){

        Gson gson = new Gson();
        String jsonString = "{"
                + "'uri' : '<uri>',"
                + "'region' : '<region>',"
                + "'location_url' : '<location_url>',"
                + "'upload_id' : '<upload_id>',"
                + "'upload_type' : 'intelligent_ingestion'"
                + "}";
        Map<String,String> inputMap = new HashMap<>();
        inputMap = gson.fromJson(jsonString,inputMap.getClass());
//        System.out.println(inputMap.getClass());
        System.out.println(inputMap.get("uri"));

    }

}
