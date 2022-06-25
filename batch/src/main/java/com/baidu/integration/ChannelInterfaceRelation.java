package com.baidu.integration;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ChannelInterfaceRelation 渠道产品接口关系表
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/10 19:41
 * @Version 1.0
 **/
@Data
public class ChannelInterfaceRelation implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String channelCode;
    private String channelProductCode;
    private String interfaceDefineCode;

}
