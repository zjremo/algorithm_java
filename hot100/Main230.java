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
    // 二叉搜索树中查找第k小的元素
    // 方法一: 直接中序遍历，然后维护一个TreeSet
    public void centerSearch(TreeNode root, Set<Integer> set){
        if (root == null)
            return ;
        centerSearch(root.left, set);
        set.add(root.val);
        centerSearch(root.right, set);
    }
    public int kthSmallest(TreeNode root, int k) {
        Set<Integer> set = new TreeSet<>();
        centerSearch(root, set);

        // 然后取出第k个元素
        int cnt = 0;
        for (int val : set){
            ++cnt;
            if (cnt == k)
                return val; 
        }
        return -1;
    }

    // 方法二: 中序遍历二叉树得到的就是有序序列
    public int kthSmallest1(TreeNode root, int k) {
        // 用栈来模拟中序遍历，然后计数k不断减直到0
        Deque<TreeNode> s = new ArrayDeque<>();

        while (root != null || !s.isEmpty()){
            while (root != null){
                s.push(root.val);
                root = root.left;
            }

            root = s.pop();
            --k;
            if (k == 0)
                break;

            root = root.right;
        }

        return root.val;
    }

}
