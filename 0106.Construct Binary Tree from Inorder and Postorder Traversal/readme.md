## 106.Construct Binary Tree from Inorder and Postorder Traversal

------



#### [力扣链接](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

根据一棵树的中序遍历与后序遍历构造二叉树。

**注意:**
你可以假设树中没有重复的元素。

例如，给出

```
中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
```

返回如下的二叉树：

![结果](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

遵循循环不变量，参数左闭右开，也可以左闭右闭，但必须改递归时的参数。

**此时有一个很重的点，就是中序数组大小一定是和后序数组的大小相同的（这是必然）。**

```java
 // 切割中序数组
 // 左中序区间，左闭右开[leftInorderBegin, leftInorderEnd)
 int leftInorderBegin = inorderBegin;
 int leftInorderEnd = delimiterIndex;
 // 右中序区间，左闭右开[rightInorderBegin, rightInorderEnd)
 int rightInorderBegin = delimiterIndex + 1;
 int rightInorderEnd = inorderEnd;

// 切割后序数组
// 左后序区间，左闭右开[leftPostorderBegin, leftPostorderEnd)
int leftPostorderBegin =  postorderBegin;
int leftPostorderEnd = postorderBegin + delimiterIndex - inorderBegin; // 终止位置是 需要加上 中序区间的大小size

// 右后序区间，左闭右开[rightPostorderBegin, rightPostorderEnd)
int rightPostorderBegin = postorderBegin + (delimiterIndex - inorderBegin);
int rightPostorderEnd = postorderEnd - 1; // 排除最后一个元素，已经作为节点了
```



