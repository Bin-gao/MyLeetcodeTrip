## 701. Insert into a Binary Search Tree

------

给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。

实例：
![](https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg)
```
输入：root = [4,2,7,1,3], val = 5
输出：[4,2,7,1,3,5]
解释：另一个满足题目要求可以通过的树是：
```
![](https://assets.leetcode.com/uploads/2020/10/05/bst.jpg)

#### [力扣链接]（https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/）

## 分析

利用二叉查找树的特性，找到末尾节点插入即可

### 递归法

有返回值
```java
 public TreeNode insertIntoBST(TreeNode root, int val) {
        if ( root == null ) return new TreeNode(val);

        if ( (Integer)root.val > val ) root.left = insertIntoBST(root.left,val);
        if ( (Integer)root.val < val ) root.right = insertIntoBST(root.right,val);

        return root;
    }
```
无返回值

```c++
class Solution {
private:
    TreeNode* parent;
    void traversal(TreeNode* cur, int val) {
        if (cur == NULL) {
            TreeNode* node = new TreeNode(val);
            if (val > parent->val) parent->right = node;
            else parent->left = node;
            return;
        }
        parent = cur;
        if (cur->val > val) traversal(cur->left, val);
        if (cur->val < val) traversal(cur->right, val);
        return;
    }

public:
    TreeNode* insertIntoBST(TreeNode* root, int val) {
        parent = new TreeNode(0);
        if (root == NULL) {
            root = new TreeNode(val);
        }
        traversal(root, val);
        return root;
    }
};
```

