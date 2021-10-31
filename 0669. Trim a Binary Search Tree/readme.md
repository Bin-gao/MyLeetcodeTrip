## 669. Trim a Binary Search Tree

------



####[力扣链接](https://leetcode-cn.com/problems/trim-a-binary-search-tree/)

给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。

所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。

实例
![](https://assets.leetcode.com/uploads/2020/09/09/trim2.jpg)

```
输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
输出：[3,2,null,1]
```



## 分析

遍历整棵树，如果当前节点的元素不符合条件（不在low和high范围内），删除改节点，同时

如果root（当前节点）的元素小于low的数值，那么应该递归右子树，并返回右子树符合条件的头结点。

如果root(当前节点)的元素大于high的，那么应该递归左子树，并返回左子树符合条件的头结点。


![](https://img-blog.csdnimg.cn/20210204155327203.png)

还是这个例子，此时节点0不符合条件，是小于low值的，这是0的左节点肯定都是不符合条件的（线索二叉树的特性），可以直接删除，不管它，我们只需返回右节点符合条件的子节点就可以了，而0的右节点的子节点就不一定了，可能小于low，可能大于low。
这时我们递归判断0节点的右节点，直到右节点为空就可以了。


```java
 if ( (Integer) node.val < low ) {
            TreeNode right = trimBST(node.right,low,high);
            return right;
 }else if ( (Integer) node.val > high ) {
            TreeNode left = trimBST(node.left,low,high);
            return left;
 }
```

其实这也是这道题的核心代码了。

