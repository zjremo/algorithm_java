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
    public int rootSum(TreeNode root, long targetSum){
        if (root == null)
            return 0;

        int res = 0;
        if (root.val == targetSum)
            ++res;
        res += rootSum(root.left, targetSum - root.val);
        res += rootSum(root.right, targetSum - root.val);
        return res;
    }

    // 路径之和
    // 方法一：直接向下暴力递归
    public int pathSum(TreeNode root, long targetSum) {
        if (root == null)
            return 0;
        int res = 0;

        // 包含根节点
        res += rootSum(root, targetSum);
        // 不包含根节点
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    // 方法二: 前缀和 + 哈希表
    // prefix[i] = prefix[j] + targetSum -> 必有一个前缀和满足
    public int pathSum1(TreeNode root, long targetSum){
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0L, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, long targetSum){
        if (root == null)
            return 0;

        int ret = 0;
        curr += root.val;

        // 查看当前的哈希表
        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        // 一定要注意清除掉当前路径到此节点的前缀和，不然会影响其他路径
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

}
