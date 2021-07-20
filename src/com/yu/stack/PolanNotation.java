package com.yu.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Author: ycm
 * @Date: 2021/7/19 21:53
 */
public class PolanNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式
        //

        //(3+4)*5-6  => 3 4 + 5 * 6 -  =>   = 29
        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression ="4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1. 先将"3 4 + 5 * 6 -"放到 ArrayList 中
        //2. 将ArrayList，传递一个方法，遍历ArrayList，配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println("rpnList = " + list);
        int res = calculate(list);
        System.out.println("计算的结果 = " + res);
    }

    //将一个逆波兰表达式，依次将数据和运算符 放入ArrayList中
    public static List<String>  getListString(String suffixExpression){
        String [] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split){
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        //创建栈，只需要一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls){
            //使用正则表达式来取出数
            if (item.matches("\\d+")){//匹配得失多位数
                //入栈
                stack.push(item);
            }else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误！");
                }
                //将res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据为结果
        return Integer.parseInt(stack.pop());
    }
}
