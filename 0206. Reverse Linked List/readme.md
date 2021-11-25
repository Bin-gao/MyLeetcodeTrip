## 206 . Reverse Linked List

------

[力扣链接](https://leetcode-cn.com/problems/reverse-linked-list/)

Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:

![](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

Example 2:

![](https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg)

```
Input: head = [1,2]
Output: [2,1]
```

## 分析

首先定义一个cur指针，指向头结点，再定义一个pre指针，初始化为null。

然后就要开始反转了，首先要把 cur->next 节点用tmp指针保存一下，也就是保存一下这个节点。

为什么要保存一下这个节点呢，因为接下来要改变 cur->next 的指向了，将cur->next 指向pre ，此时已经反转了第一个节点了。

接下来，就是循环走如下代码逻辑了，继续移动pre和cur指针。

最后，cur 指针已经指向了null，循环结束，链表也反转完毕了。 此时我们return pre指针就可以了，pre指针就指向了新的头结点。

```java
 public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while ( cur != null ) {
            ListNode tmp = cur.next;//要操作cur->next,保持当前节点。
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
```