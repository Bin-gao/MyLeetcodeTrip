package com.lbgao.leetcode.tree;

import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;

/**
 * @Auther: lbgao
 * @Date: 2021/10/27 13:34
 */


class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if ( preorder.length == 0 || inorder.length == 0 ) return null;
        return traversal(preorder,0,preorder.length,inorder,0,inorder.length);
    }

    private TreeNode traversal(int[] preorder,int preorderBegin,int preorderEnd,
                               int[] inorder,int inorderBegin,int inorderEnd) {
        if ( preorderEnd - preorderBegin == 0 ) return null;

        if ( preorderEnd - preorderBegin == 1 ) {
            return new TreeNode(preorder[preorderBegin]);
        }


        int delimiterIndex;
        int rootVal = preorder[preorderBegin];
        TreeNode root = new TreeNode(rootVal);
        for ( delimiterIndex = 0 ; delimiterIndex < inorder.length ; delimiterIndex++ ) {
            if ( inorder[delimiterIndex] == rootVal ){
                break;
            }
        }

        int leftInorderBegin = inorderBegin;
        int leftInorderEnd = delimiterIndex;
        int leftPreorderBegin = preorderBegin+1;
        int leftPreorderEnd = leftPreorderBegin + delimiterIndex - inorderBegin;


        int rightInorderBegin = delimiterIndex + 1;
        int rightInordeEnd = inorderEnd;
        int rightPreorderBegin = leftPreorderBegin + delimiterIndex - inorderBegin;
        int rightPreorderEnd = preorderEnd;


        root.left = traversal(preorder,leftPreorderBegin,leftPreorderEnd,
                              inorder,leftInorderBegin,leftInorderEnd);
        root.right = traversal(preorder,rightPreorderBegin,rightPreorderEnd,
                              inorder,rightInorderBegin,rightInordeEnd);
        return root;
    }
}
public class Sample105 {
    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        Solution105 solution105 = new Solution105();
        TreeNode root = solution105.buildTree(preorder,inorder);

        OrderReverse reverse = new OrderReverse();
        reverse.levelOrderReverse(root).forEach(s -> System.out.print(s + " "));


    }
}
