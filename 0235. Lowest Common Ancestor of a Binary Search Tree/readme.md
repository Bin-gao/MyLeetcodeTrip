## 235. Lowest Common Ancestor of a Binary Search Tree

------



#### [力扣链接](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)

```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
```
## 分析

相比于二叉树：公共祖先问题，利用了回溯自底向上搜索，遇到了一个节点的左子树有q，右子树有p，那么当前节点就是最近公共祖先。

本题是二叉搜索树，二叉搜索树是有序的，那么得好好利用这个特点。

**其实只要从上到下遍历的时候，对于二叉搜索树是有序的，p，q如果不是在cur节点的同一个子树，那么这个节点就是最近公共祖先了。或者说，cur节点数值在[p, q]区间中则说明该节点cur就是最近公共祖先了。**

如图所示：p为节点3，q为节点5
![](https://img-blog.csdnimg.cn/20210204150858927.png)

可以看出直接按照指定的方向，就可以找到节点4，为最近公共祖先，而且不需要遍历整棵树，找到结果直接返回！

