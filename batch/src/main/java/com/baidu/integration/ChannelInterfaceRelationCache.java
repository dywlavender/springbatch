package com.baidu.integration;

import org.springframework.beans.factory.annotation.Autowired;

public class ChannelInterfaceRelationCache extends BaseBizGuavaCache<String,String> {
    @Autowired
    private ChannelInterfaceRelationMapper channelInterfaceRelationMapper;

    /**
     * 当过期后获取值时调用
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    protected String getValueWhenExpired(String key) throws Exception {
        try{
            System.out.println("从数据库中获取接口信息-----_ key = "+key);
            ChannelInterfaceRelation channelInterfaceRelation = getBeanInfo(key);
            String transCode = channelInterfaceRelationMapper.selectChannelInterfaceInfo(channelInterfaceRelation);
            return transCode;
        }catch (Exception e){
            System.out.println("从数据库中获取信息异常 ，key = " + key);
            throw e;
        }
    }

    /**
     * 按照规则分割数据并整理数据 分割字符 ”-“
     */
    public ChannelInterfaceRelation getBeanInfo(String key){
        ChannelInterfaceRelation channelInterfaceRelation = new ChannelInterfaceRelation();
        if(key != null){
            String[] keys = key.split("-");
            channelInterfaceRelation.setChannelCode(keys[0]);
            channelInterfaceRelation.setChannelProductCode(keys[1]);
            channelInterfaceRelation.setInterfaceDefineCode(keys[2]);
        }
        return channelInterfaceRelation;
    }

    public String getValue(String interfaceDefineCode, String channelCode,String productCode){
        String key = channelCode + "-" + productCode + "-" + interfaceDefineCode;
        String interfaceNo = super.getValue(key);
        return interfaceNo;
    }
}
