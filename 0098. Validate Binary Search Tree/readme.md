## 98. Validate Binary Search Tree

------

[力扣链接](https://leetcode-cn.com/problems/validate-binary-search-tree/)

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

![](https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg)
```
Input: root = [2,1,3]
Output: true
```

![](https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg)
```
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4
```

## 分析

要知道中序遍历下，输出的二叉搜索树节点的数值是有序序列。

有了这个特性，验证二叉搜索树，就相当于变成了判断一个序列是不是递增的了。

但其实不用转变成数组，可以在递归遍历的过程中直接判断是否有序。

```c++
class Solution {
public:
    TreeNode* pre = NULL; // 用来记录前一个节点
    bool isValidBST(TreeNode* root) {
        if (root == NULL) return true;
        bool left = isValidBST(root->left);

        if (pre != NULL && pre->val >= root->val) return false;
        pre = root; // 记录前一个节点

        bool right = isValidBST(root->right);
        return left && right;
    }
};
```