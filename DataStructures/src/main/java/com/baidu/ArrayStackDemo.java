package com.baidu;

/**
 * @ClassName ArrayStackDemo
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/12 19:01
 * @Version 1.0
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        String expression = "2*9/3/3*6-2";
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        while (true){
            ch = expression.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.cal(num1,num2, operStack.pop());
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else{
                numStack.push(ch - 48);
            }
            index++;
            if(index == expression.length()){
                break;
            }
        }
        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            if (operStack.peek() == '-'){
                res = - numStack.cal(num1,-num2,oper );
            }else if (operStack.peek() == '/'){
                res = 1 / numStack.cal(num1,1/num2,oper );
            }else {
                res = numStack.cal(num1,num2,oper );
            }

            numStack.push(res);
        }
        System.out.println(numStack.pop());

    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack =  new int[this.maxSize];
    }

    public boolean isFull(){
        return top == this.maxSize - 1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }
    public int peek(){
        if (top == -1){
            return -1;
        }
        return stack[top];
    }
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%s\n",i,stack[i]);
        }
    }

    public int priority(int ch){
        if(ch == '*' || ch == '/' ){
            return 1;
        } else if (ch == '+' || ch == '-') {
            return 0;
        }else {
            return -1;
        }
    }
    public boolean isOper(char ch){
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }
    public int cal(int num1, int num2, int ch){
        int res = 0;
        switch (ch){
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }
}
