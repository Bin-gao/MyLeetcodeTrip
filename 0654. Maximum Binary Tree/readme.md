## 654. Maximum Binary Tree

------

#### [力扣链接](https://leetcode-cn.com/problems/maximum-binary-tree/)

给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：

1.二叉树的根是数组 nums 中的最大元素。
2.左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
3.右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。

返回有给定数组 nums 构建的 最大二叉树 。

![](https://assets.leetcode.com/uploads/2020/12/24/tree1.jpg)

## 分析

这道题目其实和 [0106.Construct Binary Tree from Inorder and Postorder Traversal](https://github.com/gg-dot/MyLeetcodeTrip/tree/master/106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal)是一个思路，比[0106.Construct Binary Tree from Inorder and Postorder Traversal](https://github.com/gg-dot/MyLeetcodeTrip/tree/master/106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal)还简单一些。



最大二叉树的构建过程如下：
![](https://tva1.sinaimg.cn/large/008eGmZEly1gnbjuvioezg30dw0921ck.gif)

构造树一般采用的是前序遍历，因为先构造中间节点，然后递归构造左子树和右子树。



```java
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree1(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree1(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 1) {// 没有元素了
            return null;
        }
        if (rightIndex - leftIndex == 1) {// 只有一个元素
            return new TreeNode(nums[leftIndex]);
        }
        int maxIndex = leftIndex;// 最大值所在位置
        int maxVal = nums[maxIndex];// 最大值
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxVal){
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // 根据maxIndex划分左右子树
        root.left = constructMaximumBinaryTree1(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree1(nums, maxIndex + 1, rightIndex);
        return root;
    }
}
```







