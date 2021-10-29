## 边界问题

------

### 二分查找法

数组为有序数组，数组中无重复元素，这些都是使用二分法的前提条件，当题目满足这些条件时，可以考虑使用二分法解决问题。

二分查找涉及很多边界问题，逻辑比较简单，但不好写。

例如到底是 `while(left < right)` 还是 `while(left <= right)`，到底是`right = middle`呢，还是要`right = middle - 1`呢？

会出现这些问题，主要还是因为对区间的定义没有想清楚，区间的定义就是不变量。要在二分查找的过程中，保持不变量，就是在while寻找中每一次边界的处理都要坚持根据区间的定义来操作，这就是**循环不变量**规则。

区间不变量会出现在很多题目中，目前（截至2021.10.27）我遇到的可以算需要考虑区间的题目如下：

- [59. Spiral Matrix II](https://leetcode-cn.com/problems/spiral-matrix-ii/)
- [704. Binary Search](https://leetcode-cn.com/problems/binary-search/)
- [34. Find First and Last Position of Element in Sorted Array](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
- [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [106. Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)



#### 二分法第一种写法

区间是左闭右闭的，也就是target定义在[left，right]

区间的定义就决定了代码应该怎么写了，因为定义在[left，right]区间中，就有了如下几个特点：

- while（left <= right） 这里就必须<=了，因为target是在左闭右闭的区间中的，left == right这种情况是有可能的。
- if (nums[middle] > target) right 要赋值为 middle - 1，这时候nums[middle]就不可能是target，那么区间的右边就可以缩短到middle-1。



```java
class Solution {
    public int search(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }
}
```

 

#### 二分法第二种写法

target是定义在区间[left,right)中的，也就是左闭右开的，那么对于边界处理的方式就完全不相同了。

- while(left<right),这里就不能加等于号了，target的下标是在区间[left,right)中的，循环判断的时候也要遵循这个规则。
- if (nums[middle] > target) right 更新为 middle，因为当前nums[middle]不等于target，去左区间继续寻找，而寻找区间是左闭右开区间，所以right更新为middle，即：下一个查询区间不会去比较nums[middle]



```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return -1;
    }
}
```

