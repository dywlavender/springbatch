package com.baidu;

/**
 * @ClassName LeetCode
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/21 12:25
 * @Version 1.0
 **/
public class LeetCode {
    public static ListNode rotateRight(ListNode head, int k) {
        int length = 0;
        if(head == null){
            return head;
        }
        ListNode temp = head;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        k = k % length;
        ListNode former = head;
        ListNode latter = head;
        for(int i=0;i<length;i++){
            former = former.next;
        }
        while(former.next != null){
            former = former.next;
            latter = latter.next;
        }
        former.next = head;
        head = latter.next;
        latter.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.add(new ListNode(2));
        listNode.add(new ListNode(3));
        listNode.add(new ListNode(4));
        listNode.add(new ListNode(5));
        System.out.println(rotateRight(listNode,2));

    }
}

