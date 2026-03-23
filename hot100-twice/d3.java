class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        visitCenter(list, root);
        return list;
    }

    public void visitCenter(List<Integer> list, TreeNode root){
        if (root == null){
            return ;
        }

        visitCenter(list, root.left);
        list.add(root.val);
        visitCenter(list, root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSubSymmetric(root.left, root.right);
    }

    public boolean isSubSymmetric(TreeNode r1, TreeNode r2){
        if (r1 == null && r2 == null)
            return true;
        if (r1 == null || r2 == null)
            return false;

        if (r1.val != r2.val)
            return false;
        return isSubSymmetric(r1.left, r2.right) && isSubSymmetric(r1.right, r2.left);
    }

    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getMaxDiameter(root);
        return ans;
    }

    public int getMaxDiameter(TreeNode root){
        if (root == null)
            return 0;
        int leftMax = getMaxDiameter(root.left);
        int rightMax = getMaxDiameter(root.right);
        ans = Math.max(ans, leftMax + rightMax);
        return Math.max(leftMax, rightMax) + 1;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        List<List<Integer>> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; ++i){
                TreeNode node = q.poll();
                tmp.add(node.val);
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int l, int r){
        if (l > r){
            return null;
        }

        int mid = ((r - l) >> 1) + l;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = buildTree(nums, l, mid - 1);
        TreeNode right = buildTree(nums, mid + 1, r);
        root.left = left;
        root.right = right;
        return root;
    }

    // 左子树 -> 寻找最大的数
    // 右子树 -> 寻找最小的数
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean isValidBST(TreeNode root, long max, long min){
        if (root == null){
            return true;
        }

        if (root.val <= min || root.val >= max){
            return false;
        }

        boolean left = isValidBST(root.left, root.val, min);
        boolean right = isValidBST(root.right, max, root.val);
        return left && right;
    }

    // 就是一个中序遍历
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        int cnt = 0;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            ++cnt;
            if (cnt == k){
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; ++i){
                TreeNode node = queue.poll()
                if (i == 0){
                    list.add(node.val);
                }

                if (node.right != null){
                    queue.add(node.right);
                }

                if (node.left != null){
                    queue.add(node.left);
                }
            }
        }
        return list;
    }

    // 额外空间复杂度为O(N)
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrderTraverse(root, list);

        TreeNode prev_head = new TreeNode(), prev = prev_head;
        for (TreeNode node : list){
            prev.right = node;
            node.left = null;
            prev = prev.right;
        }
        prev.right = null;
    }

    public void preOrderTraverse(TreeNode root, List<TreeNode> list){
        if (root == null)
            return;
        list.add(root);
        preOrderTraverse(root.left, list);
        preOrderTraverse(root.right, list);
    }

    // 额外空间复杂度为O(1) 莫里斯遍历
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null){
            if (cur.left != null){
                TreeNode next = cur.left;
                TreeNode predecessor = next;
                while (predecessor.right != null){
                    predecessor = predecessor.right;
                }
                predecessor.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }

    // 3 9 20 15 7 pre  -> root
    // 9 3 15 20 7  -> split left tree and right tree
    private Map<Integer ,Integer> map = new HashMap<>(); // value -> inorder_index
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length)
            return null;

        int n = inorder.length;
        for (int i = 0; i < n; ++i){
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, n - 1, 0, n - 1);

    }

    public TreeNode buildTree(int[] preorder, int lpre, int rpre, int lin, int rin){
        if (lpre > rpre) // 无法划分了
            return null;

        int treeSize = rpre - lpre + 1;
        int root_val = preorder[lpre];
        // 建立root节点
        TreeNode root = new TreeNode(root_val);
        // 定位inorder中的index
        int rootIdx = map.get(root_val);
        int leftTreeSize = rootIdx - lin;
        /*
         * left tree pre: lpre + 1 -> lpre + leftTreeSize
         * left tree in : lin -> rootIdx - 1
         * right tree pre； lpre + leftTreeSize + 1 -> rpre
         * right tree in : rootIdx + 1 -> rin - 1
         */
        TreeNode left = buildTree(preorder, lpre + 1, lpre + leftTreeSize, lin, rootIdx - 1);
        TreeNode right = buildTree(preorder, lpre + leftTreeSize + 1, rpre, rootIdx + 1, rin - 1);
        root.left = left;
        root.right = right;
        return root;
    }

    private Map<Long ,Integer> map = new HashMap<>(); // 前缀和 -> cnt
    private int ans = 0;

    public int pathSum(TreeNode root, int targetSum) {
        map.put(0L, 1L);
        dfs(root, 0L, targetSum);
        return ans;
    }

    public void dfs(TreeNode root , long cur, long targetSum){
        if (root == null)
            return ;

        cur += root.val;
        ans += map.getOrDefault(cur - targetSum, 0);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        dfs(root.left, cur, targetSum);
        dfs(root.right, cur, targetSum);
        map.put(cur, map.getOrDefault(cur, 0) - 1);
    }
}
