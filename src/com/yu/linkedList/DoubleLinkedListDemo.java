package com.yu.linkedList;

/**
 * @Author: ycm
 * @Date: 2021/7/18 11:15
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(1);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    // 初始化一个头节点，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");
    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    //显示链表
    public void list(){
        //判断列表是否为空
        if (head.next  == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，需要辅助变量来遍历
        HeroNode2 temp = head.next;
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

    // 添加节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //head节点不能动，因此我们需要一个辅助变量遍历temp
        HeroNode2 temp = head;
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
        //形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改双向链表的节点
    //根据no编号来修改节点的信息
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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
    //1. 对于双向链表，直接找到待删除节点，找到后，自我删除即可
    public void del(int no){
        //判断双向链表是否为空
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;//标志是否找到待删除节点
        while(true){
            if (temp.next == null){//已经到链表的最后
                break;
            }
            if (temp.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag){
//            temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出空指针异常
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("待删除的 %d 节点不存\n",no);
        }
    }

    //第二种添加英雄方式，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode2 heroNode){
        //通过辅助变量来找到添加的位置
        HeroNode2 temp = head;
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
}



//定义HeroNode2，每个HeroNode对象是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
