package com.baidu.integration;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class HttpTransport {
    @Autowired
    private BizCollectionCache cache;
    public Map<String,Object> prepareCommField(FsgContext ctx){
        Map<String,Object> data = ctx.getDataMap();
        Map reqMap = (Map) ctx.getReqBody();
        String channelCode = (String) reqMap.get("channelCode");
        String productCode = (String) reqMap.get("productCode");
        String transCode = ctx.getTransCode();
    //    获取编号
        String interfaceNo = cache.getInterfaceNo(transCode,channelCode,transCode);
        InterfaceConfig interfaceConfig = cache.getInterfaceConfig(interfaceNo);
        if(interfaceConfig == null || interfaceConfig.getInterfaceOrganization() == null ||
                interfaceConfig.getServiceCode() == null || interfaceConfig.getServiceSense() == null){
            throw new RuntimeException("异常");
        }
        data.put("serviceCode",interfaceConfig.getServiceCode());
        data.put("serviceSense",interfaceConfig.getServiceSense());
        data.put("interfaceOrganization",interfaceConfig.getInterfaceOrganization());
        data.put("transcode",interfaceConfig.getTransCode());
        data.put("_ReqTimestamp",new Timestamp(new Date().getTime()));
        ctx.setDataMap(data);
        return data;
    }

    public Map<String,Object> submit(FsgContext ctx){
        Map<String,Object> reqData = prepareCommField(ctx);
        /**
         * 从缓存中获取接口信息
         */
        String organization = (String) reqData.get("interfaceOraganization");
        String interfaceNo = (String) reqData.get("transCode");

        return null;
    }

}
