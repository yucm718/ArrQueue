package com.yu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: ycm
 * @Date: 2021/7/24 16:56
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101,34,119,1};
//        insertSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

//        System.out.println(Arrays.toString(arr));
//        System.out.println();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("----------------排序前的时间：" + dateStr1);
        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("--------排序后的时间：" + dateStr2);

    }

    //插入排序
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
        //第1轮：{101,34,119,1} --> {34,101,119,1}
        //定义待插入的数
        int insertVal = arr[i];
        int insertIndex = i - 1;//即arr[1]的前面的这个数的下标

        //给insertVal 找到插入的位置
        //说明：
        //1.insertIndex >= 0保证在给 insertVal 找到插入位置，不越界
        //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3.将 insertIndex 后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];//把arr[0]的值101 赋给 arr[1]
//            arr[i] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex+1
        //判断是否需要赋值
        if (insertIndex + 1 != i){
            arr[insertIndex + 1] = insertVal;
        }


//        System.out.println("第"+ i +"轮插入：");
//        System.out.println(Arrays.toString(arr));

        }
        //第2轮：
//        insertVal = arr[2];
//        insertIndex = 2 - 1;
//        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex];//把arr[0]的值101 赋给 arr[1]
//            insertIndex--;
//        }
//        arr[insertIndex + 1] = insertVal;
//        System.out.println("第2轮插入：");
//        System.out.println(Arrays.toString(arr));
//
//        //第3轮：
//        insertVal = arr[3];
//        insertIndex = 3 - 1;
//        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex];//把arr[0]的值101 赋给 arr[1]
//            insertIndex--;
//        }
//        arr[insertIndex + 1] = insertVal;
//        System.out.println("第3轮插入：");
//        System.out.println(Arrays.toString(arr));
    }
}
