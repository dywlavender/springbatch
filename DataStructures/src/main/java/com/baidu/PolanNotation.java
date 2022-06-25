package com.baidu;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName PolanNotation
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/13 22:59
 * @Version 1.0
 **/
public class PolanNotation {
    public static void main(String[] args) {
        String suffixExpression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(suffixExpression);
        System.out.println(list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println(list1);
        int cal = cal(list1);
        System.out.println(cal);
    }
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        while(i<s.length()){
            if((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){
                list.add("" + c);
                i++;
            }else{
                str = "";
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }
        return list;
    }

    public static List<String> parseSuffixExpressionList(List<String> list){
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item:list) {
            if(item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }
    public static int cal(List<String> list){
        Stack<Integer> strings = new Stack<>();
        for (int i=0;i<list.size();i++){
            String item = list.get(i);
            if(item.matches("\\d+")) {
                strings.add(Integer.parseInt(item));
            }else {
                int num2 = strings.pop();
                int num1 = strings.pop();
                switch (item){
                    case "+":
                        strings.push(num1 + num2);
                        break;
                    case "-":
                        strings.push(num1 - num2);
                        break;
                    case "*":
                        strings.push(num1 * num2);
                        break;
                    case "/":
                        strings.push(num1 / num2);
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            }
        }
        return strings.pop();
    }

}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("error");
                break;
        }
        return result;
    }
}
