package com.baidu.integration;

import lombok.Data;

/**
 * @ClassName InterfaceConfig
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/10 19:57
 * @Version 1.0
 **/
@Data
public class InterfaceConfig {
    private Long id;
    private String interfaceNo;
    private String interfaceType;
    private String interfaceName;
    private String status;
    private String interfaceOrganization;
    private String serviceCode;
    private String serviceSense;
    private String transCode;


}
