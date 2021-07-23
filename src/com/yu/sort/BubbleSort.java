package com.yu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @Author: ycm
 * @Date: 2021/7/23 10:47
 * 冒泡排序
 * 优化:如果我们发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序。
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//        bubbleSort(arr);
//        System.out.println("排序后的数组：");
//        System.out.println(Arrays.toString(arr));

        //测试创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i=0;i<80000;i++){
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 =  new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat1.format(date1);
        System.out.println("排序前的时间：" + date1Str);

        bubbleSort(arr);

        Date date2 =  new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间：" + date2Str);

//        System.out.println("排序后的数组：");
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将前面的冒泡排序算法，封装成一个方法
     */
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        //为了容易理解，将冒泡排序的演变过程，展示出来：
        //第一趟排序，将最大的数排在最后
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag){//在一趟排序中，一次交换都没有发生过
                break;
            }else {
                flag = false;//重置flag，进行下次判断
            }
        }
    }

//        //第二趟排序，将第二大的数排在倒数第二位
//        for (int j = 0; j < arr.length-1-1; j++){
//            if (arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println("第二趟排序后的数组：");
//        System.out.println(Arrays.toString(arr));
//
//        //第三趟排序，将第三大的数排在倒数第三位
//        for (int j = 0; j < arr.length-1-2; j++){
//            if (arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println("第三趟排序后的数组：");
//        System.out.println(Arrays.toString(arr));
//
//        //第四趟排序，将第三大的数排在倒数第四位
//        for (int j = 0; j < arr.length-1-3; j++){
//            if (arr[j] > arr[j+1]){
//                temp = arr[j];
//                arr[j] = arr[j+1];
//                arr[j+1] = temp;
//            }
//        }
//        System.out.println("第四趟排序后的数组：");
//        System.out.println(Arrays.toString(arr));
}

