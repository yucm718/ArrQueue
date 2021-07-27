package com.yu.search;

/**
 * @Author: yucm
 * @Date: 2021/7/26 17:44
 * 查找算法
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,44,-1,34,89};
        int index = seqSearch(arr,89);
        if (index == -1){
            System.out.println("没有查找到该值！");
        }else {
            System.out.println("查找到该值，下标为 = " + index);
        }
    }

    /**
     *
     * @param arr
     * @param value
     * @return int
     * @create 2021/7/26 17:46
     */
    public static int seqSearch(int[] arr,int value){
        //线性查找是逐一对比，就返回下标
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
