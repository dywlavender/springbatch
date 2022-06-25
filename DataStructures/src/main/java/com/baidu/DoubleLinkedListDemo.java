package com.baidu;

import lombok.Data;

/**
 * @ClassName DoubleLinkedListDemo
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/12 11:35
 * @Version 1.0
 **/
public class DoubleLinkedListDemo {
    public HeroNode2 head = new HeroNode2(0,"","");
    public HeroNode2 tail = new HeroNode2(0,"","");
    public HeroNode2 getHead(){return head;}
    public void list(){
        if(this.head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public void add(HeroNode2 heroNode2){
        this.tail.next = heroNode2;
        heroNode2.pre = tail;
        tail = heroNode2;
    }
    public void update(HeroNode2 heroNode2){
        if(this.head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (temp != null){
            if(temp.no == heroNode2.no){
                temp.name = heroNode2.name;
                temp.nickname = heroNode2.nickname;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            System.out.println("没有找到该节点");
        }
    }
    public void delete(HeroNode2 heroNode2){
        if(this.head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while(temp != null){
            if(temp.no == heroNode2.no){
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(!flag){
            System.out.println("没有找到该节点");
        }
    }

}
@Data
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }


}
