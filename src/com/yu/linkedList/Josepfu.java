package com.yu.linkedList;

/**
 * @Author: ycm
 * @Date: 2021/7/18 16:29
 */
public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //小孩出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建环形单向列表
class CircleSingleLinkedList{
    //创建一个firstBoy节点，当前没有编号
    private Boy first = null;

    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确！");
            return;
        }
        //使用for循环创建环形链表
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
        //根虎用户的输入，计算出小孩出圈的顺序
        /**
         * @param startNo   表示从第几个小号开始数
         * @param countNum  表示数多少下
         * @param nums      表示最初由多少个小孩在圈中
         */
        public void countBoy(int startNo,int countNum,int nums){
            //先对数据进行校验
            if (first == null || startNo < 1 || startNo > nums){
                System.out.printf("数据有误，请重新输入");
                return;
            }
            //创建辅助指针，帮助完成小孩出圈
            Boy helper = first;
            while(true){
                if (helper.getNext() == first){
                    break;
                }
                helper = helper.getNext();
            }
            //小孩报数前，先让first 和 helper移动 k-1 次
            for (int j = 0; j < startNo - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            while (true){
                if (helper == first){//说明圈中只有一个节点
                    break;
                }
                //让first 和 helper 指针同时移动 countNum-1
                for (int j = 0; j < countNum - 1; j++){
                    first = first.getNext();
                    helper = helper.getNext();
                }
                System.out.printf("小孩%d出圈\n", first.getNo());
                first = first.getNext();
                helper.setNext(first);
            }
            System.out.printf("最后留在圈中的小孩编号%d\n",helper.getNo());
        }

    //遍历当前环形链表
    public void showBoy(){
        //判断是否为空
        if (first == null){
            System.out.println("没有任何小孩~");
            return;
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号：%d\n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }
}

//创建一个Boy类，表示一个节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}