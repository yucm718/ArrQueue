package com.yu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: ycm
 * @Date: 2021/7/25 20:16
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70,-1,900,456};
//        quickSort(arr,0,arr.length-1);
//        System.out.println("arr = " + Arrays.toString(arr));

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

        quickSort(arr,0,arr.length-1);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("--------排序后的时间：" + dateStr2);

    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];//中轴值
        int  temp = 0;

        //目的：让 比pivot小的值放到左边 ，比pivot大的值放右边
        while (l < r){
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while (arr[l] < pivot){
                l += 1;
            }
            //在pivot的左边一直找，找到小于等于pivot的值，才退出
            while (arr[r] > pivot){
                r -= 1;
            }
            // 如果 l >= r,说明pivot的左右两边的值，
            // 已经按照左边全部是小于等于pivot的值
            // 右边全部是大于等于pivot的值
            if (l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后发现，发现arr[l] == pivot时 r前移(r--)
            if (arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后发现，发现arr[r] == pivot时 l后移(l++)
            if (arr[r] == pivot){
                l += 1;
            }
        }

        //如果l=r,必须l++，r--，否则出现栈溢出
        if (l == r){
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }

    }
}
