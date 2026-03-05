/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public boolean isValidBST(TreeNode node, long lower, long upper){
        if (node == null)
            return true;

        if (node.val <= lower || node.val >= upper)
            return false;

        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
    
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        } 

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }
}
