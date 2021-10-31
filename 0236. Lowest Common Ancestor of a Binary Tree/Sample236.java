package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.TreeNode;

import java.util.Arrays;

/**
 * @Auther: lbgao
 * @Date: 2021/10/30 14:34
 */
class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ( root == p || root == q || root == null ) return root;

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if ( left != null && right != null ) return root;
        else if ( left == null && right != null ) return right;
        else return left;

    }
}
public class Sample236 {
    public static void main(String[] args) {
        //1,2,3,null,4
        Integer[] nums = new Integer[]{1,2,3,null,4};

        MyTree tree = new MyTree();
        tree.layerCreateTree(nums);

        TreeNode p = tree.findNodeByVal(tree.root,4);
        TreeNode q = tree.findNodeByVal(tree.root,3);

        System.out.println(new Solution236().lowestCommonAncestor(tree.root,p,q).val.toString());

    }
}
