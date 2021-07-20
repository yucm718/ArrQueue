package com.yu.linkedList;

import java.util.Stack;

/**
 * @Author: ycm
 * @Date: 2021/7/17 22:38
 */
//演示Stack的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack  = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        stack.add("yucm");

        //出栈
        while(stack.size() > 0){
            System.out.println(stack.pop());//pop()：将栈顶的数据取出
        }

    }
}
