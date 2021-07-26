package com.yu.sort;

import java.util.Arrays;

/**
 * @Author: ycm
 * @Date: 2021/7/26 11:27
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0, arr.length - 1,temp);

        System.out.println("排序后的数组 = " + Arrays.toString(arr));

    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if(left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并的方法
    /**
     * @param arr 排序的原始数组
     * @param left 左边索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转数组
     * @return void
     * @create 2021/7/26 11:48
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        System.out.println("--------------" );
        int i = left;
        int j = mid + 1;
        int t = 0;//temp数组的下标

        //(1)
        //先把左右两边(有序)的数据按照规则填充到 temp 数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while( i <= mid && j <= right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素,填充的temp数组
            //然后 i++,t++
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {//反之，将右边的当前元素填充到temp数组
                temp[t] = temp[j];
                t += 1;
                j += 1;
            }
        }
        //(2)
        //把有剩余数据的一边的数据依次全部填充到 temp
        while (i <= mid){//说明左边的有序序列还有剩余元素，就全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right){//说明右边的有序序列还有剩余元素，就全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //(3)
        //将 temp 数组的元素拷贝到 arr
        //并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft = " + tempLeft + "  rigth = " + right);
        //第一次合并，tempLeft=0,right=1;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
