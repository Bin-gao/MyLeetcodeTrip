package com.lbgao.leetcode.tree;

import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;

import java.util.Arrays;

/**
 * @Auther: lbgao
 * @Date: 2021/10/27 09:03
 */

class Solution106 {
    public TreeNode buildTree(int[] inorder,int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        // 左闭右开的原则
        return traversal(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode traversal(int[] inorder,int inorderBegin,int inorderEnd,
                               int[] postorder,int postorderBegin,int postorderEnd) {

        if ( postorderBegin == postorderEnd ) return null;

        if ( postorder.length == 1 ) {
            return new TreeNode(postorder[postorderEnd-1]);
        }

        int rootValue = postorder[postorderEnd-1];
        TreeNode root = new TreeNode(rootValue);

        int delimiterIndex;
        for (  delimiterIndex = inorderBegin ; delimiterIndex < inorderEnd ; delimiterIndex++ ) {
            if ( rootValue == inorder[delimiterIndex] ) {
                break;
            }
        }
        // 切割中序数组
        // 左中序区间，左闭右开[leftInorderBegin, leftInorderEnd)
        int leftInorderBegin = inorderBegin;
        int leftInorderEnd = delimiterIndex;
        // 右中序区间，左闭右开[rightInorderBegin, rightInorderEnd)
        int rightInorderBegin = delimiterIndex + 1;
        int rightInorderEnd = inorderEnd;

        // 切割后序数组
        // 左后序区间，左闭右开[leftPostorderBegin, leftPostorderEnd)
        int leftPostorderBegin =  postorderBegin;
        int leftPostorderEnd = postorderBegin + delimiterIndex - inorderBegin; // 终止位置是 需要加上 中序区间的大小size
        // 右后序区间，左闭右开[rightPostorderBegin, rightPostorderEnd)
        int rightPostorderBegin = postorderBegin + (delimiterIndex - inorderBegin);
        int rightPostorderEnd = postorderEnd - 1; // 排除最后一个元素，已经作为节点了

        root.left = traversal(inorder,leftInorderBegin,leftInorderEnd,
                              postorder,leftPostorderBegin,leftPostorderEnd);
        root.right = traversal(inorder,rightInorderBegin,rightInorderEnd,
                              postorder,rightPostorderBegin,rightPostorderEnd);

        return root;

    }
}
public class Sample106 {
    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};

        TreeNode root = new Solution106().buildTree(inorder, postorder);
        OrderReverse reverse = new OrderReverse();

        reverse.levelOrderReverse(root).forEach(s -> System.out.print(s + " "));

    }
}
