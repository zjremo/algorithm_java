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
    // 二叉树展开为链表
    public void flatten(TreeNode root) {
        if (root == null)
            return ;

        if (root != null && root.left == null && root.right == null)
            return ;

        // 先序遍历，一个栈用来遍历，一个队列用来存结果
        Deque<TreeNode> searchDeque = new ArrayDeque<>();
        Queue<TreeNode> resQueue = new LinkedList<>();

        TreeNode node = root;
        while (node != null || !searchDeque.isEmpty()) {
            while (node != null) {
                resQueue.offer(node);
                searchDeque.push(node);
                node = node.left;
            }

            node = searchDeque.pop();
            node = node.right;
        }

        TreeNode n_root = resQueue.poll();
        TreeNode last_node = n_root;
        while(!resQueue.isEmpty()){
            TreeNode cur_node = resQueue.poll();
            last_node.right = cur_node;
            last_node.left = null;
            last_node = cur_node;
        }
    }
}
