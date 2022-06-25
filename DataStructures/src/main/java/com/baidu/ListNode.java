package com.baidu;

import java.util.List;

/**
 * @ClassName ListNode
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/21 12:25
 * @Version 1.0
 **/
public class ListNode {
    private int a;
    public ListNode(int a){
        this.a = a;
    }
    public ListNode next;
    public void add(ListNode next){
        this.next = next;
    }
}