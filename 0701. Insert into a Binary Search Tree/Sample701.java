package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;
import com.lbgao.tree.UniReverse;

/**
 * @Auther: lbgao
 * @Date: 2021/10/30 17:09
 */

class Solution701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if ( root == null ) return new TreeNode(val);

        if ( (Integer)root.val > val ) root.left = insertIntoBST(root.left,val);
        if ( (Integer)root.val < val ) root.right = insertIntoBST(root.right,val);

        return root;
    }
}

public class Sample701 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{4,2,5,1};

        MyTree tree = new MyTree();
        tree.createBST(nums);

        new Solution701().insertIntoBST(tree.root,25);

        OrderReverse re = new OrderReverse();
        re.levelOrderReverse(tree.root).stream().forEach(s -> System.out.print(s + " "));

    }
}
