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
        //说明:
        //1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        //2. 不方便直接对str进行操作，将中缀表达式转成对应List
        // "1+((2+3)*4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将中缀表达式对应的List => 后缀表达式对应的List
        String expression = "1+(2+3)*4-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List: " + infixExpressionList);//结果：[1, +, (, 2, +, 3, ), *, 4, -, 5]

        List<String> suffixExpressionList  = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List: " + suffixExpressionList );

        System.out.printf("expression = %d ",calculate(suffixExpressionList));
        //(3+4)*5-6  => 3 4 + 5 * 6 -  =>   = 29
        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "30 4 + 5 * 6 -";
//        String suffixExpression ="4 5 * 8 - 60 + 8 2 / +";
//        //思路
//        //1. 先将"3 4 + 5 * 6 -"放到 ArrayList 中
//        //2. 将ArrayList，传递一个方法，遍历ArrayList，配合栈完成计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println("rpnList = " + list);
//        int res = calculate(list);
//        System.out.println("计算的结果 = " + res);
    }

    //将中缀表达式转为对应的List 转变成 后缀表达式对应的 List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//数栈
        //说明：s2栈，在整个转换过程中没有pop操作，而却后续需要逆序输出
        //比较麻烦，因此不用Stack<String> 而是使用List<String>
//        Stack<String> s2 = new Stack<String>();//储存中间结果的Stack s2
        List<String> s2 = new ArrayList<String>();//储存中间结果的List s2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号")" ，则依次弹出s1栈顶的运算符，并加入s2，
                // 直至遇到左括号为止，此时将这对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// 将(弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶的运算符，将s1栈顶运算符弹出并加入到s2中
                //问题：缺少比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入s1栈中
                s1.push(item);
            }
        }
        //将s1中剩下的运算符一次弹出并加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//由于是存放到List中，按顺序输出解释对应的后缀表达式
    }

    //将中缀表达式转为对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//这是一个指针，用于遍历 中缀表达式字符串
        String str;//对对位数拼接
        char c;// 每遍历一个字符，就放入到 c
        do {
            //如果c是一个非数字，加到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else {//如果是数字，考虑多位数
                str = "";//先将str置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
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

//编写一个类Operation，可以返回一个运算符 对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法。返回优先级的数字
    public  static int getValue(String operation){
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
                System.out.println("不存在该运算符！");
                break;
        }
        return result;
    }
}

