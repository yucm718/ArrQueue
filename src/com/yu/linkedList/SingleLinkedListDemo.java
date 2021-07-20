package com.yu.linkedList;

import java.util.Stack;

/**
 * @Author: ycm
 * @Date: 2021/7/16 17:44
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //单链表的反转
        System.out.println("原链表的情况：");
        singleLinkedList.list();

        //逆序打印单链表
        System.out.println("逆序打印单链表：");
        reversePrint(singleLinkedList.getHead());

//        System.out.println("\n反转单链表：");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

/*
        //加入按照编号的顺讯
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
        //测试修改节点
//        HeroNode newHeroNode = new HeroNode(2,"小卢~~","玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改之后的链表：");
//        singleLinkedList.list();
        //测试删除
//        singleLinkedList.del(2);
//        System.out.println("删除之后的链表：");
//        singleLinkedList.list();
        //测试 求单链表中有效节点的个数
        System.out.println("有效节点的个数：" + getLength(singleLinkedList.getHead()));
        //测试 是否得到了倒数第K个元素
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),1);
        System.out.println("res = " + res);

 */
    }

    //方式2：
    //利用栈这个数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;//空链表，不能打印；
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null){
            stack.push(cur);
            cur = cur.next;//cur后移，压入下一个节点
        }
        //将栈中的节点打印
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    //将单链表反转
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助变量，遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点cur的下一个节点
        HeroNode reverseHead  = new HeroNode(0,"","");
        //遍历原来的链表将cur连接到新链表上
        //每遍历一个节点，就将其取出，并放在新链表reverseHead 的最前端
        while(cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，
            cur.next = reverseHead.next;//将cur的下一个节点指向新链表的最前端
            reverseHead.next = cur;//
            cur = next;//让cur后移
        }
        //将head.next 指向 reversrHead.next ,实现单链表反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第K个节点
    //思路：
    //1. 编写一个方法，接受head节点，同时接受一个index
    //2. index表示倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总长度getLength
    //4. 得到size后，我们从链表的第一个开始遍历（size-index）个
    //5. 如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一个遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //方法：获取到单链表节点的个数（如果是带头节点的链表，不统计头节点）
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

}
//定义一个SingleLinkedList，管理英雄
class SingleLinkedList{
    // 初始化一个头节点，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead(){
        return head;
    }
    // 添加节点到单链表
    // 思路：当不考虑编号顺序时，
    // 1.找到当前链表的最后节点
    // 2.将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode){
        //head节点不能动，因此我们需要一个辅助变量遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向链表最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    //第二种添加英雄方式，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode){
        //通过辅助变量来找到添加的位置
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){//说明temp已处于链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){//位置找到，就在temp后面
                break;
            }else if (temp.next.no == heroNode.no){//说明希望添加的heroNode的编号已经存在
                flag = true; //说明编号存在
                break;
            }

            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag){//不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号：%d 已经存在，不能加入\n" + heroNode.no);
        }else{
            //插入链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据no编号来修改节点的信息
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no){//找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    //删除节点
    //思路：
    //1.head不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明：比较时，是temp.next.no 和 待删除节点的no 比较
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while(true){
            if (temp.next == null){//已经到链表的最后
                break;
            }
            if (temp.next.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("待删除的 %d 节点不存\n",no);
        }
    }

    //显示链表
    public void list(){
        //判断列表是否为空
        if (head.next  == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，需要辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            //判断是否到最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，
            temp = temp.next;
        }
    }

}

//定义一个HeroNode，每一个HeroNode对象是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
