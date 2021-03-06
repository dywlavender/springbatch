package com.baidu.fastjson;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private String name;
    private int age;
    private BigDecimal salary;
}
