## 108. Convert Sorted Array to Binary Search Tree

-----



#### [力扣链接](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)

给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

![](https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg)

```
输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
```
![](https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg)

## 分析

题目中说要转换为一棵高度平衡二叉搜索树。这和转换为一棵普通二叉搜索树有什么差别呢？

其实这里不用强调平衡二叉搜索树，数组构造二叉树，构成平衡树是自然而然的事情，因为大家默认都是从数组中间位置取值作为节点元素，一般不会随机取，所以想构成不平衡的二叉树是自找麻烦。

在二叉树：构造二叉树登场！ (opens new window)和二叉树：构造一棵最大的二叉树 (opens new window)中其实已经讲过了，如果根据数组构造一颗二叉树。

本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间。

本题其实要比二叉树：构造二叉树登场！ (opens new window)和 二叉树：构造一棵最大的二叉树 (opens new window)简单一些，因为有序数组构造二叉搜索树，寻找分割点就比较容易了。

分割点就是数组中间位置的节点。

那么为问题来了，如果数组长度为偶数，中间节点有两个，取哪一个？

取哪一个都可以，只不过构成了不同的平衡二叉搜索树。

例如：输入：[-10,-3,0,5,9]

如下两棵树，都是这个数组的平衡二叉搜索树：

![](https://code-thinking.cdn.bcebos.com/pics/108.%E5%B0%86%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84%E8%BD%AC%E6%8D%A2%E4%B8%BA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.png)

如果要分割的数组长度为偶数的时候，中间元素为两个，是取左边元素 就是树1，取右边元素就是树2。

这也是题目中强调答案不是唯一的原因。 理解这一点，这道题目算是理解到位了。

```java
private TreeNode traversal(int[] nums,int left,int right) {
        if ( right == left ) return null;
        if ( right - left == 1 ) return new TreeNode(nums[left]);

        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);

        root.left = traversal(nums,left,mid);
        root.right = traversal(nums,mid+1,right);

        return root;
    }
```