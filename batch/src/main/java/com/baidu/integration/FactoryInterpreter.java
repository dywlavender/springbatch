package com.baidu.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName FactoryInterpreter
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/13 16:38
 * @Version 1.0
 **/
@Component
public class FactoryInterpreter {
    @Value("xml/")
    private String path;

    public static final String classMapping = "config/tagmapping.properties";
    public static final String aliasMapping = "config/aliasmapping.properties";

}
