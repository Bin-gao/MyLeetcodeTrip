package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;

/**
 * @Auther: lbgao
 * @Date: 2021/10/30 23:43
 */

class Solution669 {
    public TreeNode trimBST(TreeNode node, int low, int high) {
        if ( node == null )  return node;

        if ( (Integer) node.val < low ) {
            TreeNode right = trimBST(node.right,low,high);
            return right;
        }else if ( (Integer) node.val > high ) {
            TreeNode left = trimBST(node.left,low,high);
            return left;
        }

        node.left = trimBST(node.left,low,high);
        node.right = trimBST(node.right,low,high);

        return node;
    }

}

public class Sample669 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{4,0,5,null,2,null,null,1,3};
        int low = 3;
        int high = 4;

        MyTree tree = new MyTree();
        tree.layerCreateTree(nums);
        OrderReverse re = new OrderReverse();
        re.levelOrderReverse(new Solution669().trimBST(tree.root,low,high)).forEach(s -> System.out.print(s + " "));

    }
}
