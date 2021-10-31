## 538. Convert BST to Greater Tree

------



#### [力扣链接](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/)

给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。

注意：本题和 1038:https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同

实例
![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/05/03/tree.png)

```
输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
```

## 分析

这是一颗二叉搜索树，二叉搜索树啊，这是有序的啊
找出最大的节点，往前加就可以了。

```java
 private void traversal(TreeNode node) {
        if ( node == null ) return;

        traversal(node.right);
        max += (Integer) node.val;
        node.val = max;
        traversal(node.left);

        return;

    }
```

