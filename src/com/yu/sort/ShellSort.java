package com.yu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: ycm
 * @Date: 2021/7/25 16:35
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("----------------排序前的时间：" + dateStr1);

        shellSort2(arr);

//        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("--------排序后的时间：" + dateStr2);

    }

//    //交换法进行希尔排序
//    public static void shellSort(int[] arr){
//        int temp = 0;
//         int conut = 0;
//        //根据前面的逐步分析，使用循环
//        for (int gap = arr.length / 2; gap > 0; gap /= 2){
//            //希尔排序第 1 轮：
//            //将10个数据分成5组
//            for (int i = gap; i < arr.length; i++){
//                //遍历各组中所有元素(共gap组，每组2个元素) 步长5
//                for (int j = i - gap; j >= 0; j -= gap){
//                    if (arr[j] > arr[j + gap]){
//                        temp = arr[j];
//                        arr[j] = arr[j + gap];
//                        arr[j + gap] = temp;
//                    }
//                }
//            }
////            conut++;
////            System.out.println("第"+(conut)+"轮排序后：");
////            System.out.println(Arrays.toString(arr));
//        }

//        //希尔排序第 1 轮：
//        //将10个数据分成5组
//        for (int i = 5; i < arr.length; i++){
//            //遍历各组中所有元素(共5组，每组2个元素) 步长5
//            for (int j = i - 5; j >= 0; j -= 5){
//                if (arr[j] > arr[j + 5]){
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println("第1轮：");
//        System.out.println(Arrays.toString(arr));
//
//        //希尔排序第 2 轮：
//        //将10个数据分成 5/2 = 2组
//        for (int i = 2; i < arr.length; i++){
//            //遍历各组中所有元素(共2组，每组5个元素) 步长5
//            for (int j = i - 2; j >= 0; j -= 2){
//                if (arr[j] > arr[j + 2]){
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("第2轮：");
//        System.out.println(Arrays.toString(arr));
//
//
//        //希尔排序第 3 轮：
//        //将10个数据分成 5/2 = 2/2 = 1组
//        for (int i = 1; i < arr.length; i++){
//            //遍历各组中所有元素(共2组，每组5个元素) 步长5
//            for (int j = i - 1; j >= 0; j -= 1){
//                if (arr[j] > arr[j + 1]){
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("第3轮：");
//        System.out.println(Arrays.toString(arr));
//    }
    public static void shellSort2(int[] arr){
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
