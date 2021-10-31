## 530. Minimum Absolute Difference in BST

------



#### [力扣链接](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)

Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

![](https://assets.leetcode.com/uploads/2021/02/05/bst1.jpg)
```
Input: root = [4,2,6,1,3]
Output: 1
```

## 分析
题目中要求在二叉搜索树上任意两节点的差的绝对值的最小值。

注意是二叉搜索树，二叉搜索树可是有序的。

遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了。

需要用一个pre节点记录一下cur节点的前一个节点。
![](https://img-blog.csdnimg.cn/20210204153247458.png)

递归中如何记录前一个节点的指针，其实实现起来是很简单的，大家只要看过一次，写过一次，就掌握了。

```c++
TreeNode* pre;
void traversal(TreeNode* cur) {
    if (cur == NULL) return;
    traversal(cur->left);   // 左
    if (pre != NULL){       // 中
        result = min(result, cur->val - pre->val);
    }
    pre = cur; // 记录前一个
    traversal(cur->right);  // 右
}
```
