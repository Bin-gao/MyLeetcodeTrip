## 700. Search in a Binary Search Tree

------


#### [力扣链接](https://leetcode-cn.com/problems/search-in-a-binary-search-tree/)

You are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.

例1：
![例1](https://assets.leetcode.com/uploads/2021/01/12/tree1.jpg)

```
Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]
```

例2
![例2](https://assets.leetcode.com/uploads/2021/01/12/tree2.jpg)

```
Input: root = [4,2,7,1,3], val = 5
Output: []
```

二叉搜索树的遍历，二叉搜索树的节点是有序的，所以可以有方向的去搜索。
这里可能会疑惑，在递归遍历的时候，什么时候直接return 递归函数的返回值，什么时候不用加这个 return呢。

如果要搜索一条边，递归函数就要加返回值，这里也是一样的道理。

因为搜索到目标节点了，就要立即return了，这样才是找到节点就返回（搜索某一条边），如果不加return，就是遍历整棵树了。

```java
    //迭代法
    public TreeNode searchBST(TreeNode root, int val) {

        while ( root != null ){
            if ( (Integer) root.val > val )  root = root.left;
            else if ( (Integer)root.val < val ) root = root.right;
            else return root;
        }
        return null;

    }
    //递归法
    public TreeNode searchBST(TreeNode root, int val) {
        if ( root == null || root.val == (Integer)val ) return root;

        if ( (Integer)root.val > (Integer)val ) return searchBST(root.left,val);
        if ( (Integer)root.val < (Integer)val ) return searchBST(root.right,val);

        return null;
    }
```