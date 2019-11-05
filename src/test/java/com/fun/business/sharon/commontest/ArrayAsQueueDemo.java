package com.fun.business.sharon.commontest;


import java.util.Scanner;

/**
 * @Package: com.estone.erp.product.test
 * @ClassName: ArrayAsQueueDemo
 * @Description: 数组模拟循环队列，测试
 * @Author: liangxin
 * @CreateDate: 2019/9/25 9:46
 * @UpdateDate: 2019/9/25 9:46
 */
public class ArrayAsQueueDemo {

    public static void main(String[] args) {
        // 实际有效使用个数为4，约定一个为空
        CircleArray queue = new CircleArray(5);
        char key = ' ';
        boolean flag = true;
        while (flag){
            System.out.println();
            System.out.println("请选择您的操作类型：");
            System.out.println("s ====  显示队列所有数据");
            System.out.println("e ====  退出");
            System.out.println("a ====  往队列添加一个数");
            System.out.println("g ====  取出一个数据");
            System.out.println("h ====  显示头部数据（非取出）");

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String in = scanner.nextLine();
                key = in.charAt(0); // 获取输入的第一个字符
            }

            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'g':
                    int head = queue.getHead();
                    System.out.println("取出的头部数据是：" + head);
                    break;
                case 'a':
                    System.out.println("请输入需要添加到队列的数据：");
                    queue.addQueue(scanner.nextInt());
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    System.out.println("程序已退出！");
                    break;
                case 'h':
                    queue.showHead();
                    break;
            }
        }
    }

}

/**
 * 队列声明——增删改查
 */
class CircleArray{

    private int maxSize;

    /**
     * 指向队列第一个元素 arr[front] , front 初始值 0
     */
    private int front;

    /**
     * 指向队列最后一个元素 arr[front] , rear 初始值 0
     */
    private int rear;

    private int[] arr;

    /**
     * 构造器
     * @param maxSize
     */
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        // 另外两个变量默认值就是0，不写
    }

    /**
     * 空
     * @return
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 满
     * @return
     */
    private boolean isFull(){
        return (rear + 1) % maxSize == front; // 已测试
    }

    /**
     * 添加数据
     * @param insert
     */
    public void addQueue(int insert){
        if (isFull()) {
            System.err.println("队列已满！");
            return;
        }
        arr[rear] = insert;
        // 尾部后移
        rear = ( rear + 1 ) % maxSize;
    }

    /**
     * 取出数据
     * @return
     */
    public int getHead(){
        if (isEmpty()) {
            System.err.println("数据为空");
            throw new RuntimeException("空");
        }

        int tempValue = arr[front];

        front = (front + 1) % maxSize;

        return tempValue;
    }

    /**
     * 显示队列头部数据
     * @return
     */
    public int showHead(){
        if (isEmpty()) {
            throw new RuntimeException("空");
        }
        return arr[front];
    }

    /**
     * 可用数据个数
     * @return
     */
    public int getSize(){
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示所有数据
     */
    public void showQueue(){
        if (isEmpty()) {
            System.err.println("数据为空");
        }
        for (int i = front; i < front + getSize(); i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

}
