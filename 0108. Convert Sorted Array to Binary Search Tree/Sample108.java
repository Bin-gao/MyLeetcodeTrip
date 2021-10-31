package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;


/**
 * @Auther: lbgao
 * @Date: 2021/10/31 00:03
 */
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums,0,nums.length);
    }

    private TreeNode traversal(int[] nums,int left,int right) {
        if ( right == left ) return null;
        if ( right - left == 1 ) return new TreeNode(nums[left]);

        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);

        root.left = traversal(nums,left,mid);
        root.right = traversal(nums,mid+1,right);

        return root;
    }
}
public class Sample108 {
    public static void main(String[] args) {
        TreeNode root = new Solution108().sortedArrayToBST(new int[]{-10,-3,0,5,9});


        OrderReverse reverse = new OrderReverse();
        reverse.levelOrderReverse(root).forEach(s -> System.out.print(s + " "));

    }
}
