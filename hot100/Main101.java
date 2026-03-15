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
    public boolean check(TreeNode p, TreeNode q){
        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
    // 检查轴对称
    // 方法一: 走相反的方向，然后检查元素是不是一样的 -> 根节点比较
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }


    public boolean isSymmetric1(TreeNode root) {
        if (root == null)
            return true;
        if (root != null && root.left == null && root.right == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode l = root.left, r = root.right;

        queue.offer(l);
        queue.offer(r);
        while (!queue.isEmpty()) {
            l = queue.poll();
            r = queue.poll();
            if (l == null && r == null){
                continue;
            }

            if ((l == null || r == null) || (l.val != r.val))
                return false;

            queue.offer(l.left);
            queue.offer(r.right);
            queue.offer(l.right);
            queue.offer(r.left);
        }

        return true;
    }

    // 方法二: 向下递归，左子树左对右子树右，左子树右对右子树左
    public boolean dfs(TreeNode leftRoot, TreeNode rightRoot){
        if (leftRoot == null && rightRoot == null)
            return true;

        if (leftRoot == null || rightRoot == null)
            return false;

        return dfs(leftRoot.left, rightRoot.right) && dfs(leftRoot.right, rightRoot.left) && leftRoot.val == rightRoot.val;
    }

    public boolean isSymmetric2(TreeNode root){
        if (root == null)
            return true;
        return dfs(root.left, root.right);
    }

}
