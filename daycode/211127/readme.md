## 链表的一天21.11.27

#### 题目和链接

[24 . Swap Nodes in Pairs](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

[面试题 02.07 . Intersection of Two Linked Lists LCCI](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/)

[141 . Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)

[142 . Linked List Cycle II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

[287 . Find the Duplicate Number](https://leetcode-cn.com/problems/find-the-duplicate-number/)

#### 总结

两两交换链表中的节点[24 . Swap Nodes in Pairs](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)，比较简单，也很容易想到思路，

建议使用虚拟头结点，这样会方便很多，要不然每次针对头结点（没有前一个指针指向头结点），还要单独处理。

```java
// 虚拟头结点
class Solution {
	//时间复杂度：O(n)
    //空间复杂度：O(1)
  public ListNode swapPairs(ListNode head) {

    ListNode dummyNode = new ListNode(0);
    dummyNode.next = head;
    ListNode prev = dummyNode;

    while (prev.next != null && prev.next.next != null) {
      ListNode temp = head.next.next; // 缓存 next
      prev.next = head.next;          // 将 prev 的 next 改为 head 的 next
      head.next.next = head;          // 将 head.next(prev.next) 的next，指向 head
      head.next = temp;               // 将head 的 next 接上缓存的temp
      prev = head;                    // 步进1位
      head = head.next;               // 步进1位
    }
    return dummyNode.next;
  }
}
```

来看看链表相交这道题[面试题 02.07 . Intersection of Two Linked Lists LCCI](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/)，题目看起来很复杂，简单说，就是求两个链表交点节点的指针，注意，交点不是数值相等，而是指针相等。

看如下两个链表，目前curA指向链表A的头结点，curB指向链表B的头结点：
![](https://code-thinking.cdn.bcebos.com/pics/%E9%9D%A2%E8%AF%95%E9%A2%9802.07.%E9%93%BE%E8%A1%A8%E7%9B%B8%E4%BA%A4_1.png)
我们求出两个链表的长度，并求出两个链表长度的差值，然后让curA移动到和curB 末尾对齐的位置，如图：
![](https://code-thinking.cdn.bcebos.com/pics/%E9%9D%A2%E8%AF%95%E9%A2%9802.07.%E9%93%BE%E8%A1%A8%E7%9B%B8%E4%BA%A4_2.png)
此时我们就可以比较curA和curB是否相同，如果不相同，同时向后移动curA和curB，如果遇到curA == curB，则找到交点。

否则循环退出返回空指针。

```java
public class Solution {
	//时间复杂度：O(n + m)
    //空间复杂度：O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) { // 求链表A的长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null) { // 求链表B的长度
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        // 让curA为最长链表的头，lenA为其长度
        if (lenB > lenA) {
            //1. swap (lenA, lenB);
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            //2. swap (curA, curB);
            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }
        // 求长度差
        int gap = lenA - lenB;
        // 让curA和curB在同一起点上（末尾位置对齐）
        while (gap-- > 0) {
            curA = curA.next;
        }
        // 遍历curA 和 curB，遇到相同则直接返回
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}
```

接下来是三道关于循环链表的题，其中[141 . Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)就是纯粹的判断是不是循环链表了。
代码如下：

```java
public class Solution {
	//时间复杂度：O(n)
    //空间复杂度：O(1)
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
```
之后的两道题其实差不多，在[141 . Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)的基础上，找出环的入口，不同的是
[142 . Linked List Cycle II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)给的是链表，在链表中找环的入口，而
[287 . Find the Duplicate Number](https://leetcode-cn.com/problems/find-the-duplicate-number/)给的是数组，在数组中找环的入口。

最难的是找这个环的入口，涉及到了一些数学问题。

在我们已经判断出存在环了的情况下，slow指针和fast指针是指向同一个位置的。

假设从头结点到环形入口节点 的节点数为x。 环形入口节点到 fast指针与slow指针相遇节点 节点数

为y。 从相遇节点 再到环形入口节点节点数为 z。 如图所示：

![](https://img-blog.csdnimg.cn/20210318162938397.png)

那么相遇时： slow指针走过的节点数为: x + y， fast指针走过的节点数：x + y + n (y + z)，n为fast

指针在环内走了n圈才遇到slow指针， （y+z）为 一圈内节点的个数A。

因为fast指针是一步走两个节点，slow指针一步走一个节点， 所以 fast指针走过的节点数 = slow指

针走过的节点数 * 2：

(x + y) * 2 = x + y + n (y + z)

两边消掉一个（x+y）: x + y = n (y + z)

因为要找环形的入口，那么要求的是x，因为x表示 头结点到 环形入口节点的的距离。

所以要求x ，将x单独放在左面：x = n (y + z) - y ,

再从n(y+z)中提出一个 （y+z）来，整理公式之后为如下公式：x = (n - 1) (y + z) + z 注意这里n一定是大于等于1的，因为 fast指针至少要多走一圈才能相遇slow指针。

这个公式说明什么呢？

先拿n为1的情况来举例，意味着fast指针在环形里转了一圈之后，就遇到了 slow指针了。

当 n为1的时候，公式就化解为 x = z，

这就意味着，从头结点出发一个指针，从相遇节点 也出发一个指针，这两个指针每次只走一个节点， 那么当这两个指针相遇的时候就是 环形入口的节点。

也就是在相遇节点处，定义一个指针index1，在头结点处定一个指针index2。

让index1和index2同时移动，每次移动一个节点， 那么他们相遇的地方就是 环形入口的节点。


那么 n如果大于1是什么情况呢，就是fast指针在环形转n圈之后才遇到 slow指针。

其实这种情况和n为1的时候 效果是一样的，一样可以通过这个方法找到 环形的入口节点，只不过，index1 指针在环里 多转了(n-1)圈，然后再遇到index2，相遇点依然是环形的入口节点。



```java
//时间复杂度：O(n)
//空间复杂度：O(1)
 public  ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while ( fast.next != null && fast != null ) {
            slow = slow.next;
            fast = fast.next.next;

            if ( slow == fast ) {
                ListNode node1 = head;
                ListNode node2 = slow;

                while ( node1 != node2 ) {

                    node1 = node1.next;
                    node2 = node2.next;
                }
                return node1;
            }

        }
        return null;
    }
```

[287 . Find the Duplicate Number](https://leetcode-cn.com/problems/find-the-duplicate-number/)有了这个条件 “其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。”  我们对 nums 数组建图，每个位置 i 连一条 i→nums[i]的边。由于存在的重复的数字 target因此 target 这个位置一定有起码两条指向它的边，因此整张图一定存在环。

![](https://assets.leetcode-cn.com/solution-static/287/25.PNG)

```java
	//时间复杂度：O(n)
    //空间复杂度：O(1)
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
```

