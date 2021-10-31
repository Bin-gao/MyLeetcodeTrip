package com.lbgao.leetcode.tree;

import com.lbgao.tree.MyTree;
import com.lbgao.tree.OrderReverse;
import com.lbgao.tree.TreeNode;
import com.lbgao.tree.UniReverse;


/**
 * @Auther: lbgao
 * @Date: 2021/10/30 17:28
 */
class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        return traversal(root,key);
    }

    private TreeNode traversal(TreeNode node,int key) {
        if ( node == null ) return node;
        if ( (Integer) node.val == key ) {
            if ( node.right == null && node.left == null ) return null;
            if ( node.left != null && node.right == null ) return node.left;
            if ( node.left == null && node.right != null ) return node.right;
            if ( node.right != null && node.left != null ) {
                TreeNode leftNode = node.left;//删除节点的左节点
                TreeNode rightNode = node.right;//删除节点的右节点

                while ( rightNode.left != null ) {
                    rightNode = rightNode.left;
                }

                rightNode.left = leftNode;
                return node.right;
            }
        }

      node.left = traversal(node.left,key);
      node.right = traversal(node.right,key);
        return node;

    }

}
public class Sample450 {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{5,3,6,2,4,null,7};
        int key = 7;

        MyTree tree = new MyTree();
        tree.layerCreateTree(nums);

        UniReverse reverse = new UniReverse();
        reverse.inorderTraversal(new Solution450().deleteNode(tree.root, key)).stream().forEach(s -> System.out.print(s + " "));
        System.out.println();
        OrderReverse reverse1 = new OrderReverse();
        reverse1.levelOrderReverse(tree.root).stream().forEach(s -> System.out.print(s + " "));
    }
}
