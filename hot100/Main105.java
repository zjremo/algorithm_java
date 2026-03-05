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
    // 根据前序遍历和后序遍历构建二叉树
    // 前序遍历: (root, left, right)
    // 中序遍历: (left, root, right)
    // 前序遍历的第一个元素必定是root，然后去中序遍历的左右可以确定左右子树
    // 同时确定前序遍历的左右子树序列，每次可以锁死一个root，从而建立一个root
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 依据前序遍历找到root，然后切分中序遍历数组
        if (preorder.length == 0 && inorder.length == 0)
            return null;

        TreeNode root = new TreeNode(preorder[0]);
        int idx = 0;
        for (int i = 0; i < inorder.length; ++i){
            if (inorder[i] == preorder[0]){
                idx = i;
                break;
            }
        }

        // 切分左右子树，左子树preorder的范围，右子树inorder的范围
        int[] leftpreOrder = new int[idx];
        int[] rightpreOrder = new int[preorder.length - idx - 1];
        int[] leftinOrder = new int[idx];
        int[] rightinOrder = new int[preorder.length - idx - 1];

        // 拷贝
        System.arraycopy(preorder, 1, leftpreOrder, 0, idx);
        System.arraycopy(preorder, 1 + idx, rightpreOrder, 0, preorder.length - idx - 1);
        System.arraycopy(inorder, 0, leftinOrder, 0, idx);
        System.arraycopy(inOrder, idx + 1, rightinOrder, 0, preorder.length - idx - 1);
        root.left = buildTree(leftpreOrder, leftinOrder);
        root.right = buildTree(rightpreOrder, rightinOrder);
        return root;
    }

    // 方法二: 每次都要拷贝数组太麻烦了，完全可以用双指针来确定
    // 每次都要遍历来确定inorder中的root索引太麻烦了，完全可以建立个map存储
    private Map<Integer, Integer> map;

    public TreeNode buildTree1(int[] preorder, int[] inorder, int leftpre, int rightpre, int leftin, int rightin){
        // 此时没有元素可以用来构建节点
        if (leftpre > rightpre)
            return null;

        // 定位inorder节点
        TreeNode root = new TreeNode(preorder[leftpre]);
        int idx = map.get(preorder[leftpre]);

        int left_subtree_size = idx - leftin;
        
        // leftpreOrder leftpre + 1 -> leftpre + idx - leftpre = leftpre + left_subtree_size
        // rightpreOrder leftpre + left_subtree_size + 1 -> rightpre
        // leftinOrder leftin -> leftin + left_subtree_size - 1
        // rightinOrder idx + 1 -> rightin
        root.left = buildTree1(preorder, inorder, leftpre + 1, leftpre + left_subtree_size, leftin, leftin + left_subtree_size - 1);
        root.right = buildTree1(preorder, inorder, leftpre + left_subtree_size + 1, rightpre, idx + 1, rightin);
        return root;
    }

    public TreeNode buildTree1(int[] preorder, int[] inorder){
        map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; ++i)
            map.put(inorder[i], i);
        return buildTree1(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
