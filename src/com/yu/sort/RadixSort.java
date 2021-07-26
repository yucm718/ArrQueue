package com.yu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author: yucm
 * @Date: 2021/7/26 16:50
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53,3,542,748,14,214,9090,8989,6767,444,333};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("----------------排序前的时间：" + dateStr1);

        radixSort(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("--------排序后的时间：" + dateStr2);
    }

    //基数排序方法
    public static void radixSort(int[] arr){
        //根据前面的推导，得到最终的基数排序的代码
        //得到数组中最大数的位数
        int max = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > max){
                max =arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，
        //定义一个一维数组来记录各个桶的每次存放的数据个数
        //bucketElementCounts[0],记录的就是bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10){

            for (int j = 0; j < arr.length; j++){
                //取出每个元素对应位的值
                int digitOfElement = arr[j] /n % 10;
                //放到对应的桶
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标一次取出数据，放入原来数组)
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放到原数组
            for (int k = 0; k < bucketElementCounts.length; k++){
                //如果桶中有数据
                if (bucketElementCounts[k] != 0){
                    for (int l = 0; l < bucketElementCounts[k];l++){
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后需要将每个桶置0
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮，基数排序处理后：" + Arrays.toString(arr));
        }
//        //第1轮（针对每个元素的个位进行排序）
//        for (int j = 0; j < arr.length; j++){
//            //取出每个元素个位的值
//            int digitOfElement = arr[j] % 10;
//            //放到对应的桶
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照这个桶的顺序(一维数组的下标一次取出数据，放入原来数组)
//        int index = 0;
//        //遍历每一个桶，并将桶中的数据，放到原数组
//        for (int k = 0; k < bucketElementCounts.length; k++){
//            //如果桶中有数据
//            if (bucketElementCounts[k] != 0){
//                for (int l = 0; l < bucketElementCounts[k];l++){
//                    //取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            //第1轮处理后需要将每个桶置0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第1轮，对个位数的排序处理：" + Arrays.toString(arr));
//
//        //-------------------------------------------------------
//
//        //第2轮（针对每个元素的十位进行排序）
//        for (int j = 0; j < arr.length; j++){
//            //取出每个元素个位的值
//            int digitOfElement = arr[j] /10 % 10;
//            //放到对应的桶
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照这个桶的顺序(一维数组的下标一次取出数据，放入原来数组)
//        index = 0;
//        //遍历每一个桶，并将桶中的数据，放到原数组
//        for (int k = 0; k < bucketElementCounts.length; k++){
//            //如果桶中有数据
//            if (bucketElementCounts[k] != 0){
//                for (int l = 0; l < bucketElementCounts[k];l++){
//                    //取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            //第2轮处理后需要将每个桶置0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第2轮，对十位数的排序处理：" + Arrays.toString(arr));
//
//        //--------------------------------------------------------------
//
//        //第3轮（针对每个元素的百位进行排序）
//        for (int j = 0; j < arr.length; j++){
//            //取出每个元素个位的值
//            int digitOfElement = arr[j] /10 /10 % 10;
//            //放到对应的桶
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//        //按照这个桶的顺序(一维数组的下标一次取出数据，放入原来数组)
//        index = 0;
//        //遍历每一个桶，并将桶中的数据，放到原数组
//        for (int k = 0; k < bucketElementCounts.length; k++){
//            //如果桶中有数据
//            if (bucketElementCounts[k] != 0){
//                for (int l = 0; l < bucketElementCounts[k];l++){
//                    //取出元素放入到arr
//                    arr[index++] = bucket[k][l];
//                }
//            }
//            //第3轮处理后需要将每个桶置0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第3轮，对百位数的排序处理：" + Arrays.toString(arr));
    }
}
