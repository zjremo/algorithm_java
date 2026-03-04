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
    int ans;
    public int getDistance(TreeNode root){
        if (root == null){
            return 0;
        }

        int left_max = getDistance(root.left);
        int right_max = getDistance(root.right);
        ans = Math.max(left_max + right_max, ans); 
        return Math.max(left_max, right_max) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        getDistance(root);
        return ans;
    }
}

