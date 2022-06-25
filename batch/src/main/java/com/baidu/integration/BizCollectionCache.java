package com.baidu.integration;

import org.springframework.beans.factory.annotation.Autowired;

//缓存类
public class BizCollectionCache {
    @Autowired
    private ChannelInterfaceRelationCache channelInterfaceRelationCache;
    private InterfaceConfigCache interfaceConfigCache;
    public String getInterfaceNo(String InterfaceDefineCode, String channelCode, String productCode){
        return channelInterfaceRelationCache.getValue(InterfaceDefineCode,channelCode,productCode);
    }
    public InterfaceConfig getInterfaceConfig(String key){
        return interfaceConfigCache.getValue(key);
    }
}
