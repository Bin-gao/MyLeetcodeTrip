package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;
import com.lbgao.tree.UniReverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: lbgao
 * @Date: 2021/10/29 15:02
 */

class Solution501 {

    //递归
    private ArrayList<Integer> result;
    private TreeNode pre = null;
    private int count;
    private int maxCount;
    public int[] findMode(TreeNode root) {
        result = new ArrayList<>();
        searchBST(root);
        int[] res = new int[result.size()];
        for ( int i = 0; i < result.size(); i++ ) {
            res[i] = result.get(i);
        }
        return res ;
    }
//    递归
//    private void searchBST(TreeNode root) {
//        if ( root == null ) return;
//
//        searchBST(root.left);
//
//        Integer rootValue = (Integer) root.val;
//        // 计数
//        if (pre == null || rootValue != pre.val) {
//            count = 1;
//        } else {
//            count++;
//        }
//
//        pre = root;
//
//        if ( maxCount == count ) {
//            result.add((Integer) root.val);
//        }
//
//        if ( maxCount < count ){
//            maxCount = count;
//            result.clear();
//            result.add((Integer) root.val);
//        }
//
//        searchBST(root.right);
//    }
    //迭代
    public void searchBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while ( !stack.isEmpty() ) {
            TreeNode cur = stack.peek();
            if ( cur != null ) {
                stack.pop();

                if ( cur.right != null ) stack.push(cur.right);
                stack.push(cur);
                stack.push(null);
                if ( cur.left != null ) stack.push(cur.left);
            }else {
                stack.pop();
                cur = stack.pop();

                if ( pre == null ) {
                    count = 1;
                }else if ( pre.val != cur.val ) {
                    count = 1;
                }else {
                    count++;
                }

                pre = cur;

                if ( maxCount == count ) {
                    result.add((Integer) cur.val);
                }

                if ( count > maxCount ) {
                    maxCount = count;
                    result.clear();
                    result.add((Integer) cur.val);
                }
            }

        }
    }
}
public class Sample501 {
    public static void main(String[] args) {
        MyTree bst = new MyTree();
        Integer[] nums = new Integer[]{1,null,2};
        bst.createBST(nums);

        Arrays.stream(new Solution501().findMode(bst.root)).forEach(s -> System.out.print(s + " "));
    }
}
