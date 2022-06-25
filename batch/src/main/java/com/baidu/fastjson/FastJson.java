package com.baidu.fastjson;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.List;

public class FastJson {
    // private final int a;
    public static void main(String[] args) {
        //
        User user = new User();
        user.setAge(1);
        user.setName("xiangming");
        user.setSalary(new BigDecimal("123"));
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        String josnString2= "{\"age\":1,\"name\":\"zs\",\"salary\":123}";
        User user1 = JSON.parseObject(josnString2, User.class);
        System.out.println(user1.getName());

        String josnStringArray= "[{\"age\":1,\"name\":\"zs\",\"salary\":123}]";
        List<User> userList = JSON.parseArray(josnStringArray,User.class);
        System.out.println(userList.size());
    }
}
