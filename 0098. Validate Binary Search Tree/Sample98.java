package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.TreeNode;

/**
 * @Auther: lbgao
 * @Date: 2021/10/28 23:21
 */

class Solution98 {
    private TreeNode maxNode;
    public boolean isValidBST(TreeNode root) {
        return traversal(root);
    }

    private boolean traversal(TreeNode root) {
       if ( root == null ) return true;

       boolean left = traversal(root.left);
       if ( !left ) return false;

       if ( maxNode != null && (Integer)root.val <= (Integer) maxNode.val ) return false;
       maxNode = root;

       boolean right = traversal(root.right);
       return right;

    }
}
public class Sample98 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{5,1,4,null,null,3,6};
        MyTree tree = new MyTree();

        tree.layerCreateTree(nums);
        System.out.println(new Solution98().isValidBST(tree.root));

    }

}
