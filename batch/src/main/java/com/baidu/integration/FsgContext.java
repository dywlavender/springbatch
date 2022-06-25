package com.baidu.integration;

import java.util.HashMap;
import java.util.Map;

public class FsgContext {
    private Map<String,Object> data = new HashMap<>();
    public Map<String,Object> getDataMap(){return new HashMap<>(this.data);}
    public Object getData(String key){return this.data.get(key);}
    public Object getReqHeader(){return getData("_ReqHeader");}
    public Object getReqBody(){return getData("_ReqBody");}

    public String getTransCode() {
        return getString("transCode");
    }

    private String getString(String key) {
        Object value = this.data.get(key);
        if(value == null){
            return null;
        }else{
            return value instanceof String ? (String)value:value.toString();
        }
    }
    public void setDataMap(Map<String,Object> map){
        if(map != null){
            this.data.putAll(map);
        }
    }
}
