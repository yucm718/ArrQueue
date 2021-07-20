package com.yu.queue;

import java.util.Scanner;

/**
 * @Author: ycm
 * @Date: 2021/7/16 10:01
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个环形队列
        CircleArray queue = new CircleArray(4);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从取出数据队列");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数:");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是:" + "%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头数据为" + "%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

    class CircleArray {
        private int maxSize; //表示数组的最大容量
        //front：队列的第一个元素
        //front初始值=0
        private int front;
        //rear:队列的最后一个元素的后一个位置
        //rear初始值=0
        private int rear;
        private int[] arr; //该数据用于存放数据，模拟队列

        public CircleArray(int arrMaxSize) {
            maxSize = arrMaxSize;
            arr = new int[maxSize];
        }

        //判断队列是否满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        //判断队列是否空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n) {
            //判断队列是否满
            if (isFull()) {
                System.out.println("队列满，不能加入数据！");
                return;
            }
            //直接加入数据
            arr[rear] = n;
            //将rear后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }

        //获取队列的数据，出队列
        public int getQueue() {
            //判断队列是否空
            if (isEmpty()) {
                throw new RuntimeException("队列满，不能加入数据！");
            }
            //1. 先把front对应的值保留到一个临时变量
            //2.将front后移，考虑取模
            //3.将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列所有数据
        public void showQueue() {
            //遍历
            if (isEmpty()) {
                System.out.println("队列为空，没有数据！");
                return;
            }
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
            }
        }

        //求出当前队列的有效数据的个数
        public int size() {
            //
            return (rear + maxSize - front) % maxSize;
        }

        //显示队列的头数据，注意不是取出数据
        public int headQueue() {
            //判断
            if (isEmpty()) {
                throw new RuntimeException("队列空，没有数据~");
            }
            return arr[front];
        }
    }

