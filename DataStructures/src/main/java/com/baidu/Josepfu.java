package com.baidu;

import lombok.Data;
import lombok.val;

/**
 * @ClassName Josepfu
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/12 16:09
 * @Version 1.0
 **/
public class Josepfu {
    public static void main(String[] args) {
        CirclesSingleLinkedList circlesSingleLinkedList = new CirclesSingleLinkedList();
        circlesSingleLinkedList.addBoy(5);
        circlesSingleLinkedList.showBoy();
        circlesSingleLinkedList.countBoy(1,2,5);
    }
}


class CirclesSingleLinkedList{
    private Boy fist = null;
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums 值不对");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <=nums; i++) {
            Boy boy = new Boy(i);
            if (i==1){
                fist = boy;
                fist.setNext(boy);
                curBoy = boy;
            }else {
                curBoy.setNext(boy);
                boy.setNext(fist);
                curBoy = boy;
            }
        }
    }
//    遍历当前链表
    public void showBoy(){
        if(fist == null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = fist;
        while(true){
            System.out.println("no :" + curBoy.getNo());
            if (curBoy.getNext().equals(fist)){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户输入，计算小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩
     */
    public void countBoy(int startNo, int countNum,int nums){
        if (fist == null || startNo < 0 || startNo > nums){
            System.out.println("参数输入有误");
            return;
        }
        Boy helper = fist;
        while(true){
            if (helper.getNext() == fist){
                break;
            }
            helper = helper.getNext();
        }
        for (int i = 0; i < startNo-1; i++) {
            fist = fist.getNext();
            helper = helper.getNext();
        }
        while(true){
            if (helper.equals(fist)){
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                fist = fist.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈： "+fist.getNo());
            fist = fist.getNext();
            helper.setNext(fist);
        }
        System.out.println("最后： "+ fist.getNo());
    }
}
@Data
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }
}
