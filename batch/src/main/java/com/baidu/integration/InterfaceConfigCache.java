package com.baidu.integration;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName InterfaceConfigCache 从缓存中获取接口信息
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/10 19:58
 * @Version 1.0
 **/
public class InterfaceConfigCache extends BaseBizGuavaCache<String ,InterfaceConfig>{
    @Autowired
    private InterfaceConfigMapper interfaceConfigMapper;

    @Override
    protected InterfaceConfig getValueWhenExpired(String key) throws Exception {
        try{
            InterfaceConfig interfaceConfig = interfaceConfigMapper.selectInterfaceConfigInfo(key);
            if(interfaceConfig != null && "N".equals(interfaceConfig.getStatus())){
                this.removeValue(key);
                System.out.println("移除该接口缓存------> "+ key);
                throw new Exception("移除该接口缓存");
            }
            return interfaceConfig;
        }catch (Exception e){
            throw e;
        }
    }
}
