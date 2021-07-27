package com.yu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yucm
 * @Date: 2021/7/27 9:44
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1000,1000,1234};
//        int resIndex = binarySearch(arr,0,arr.length-1,1000);
//        System.out.println("resIndex = " + resIndex);

        List<Integer> resIndexList = binarySearch2(arr,0,arr.length-1,1000);
        System.out.println("resIndexList = " + resIndexList);
    }

    //二分查找算法----有序数组
    /**
     *
     * @param arr
     * @param left  左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     * @create 2021/7/27 9:46
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //当left > right时，说明递归完毕整个数组，但是没有找到该元素，直接返回-1
        if (left > right){
            return -1;
        }
        if (findVal > midVal){//向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){//向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    /**
     *
     * @return int
     * @create 2021/7/27 10:08
     *
     * 1.找到mid索引值 midVal 时，不要马上返回；
     * 2.向mid索引值 midVal左边扫描，将所有满足1000的值的下标，加入集合ArrayList
     * 3.向mid索引值 midVal右边扫描，将所有满足1000的值的下标，加入集合ArrayList
     * 4.将ArrayList返回
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //当left > right时，说明递归完毕整个数组，但是没有找到该元素，直接返回-1
        if (left > right){
            return new ArrayList<>();
        }
        if (findVal > midVal){//向右递归
            return binarySearch2(arr,mid+1,right,findVal);
        }else if (findVal < midVal){//向左递归
            return binarySearch2(arr,left,mid-1,findVal);
        }else {

            List<Integer> resIndexList = new ArrayList<Integer>();

            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                //否则将temp放入集合list中
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);

            temp = mid + 1;
            while (true){
                if (temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                //否则将temp放入集合list中
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
