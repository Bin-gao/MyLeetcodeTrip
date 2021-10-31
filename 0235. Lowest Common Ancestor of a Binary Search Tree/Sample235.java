package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.TreeNode;

/**
 * @Auther: lbgao
 * @Date: 2021/10/30 16:39
 */

class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if ( p == root || q == root || root == null ) return root;
//        if ( (Integer)p.val < (Integer)root.val && (Integer)q.val > (Integer)root.val ) return root;
//        if ( (Integer)q.val < (Integer)root.val && (Integer)p.val > (Integer)root.val ) return root;

        if ( (Integer)p.val < (Integer)root.val && (Integer)q.val < (Integer)root.val ) {
            return lowestCommonAncestor(root.left,p,q);
        }
        if ( (Integer)p.val > (Integer)root.val && (Integer)q.val > (Integer)root.val ) {
            return lowestCommonAncestor(root.right,p,q);
        }

        return root;
    }
}
public class Sample235 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{6,2,8,0,4,7,9,null,null,3,5};
        MyTree tree = new MyTree();
        tree.createBST(nums);

        TreeNode p = tree.findNodeByVal(tree.root,8);
        TreeNode q = tree.findNodeByVal(tree.root,8);

        System.out.println(new Solution235().lowestCommonAncestor(tree.root,p,q).val.toString());

    }
}
