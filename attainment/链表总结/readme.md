## 链表总结

链表是一种通过指针串联在一起的线性结构，每一个节点由两部分组成，一个是数据与一个是指针

域（存放下一个节点的指针），最后一个节点的指针与为null。

如图：

![](https://img-blog.csdnimg.cn/20200806194529815.png)

## 链表类型

### 单链表
刚刚所述就是单链表。

java节点代码
```java
public class ListNode {
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
```

### 双链表
单链表中的节点只能指向下一个节点。

双链表的节点多了一个可以指向上一个节点的指针。

双链表既可以向前查询也可以向后查询。
如图：
![](https://img-blog.csdnimg.cn/20200806194559317.png)

java节点代码
```java
public class ListNode {
        int val;
        ListNode next,prev;
        public ListNode() {
    	}
        ListNode(int x) {val = x;}
    }
```

### 循环链表
头节点和尾节点连在一起的链表，可以是单向的，也可以是双向的。
如图：
![](https://img-blog.csdnimg.cn/20200806194629603.png)

## 链表的存储方式

数组是在内存中是连续分布的，但是链表在内存中可不是连续分布的。

链表是通过指针域的指针链接在内存中各个节点。

所以链表中的节点在内存中不是连续分布的 ，而是散乱分布在内存中的某地址上，分配机制取决于操作系统的内存管理。
如图：
![](https://img-blog.csdnimg.cn/20200806194613920.png)

## 链表的操作

力扣707题设计链表[707 . Design Linked List](https://leetcode-cn.com/problems/design-linked-list/)有链表的增删查。

### 单链表添加节点

添加节点F，C向指向F，F再指向D就可以了。

如图：
![](https://img-blog.csdnimg.cn/20200806195134331.png)

可以看出链表的增添和删除都是O(1)操作，也不会影响到其他节点。

```java
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
```

### 单链表删除节点

只要将C节点的next指针 指向E节点就可以了。

如图：
![](https://img-blog.csdnimg.cn/20200806195114541.png)

```java
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

```

### 单链表查某个节点

```java
    //无参初始化链表
    public MyDoubleLinkedList() {
        size = 0;
        head = new DoubleListNode(-1);
        tail = new DoubleListNode(-1);
        head.next = tail;
        tail.prev = head;
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
 //在链表的最前面插入数值
    public void addAtHead(int val) {
        DoubleListNode cur = head;
        DoubleListNode newNode = new DoubleListNode(val);
        newNode.next = cur.next;
        cur.next.prev = newNode;
        cur.next = newNode;
        newNode.prev = cur;
        size++;
    }

    //在链表的最后面插入数值
    public void addAtTail(int val) {
        DoubleListNode cur = tail;
        DoubleListNode newNode = new DoubleListNode(val);
        newNode.next = tail;
        newNode.prev = cur.prev;
        cur.prev.next = newNode;
        cur.prev = newNode;
        size++;
    }

    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index,int val) {
        if ( index == 0 ) addAtHead(val);
        else if ( index == size ) addAtTail(val);
        else if ( index > 0 && index < size){
            size++;
            DoubleListNode node = new DoubleListNode(val);
            DoubleListNode preNode = head.next;
            for ( int i = 0 ; i < index-1 ; i++ ) {
                preNode = preNode.next;
            }
            node.next = preNode.next;
            preNode.next.prev = node;
            node.prev = preNode;
            preNode.next = node;
        }
    }

    //删除第index个节点
    public void deleteAtIndex(int index) {
        if ( index < 0 || index >= size ) return;

        DoubleListNode preNode = head;
        for ( int i = 0 ; i < index ; i++ ) {
            preNode = preNode.next;
        }
        preNode.next.next.prev = preNode;
        preNode.next = preNode.next.next;
        size--;

    }
```



