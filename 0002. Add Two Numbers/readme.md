## 2 . Add Two Numbers

------

#### [力扣链接](https://leetcode-cn.com/problems/add-two-numbers/)

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
 ![](https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg)

 ```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
 ```

Example 2:
 ```
Input: l1 = [0], l2 = [0]
Output: [0]
 ```
## 分析

- 两个链表同时进行遍历，定义一个变量pro，考虑每一位的和是否需要进位，如果需要则pro赋值为1，没有的话则赋值为0，如果l1为927，l2为952，第一位9+9为18，需要进位，则新的链表newList第一位为8，同时pro赋值为1，newList下一位为2+5+1为8，不需要进位，为8，pro赋值0.
- 如果两个链表全部遍历完毕后，pro为0，需要再newList后面添加节点1，说明l1，l2最后一个结点相加需要进位。
- 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。

```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int pro = 0;
        while( l1 != null || l2 != null ) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + pro;
            
            pro = sum / 10;//sum > 10 pro赋值为1，sum < 10 pro赋值为0
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if( l1 != null )
                l1 = l1.next;
            if( l2 != null )
                l2 = l2.next;
        }
        if( pro == 1 ) {
            cur.next = new ListNode(pro);
        }
        return pre.next;
    }

```



