/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public Deque<TreeNode> postorderTravel(TreeNode root, TreeNode p){
        Deque<TreeNode> stack = new ArrayDeque<>();

        if (root == null)
            return stack;

        TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode node = stack.peek();
            if (node.right == null || prev == node.right){
                if (node == p){
                    break;
                }
                stack.pop();
                prev = node;
            } else{
                // 右子树没有被访问
                root = node.right;
            }
        }

        // 栈反转
        Deque<TreeNode> res = new ArrayDeque<>();
        while (!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            res.push(tmp);
        }

        return res;
    }

    // 方法一: 使用栈的后续遍历，中途进行栈反转，然后取公共部分
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 遍历两次，得到两个序列，然后取公共位置
        Deque<TreeNode> s1 = postorderTravel(root, p);
        Deque<TreeNode> s2 = postorderTravel(root, q);

        if (s1.isEmpty() || s2.isEmpty()){
            return null;
        }

        TreeNode res = null;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode t1 = s1.pop(), t2 = s2.pop();
            if (t1 == t2)
                res = t1;
        }
        return res;
    }

    // 方法二: 存储父节点，使用哈希表存储节点的父节点，然后一直向上跳
    private Map<TreeNode, TreeNode> parent = new HashMap<>();
    private Set<TreeNode> visited = new HashSet<>();

    public void dfs(TreeNode root){
        if (root == null)
            return;

        if (root.left != null){
            parent.put(root.left, root);
            dfs(root.left);
        }

        if (root.right != null){
            parent.put(root.right, root);
            dfs(root.right);
        }

    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        
        while (p != null){
            visited.add(p);
            p = parent.get(p);
        }

        while (q != null){
            if (visited.contains(q))
                return q;
            q = parent.get(q);
        }

        return null;
    }
}
