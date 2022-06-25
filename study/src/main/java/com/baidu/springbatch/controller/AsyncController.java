package com.baidu.springbatch.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName AsyncController
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/21 14:41
 * @Version 1.0
 **/
@Controller
public class AsyncController {

    public DeferredResult<ResponseEntity<List<User>>> deferredResultListUser(){
        DeferredResult<ResponseEntity<List<User>>> deferredResult = new DeferredResult<>(20000L,new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
        return null;
    }
}
@Data
class User{
    private int id;
}
