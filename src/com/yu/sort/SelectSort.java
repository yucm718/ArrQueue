package com.yu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ycm
 * @Date: 2021/7/24 11:13
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101,34,119,1,90,-1,99};
        //创建一个80000个数的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

        selectSort(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后的时间：" + dateStr2);

//        System.out.println("排序后的数组：");
//        System.out.println(Arrays.toString(arr));

    }

    public static void selectSort(int[] arr){

        //使用逐步推导的方法
        //原始数组:101,34,119,1
        //第一轮排序 ：1,34,119,101

        //第1轮：
        for (int i = 0;i < arr.length - 1;i++){

            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1;j < arr.length; j++){
                if (min > arr[j]){
                    min = arr[j];//找出最小值和最小值下标
                    minIndex = j;
                }
            }
            //将最小值放在arr[0]这个位置
            if (minIndex != i){
                arr[minIndex] = arr[i];//把101赋值给arr[3]
                arr[i] = min;//把3赋给arr[0]
            }
        }
        //第2轮：
//        minIndex = 1;
//        min = arr[1];
//        for (int j = 1 + 1;j < arr.length; j++){
//            if (min > arr[j]){
//                min = arr[j];//找出最小值和最小值下标
//                minIndex = j;
//            }
//        }
//        //将最小值放在arr[0]这个位置
//        if (minIndex != 1){
//            arr[minIndex] = arr[1];//把101赋值给arr[3]
//            arr[1] = min;//把3赋给arr[0]
//        }
//
//        System.out.println("第2轮后：");
//        System.out.println(Arrays.toString(arr));
//
//        //第3轮:
//        minIndex = 2;
//        min = arr[2];
//        for (int j = 2 + 1;j < arr.length; j++){
//            if (min > arr[j]){
//                min = arr[j];//找出最小值和最小值下标
//                minIndex = j;
//            }
//        }
//        //将最小值放在arr[0]这个位置
//        if (minIndex != 2){
//            arr[minIndex] = arr[2];
//            arr[2] = min;
//        }
//
//        System.out.println("第3轮后：");
//        System.out.println(Arrays.toString(arr));
    }

}
