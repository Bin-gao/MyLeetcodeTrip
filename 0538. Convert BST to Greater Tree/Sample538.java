package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;

/**
 * @Auther: lbgao
 * @Date: 2021/10/31 10:26
 */
class Solution538 {
    private int max = 0;
    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }

    private void traversal(TreeNode node) {
        if ( node == null ) return;

        traversal(node.right);
        max += (Integer) node.val;
        node.val = max;
        traversal(node.left);

        return;

    }
}
public class Sample538 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{0,null,1};
        MyTree tree = new MyTree();
        tree.layerCreateTree(nums);

        OrderReverse reverse = new OrderReverse();
        reverse.levelOrderReverse(new Solution538().convertBST(tree.root)).forEach(s -> System.out.print(s + " "));

    }
}
