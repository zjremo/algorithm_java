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

    public TreeNode buildTree(int[] nums, int l, int r){
        if (l == r){
            return new TreeNode(nums[l]);
        }

        if (l > r)
            return null;

        int mid = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = buildTree(nums, l, mid - 1);
        TreeNode right = buildTree(nums, mid + 1, r);
        root.left = left;
        root.right = right;
        return root;
    }
    // 将有序数组转换为二叉搜索树
    // 有序序列每次取中点，中点两侧的长度差必定小于等于1
    public TreeNode sortedArrayToBST(int[] nums) {
        // 中序遍历的结果 -> 有序序列
        return buildTree(nums, 0, nums.length - 1);
    }
}
