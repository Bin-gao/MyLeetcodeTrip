## 450. Delete Node in a BST

------



#### [力扣链接](https://leetcode-cn.com/problems/delete-node-in-a-bst)

给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

实例
![](https://assets.leetcode.com/uploads/2020/09/04/del_node_1.jpg)
```
输入：root = [5,3,6,2,4,null,7], key = 3
输出：[5,4,6,2,null,null,7]
解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
另一个正确答案是 [5,2,6,null,4,null,7]。
```
![](https://assets.leetcode.com/uploads/2020/09/04/del_node_supp.jpg)



## 分析

比起插入，删除会复杂很多，这是因为插入找到符合条件叶子节点就可以了，**不涉及到结构的调整**，而删除的位置可以是叶子节点，也可以不是，**而不是叶子节点的操作可能涉及到结构的调整**。

一般有五种情况：

- 第一种情况：没找到删除的节点，遍历到空节点直接返回了
- 找到删除的节点
  - 第二种情况 删除的节点是叶子节点，左右为空，则返回null为根节点
  - 第三种情况 删除的节点左孩子为空，右孩子不为空，则返回右孩子为根节点
  - 第四种情况 删除的节点右孩子为空，左孩子不为空，则返回左孩子为根节点
  - 第五种情况 删除的节点左右孩子都不为空，将左孩子作为右孩子最左边叶子节点的左孩子就可以了，返回右孩子为根节点，

第五种情况有点难理解，看下面动画：
![](https://tva1.sinaimg.cn/large/008eGmZEly1gnbj3k596mg30dq0aigyz.gif)

动画中颗二叉搜索树中，删除元素7， 那么删除节点（元素7）的左孩子就是5，删除节点（元素7）的右子树的最左面节点是元素8。

将删除节点（元素7）的左孩子放到删除节点（元素7）的右子树的最左面节点（元素8）的左孩子上，就是把5为根节点的子树移到了8的左孩子的位置。

要删除的节点（元素7）的右孩子（元素9）为新的根节点。.

```java
 private TreeNode traversal(TreeNode node,int key) {
        if ( node == null ) return node;
        if ( (Integer) node.val == key ) {
        	//第二种情况
            if ( node.right == null && node.left == null ) return null;
            //第三种情况
            if ( node.left != null && node.right == null ) return node.left;
            //第四种情况
            if ( node.left == null && node.right != null ) return node.right;
            //第五种情况
            if ( node.right != null && node.left != null ) {
                TreeNode leftNode = node.left;//删除节点的左节点
                TreeNode rightNode = node.right;//删除节点的右节点
				
                //找到右节点最左的叶子节点
                while ( rightNode.left != null ) {
                    rightNode = rightNode.left;
                }
				//删除节点的左节点作为最左的叶子节点的左孩子
                rightNode.left = leftNode;
                return node.right;
            }
        }

      node.left = traversal(node.left,key);
      node.right = traversal(node.right,key);
        return node;

    }
```

## 普通二叉树的删除方式

二叉树通用的删除方式（没有使用搜索树的特性，遍历整棵树），用交换值的操作来删除目标节点。

代码中目标节点（要删除的节点）被操作了两次：

- 第一次是和目标节点的右子树最左面节点交换。
- 第二次直接被NULL覆盖了

```c++
class Solution {
public:
    TreeNode* deleteNode(TreeNode* root, int key) {
        if (root == nullptr) return root;
        if (root->val == key) {
            if (root->right == nullptr) { // 这里第二次操作目标值：最终删除的作用
                return root->left;
            }
            TreeNode *cur = root->right;
            while (cur->left) {
                cur = cur->left;
            }
            swap(root->val, cur->val); // 这里第一次操作目标值：交换目标值其右子树最左面节点。
        }
        root->left = deleteNode(root->left, key);
        root->right = deleteNode(root->right, key);
        return root;
    }
};
```

