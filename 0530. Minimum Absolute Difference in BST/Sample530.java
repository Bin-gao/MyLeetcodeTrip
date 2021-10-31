package com.lbgao.leetcode.tree;

import com.lbgao.tree.TreeNode;

import java.util.Stack;

/**
 * @Auther: lbgao
 * @Date: 2021/10/29 09:46
 */
class Solution530 {
    //递归
    private TreeNode pre;
    private int result = Integer.MAX_VALUE;
//    public int getMinimumDifference(TreeNode root) {
//        traversal(root);
//        return result;
//    }
//
//    private void traversal(TreeNode root) {
//        if ( root == null ) return;
//
//        traversal(root.left);
//
//        if ( pre != null ) {
//            result = Math.min(result,Math.abs((Integer)root.val-(Integer)pre.val));
//        }
//        pre = root;
//        traversal(root.right);
//    }

    //迭代
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        stack.push(root);

        while ( !stack.isEmpty() ) {
            TreeNode node = stack.peek();

            if ( node != null ) {
                stack.pop();

                //右
                if ( node.right != null ) stack.push(node.right);

                //中
                stack.push(node);
                stack.push(null);

                //左
                if ( node.left != null ) stack.push(node.left);
            }else {
                stack.pop();
                node = stack.pop();

                if ( pre != null ) {
                    result = Math.min(Math.abs((Integer) pre.val - (Integer) node.val),result);
                }
                pre = node;
            }
        }
        return result;
    }
}
public class Sample530 {
}
