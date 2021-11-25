## 707 . Design Linked List

------

#### [力扣链接](https://leetcode-cn.com/problems/design-linked-list/)

设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

    get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
    addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
    addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
    addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
    deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。

 

示例：

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3

```java
/**
 * @Auther: lbgao
 * @Date: 2021/10/18 15:33
 */
 
 //节点定义
 class ListNode {
    public int val;//数据域
    public ListNode next;//指针域

    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val,ListNode head){
        this.val = val;
        this.next = head;
    }

}

public class MyLinkedList {
    //size存储链表元素的个数
    int size;
    //虚拟头结点
    public ListNode head;

    public MyLinkedList(){
        size = 0;
        head = new ListNode(0);
    }

    public MyLinkedList(int[] vals) {
        this();
        for ( int i = 0 ; i < vals.length ; i++ ) {
            this.addAtIndex(i,vals[i]);
        }
    }

    //获取第index个节点
    public ListNode getNode(int index){
        if ( index < 0 || index >= size ) return null;
        ListNode cur = head.next;
        while ( index > 0 ) {
            cur = cur.next;
            index--;
        }
        return cur;
    }

    //获取第index个节点的数值
    public int get(int index) {
        //如果index非法，返回-1
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode currentNode = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.val;
    }

    //在链表最前面插入一个节点
    public void addAtHead(int val){
        if ( head.next == null ) this.head.next = new ListNode(val);
        else {
            ListNode node = new ListNode(val);
            node.next = head.next;
            head.next = node;
        }
        size++;
        return;
    }
    //在链表的最后插入一个节点
    public void addAtTail(int val){
        if ( head.next == null ) this.head.next = new ListNode(val);
        else {
            ListNode node = new ListNode(val,null);
            ListNode cur = head.next;
            while ( cur.next != null ) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
        return;
    }

    //在链表删除指定索引节点
    public void deleteAtIndex (int index) {
        if ( index < 0 || index >= size ) {
            return;
        }
        ListNode pre = head;
        ListNode cur = head.next;

        for ( int i = 0 ; i < index ; i++ ) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        size--;
        return;
    }


    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index,int val){
        if ( index == 0 ) addAtHead(val);
        else if ( index == size ) addAtTail(val);
        else if ( index > size || index < 0 ) return;
        else {
            //找到要插入节点的前驱
            ListNode pre = head;
            ListNode cur = head.next;

            for ( int i = 0 ; i < index ; i++ ) {
                pre = cur;
                cur = cur.next;
            }
            ListNode node = new ListNode(val,cur);
            pre.next = node;
            size++;
        }
    }

    public void iterator(){
        ListNode tmp = head.next;
        while ( tmp != null ){
            System.out.print(tmp.val+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public void iterator(ListNode head){
        ListNode tmp = head;
        while ( tmp != null ){
            System.out.print(tmp.val+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }

}
```