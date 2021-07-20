package com.yu.stack;

/**
 * @Author: ycm
 * @Date: 2021/7/19 9:55
 */
public class Calculator {
    public static void main(String[] args) {
        //根据分析
        String expression = "70+20*6-2";//中缀表达式
        //创建两个栈；数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";//用于拼接多位数
        while (true){
            //依次得到expression表达式的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，
            if (operStack.isOper(ch)){//如果是运算符
                //判断是否为空，如果不为空
                if (!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                       num1 = numStack.pop();
                       num2 = numStack.pop();
                       oper = operStack.pop();
                       res = numStack.cal(num1,num2,oper);
                       //把运算结果压入数栈
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            }else {//如果是数字，直接入数栈
                //numStack.push(ch - 48);
                //当处理多位数时，需要向expression的表达式的index后再看一位，
                // 如果是数字就进行扫描，如果是符号就入栈
                //因此需要定义一个变量 字符串，用于拼接

                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位，直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一位字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意看后一位，不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的书和符号，并运算
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            //把运算结果压入数栈
            numStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d ",expression,res2);
    }
}
//定义一个ArrayStack
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回当前栈顶的值，不是弹出pop
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //判断
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况（遍历）,遍历从栈顶显示数据
    public void list() {
        //判断
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级
    //优先级使用数字表示，数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                    break;
            case '/':
                res = num2 / num1;
                break;
        default:
            break;
        }
        return res;
    }
}