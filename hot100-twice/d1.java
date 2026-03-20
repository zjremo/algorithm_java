class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map= new HashMap<>();

        int n = nums.length;

        for (int i = 0; i < n; ++i){
            if (map.containsKey(target - nums[i])){
                return new int[]{
                    i, map.get(target - nums[i])
                };
            } else{
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public List<List<String>> groupAnagrams(String[] strs){
        Map<String, List<String>> map = new HashMap<>();

        for (String str: strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);

            String key = Arrays.toString(arr);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return map.values().stream().toList();
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num: nums){
            num_set.add(num);
        }

        int longestStreak = 0;
        for (int num : num_set){
            if (!num_set.contains(num - 1)){
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)){
                    currentStreak += 1;
                    currentNum += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    public void moveZeroes(int[] nums) {
        // 0 1 0 3 12
        // 1 3 0 0 12
        int l = 0, r = 0, n = nums.length;
        while(r < n){
            if (nums[r] != 0){
                swap(nums, l++, r);
            }         
            ++r;
        }

    }

    public void swap(int[] arr, int l, int r){
        if (l == r)
            return ;
        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

    public int maxArea(int[] height){
        int n = height.length;

        int l = 0, r = n - 1;
        int max = 0;
        while (l < n){
            int sum = (r - l) * Math.min(height[l], height[r]);

            max = Math.max(sum, max);
            int next = height[l] < height[r] ? l : r;
            if (next == l){
                ++l;
            } else{
                --r;
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums){
        // -1 0 1 2 -1 -4
        // -4 -1 -1 0 1 2
        int n = nums.length;

        Arrays.sort(nums); // 升序
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < n; ++i){
            if (i != 0 && nums[i] == nums[i - 1]){
                // 此时出现重复
                continue;
            }
            
            if (nums[i] > 0){ // 和已经超了
                break;
            }

            int target = 0 - nums[i];
            // 两数之和 = target
            int l = i + 1, r = n - 1;
            while (l < r){
                int sum = nums[l] + nums[r];
                if (sum == target){
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    ++l;
                    --r;
                    // 移动到不等的位置
                    for (; l < n - 1 && nums[l] == nums[l - 1]; ++l);
                    for (; r > l && nums[r] == nums[r + 1]; --r);
                } else if (sum < target){
                    ++l;
                } else{
                    --r;
                }
            }
        }

        return res;

    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;

        Set<Character> set = new HashSet<>();
        int max = 1;

        int n = s.length(), l = 0, r = 0;
        while (r < n){
            if (!set.contains(s.charAt(r))){
                set.add(s.charAt(r));
                max = Math.max(max, r - l + 1);
                ++r;
            }
            else { // 重复了
                set.remove(s.charAt(l));
                ++l;
            }
        }
        return max;
    }

    public List<Integer> findAnagrams(String s, String p){
        int len = p.length(), n = s.length();

        if (len > n){
            return new ArrayList<>();
        }

        int[] target = new int[26];
        for (char c: p.toCharArray()){
            target[c - 'a'] += 1;
        }

        int[] compare = new int[26];
        for (int i = 0; i < len; ++i){
            compare[s.charAt(i) - 'a'] += 1;
        }

        int l = 0, r = len - 1;
        List<Integer> res = new ArrayList<>();
        while (r < n){
            if (Arrays.equals(compare, target)){
                res.add(l);
            }
            // 前推 + update
            if (r == n -1)
                break;
            compare[s.charAt(++r) - 'a'] += 1;
            compare[s.charAt(l++) - 'a'] -= 1;
        }
        return res;

    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int cnt = 0, cur = 0;
        map.put(0, 1);
        for (int i = 0; i < n; ++i){
            cur += nums[i];
            if (map.containsKey(cur - k)){
                cnt += map.get(cur - k);
            }
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return cnt;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int n = nums.length;

        int[] dp = new int[n];

        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; ++i){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 1)
            return intervals;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return Integer.compare(t1[0], t2[0]);
            }
        });

        List<List<Integer>> ans = new ArrayList<>(n);

        List<Integer> cur = Arrays.asList(intervals[0][0], intervals[0][1]);

        for (int i = 1; i < n; ++i){
            if (cur.get(1) >= intervals[i][0]){
                cur = Arrays.asList(cur.get(0), Math.max(cur.get(1), intervals[i][1]));
            }else{
                ans.add(cur);
                cur = Arrays.asList(intervals[i][0], intervals[i][1]);
            }
        }
    
        // 把cur最后加入进来
        ans.add(cur);

        // 拷贝数组
        int[][] arr = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); ++i){
            arr[i] = new int[2];
            arr[i][0] = ans.get(i).get(0);
            arr[i][1] = ans.get(i).get(1);
        }
        return arr;
    }

    public void rotate(int[] nums, int k){
        int n = nums.length;
        if (n == 1)
            return ;

        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void swap(int[] nums, int l, int r){
        if (l == r){
            return ;
        }
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }

    public void reverse(int[] nums, int l, int r){
        while (l <= r){
            swap(nums, l++, r--);
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prev = new int[n];
        int[] back = new int[n];

        int cur = 1;
        for (int i = 0; i < n; ++i){
            prev[i] = cur;
            cur *= nums[i];
        }

        cur = 1;
        for (int i = n - 1; i >= 0; --i){
            back[i] = cur;
            cur *= nums[i];
        }

        return IntStream.range(0, n).map(v -> prev[v] * back[v]).toArray();
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // 第0行和第0列
        boolean flagRow = false, flagCol = false;
        for (int i = 0; i < m; ++i){
            if (matrix[i][0] == 0){
                flagRow = true;
                break;
            }
        }

        for (int i = 0; i < n; ++i){
            if (matrix[0][i] == 0){
                flagCol = true;
                break;
            }
        }

        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (flagRow){
            for (int i = 0; i < m; ++i)
                matrix[i][0] = 0;
        }

        if (flagCol){
            for (int i = 0; i < n; ++i)
                matrix[0][i] = 0;
        }

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] directions = new int[][]{
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int direction = 0;

        List<Integer> res = new ArrayList<>();
        int r = 0, c = 0;

        boolean[][] traverse = new boolean[m][n];
        traverse[0][0] = true;
        res.add(matrix[0][0]);
        while (true){
            // 开始调整方向
            boolean find = false;
            int tmp_direction = direction;

            int trys = 0;
            while (!find){
                int tmp_r = directions[tmp_direction][0] + r;
                int tmp_c = directions[tmp_direction][1] + c];
                if (tmp_r >=0 && tmp_r < m && tmp_c >= 0 && tmp_c <n){
                    find = true;
                    r = tmp_r;
                    c = tmp_c;
                    traverse[r][c] = true;
                    res.add(matrix[r][c]);
                    direction = tmp_direction;
                }
                tmp_direction = (tmp_direction + 1) % 4;
                if (++trys == 4)
                    break;
            }
            if (!find)
                break;
        }

        return res;
    }

    public void rotate(int[][] matrix){
        int n = matrix.length;

        // 对角线对折，然后每行反转
        for (int i = 0; i < n; ++i){
            for (int j = i; j < n; ++j){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for (int i = 0; i < n; ++i){
            reverse(matrix[i], 0, n - 1);
        }
    }

    public void reverse(int[] arr, int l, int r){
        while (l < r){
            arr[l] ^= arr[r];
            arr[r] ^= arr[l];
            arr[l] ^= arr[r];
            ++l;
            --r;
        }
    }

    public boolean searchMatrix(int[][] matrix, int target){
        // 每一个行直接二分查找
        int m = matrix.length;
        for (int i = 0; i < m; ++i){
            int val = selectInsert(matrix[i], target);
            if (target == val)
                return true;
        } 

        return false;
    }

    public int selectInsert(int[] arr, int target){
        int m = arr.length;

        int l = 0, r = m - 1;
        while (l < r){
            int mid = l + ((r - l) >> 1);
            if (arr[mid] < target){
                l = mid + 1;
            }
            else if (arr[mid] == target){
                return arr[mid];
            }
            else {
                r = mid - 1;
            }
        }
        return arr[l];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode p1 = headA, p2 = headB;
        while (p1 != p2){
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, p = head;
        while (p != null){
            ListNode tmp = p.next;
            p.next = prev;
            prev = p;
            p = tmp;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return false;
        if (head.next == null)
            return true;

        ListNode s = head, f = head;

        while (f.next != null && f.next.next != null){
            f = f.next.next;
            s = s.next;
        }

        // 反转s之后的链表
        f = s.next;
        ListNode tailHead = reverseList(f);

        ListNode p1 = head, p2 = tailHead;
        boolean isTrue = true;
        while (p1 != null && p2 != null){
            if (p1.val != p2.val){
                isTrue = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 反转回去
        reverseList(tailHead);
        return isTrue;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null){
            return false;
        }

        ListNode s = head, f = head;
        boolean cycle = false;
        while (f.next != null && f.next.next != null){
            f = f.next.next;
            s = s.next;
            if (f == s){
                cycle = true;
                break;
            }
        }
        return cycle;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;

        ListNode s = head, f = head;
        boolean cycle = false;
        while (f.next != null && f.next.next != null){
            f = f.next.next;
            s = s.next;
            if (f == s){
                // 证明此时有环
                cycle = true;
                break;
            }
        }
        if (!cycle){
            return null;
        }

        f = head;
        while (f != s){
            f = f.next;
            s = s.next;
        }

        return f;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode prev_head = new ListNode(), prev = prev_head;
        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null){
            if (p1.val < p2.val){ // <
                prev.next = p1;
                p1 = p1.next;
            } else { // >=
                prev.next = p2;
                p2 = p2.next;
            }
            prev = prev.next;
        }

        if (p1 != null){
            prev.next = p1;
        }

        if (p2 != null){
            prev.next = p2;
        }

        return prev_head.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode prev_head = new ListNode(), prev = prev_head;

        ListNode p1 = l1, p2 = l2;
        
        while (p1 != null && p2 != null){
            int sum = p1.val + p2.val + c;
            c = sum / 10;
            int val = sum % 10;
            prev.next = new ListNode(val);
            prev = prev.next;
            p1 = p1.next;
            p2 = p2.next;
        }

        // 此时还有没有做完的运算
        if (p1 != null){
            while (p1 != null){
                int sum = p1.val + c;
                c = sum / 10;
                int val = sum % 10;
                prev.next = new ListNode(val);
                prev = prev.next;
                p1 = p1.next;
            }
        }

        if (p2 != null){
            while (p2 != null){
                int sum = p2.val + c;
                c = sum / 10;
                int val = sum % 10;
                prev.next = new ListNode(val);
                prev = prev.next;
                p2 = p2.next;
            }
        }

        if (c != 0){
            prev.next = new ListNode(1);
        }

        return prev_head.next;
    }

    // p 1 2  
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null)
            return null;

        ListNode prev_head = new ListNode();
        prev_head.next = head;

        ListNode f = prev_head, s = prev_head;
        int i = 0;
        while (f.next != null && i < n){
            f = f.next;
            ++i;
        } 

        while (f.next != null){
            f = f.next;
            s = s.next;
        }

        ListNode tmp = s.next.next;
        s.next.next = null;
        s.next = tmp;
        return prev_head.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null){
            return null;
        }

        if (head.next == null)
            return head;

        ListNode prev_head = new ListNode();
        ListNode f = head, s = prev_head;

        while (f != null && f.next != null){
            ListNode tmp = f.next.next;
            f.next.next = f;

            s.next = f.next;
            s = f;
            f.next = null;
            f = tmp;
        }

        if (f != null)
            s.next = f;

        return prev_head.next;
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Node p = head;
        while (p != null){
            Node p_copy = new Node(p.val);
            Node tmp = p.next;
            p.next = p_copy;
            p_copy.next = tmp;

            p = tmp;
        }

        // 填充next and random
        p = head;
        while (p != null){
            Node p_copy = p.next;
            p_copy.random = p.random == null ? null : p.random.next;
            p = p.next.next;
        }

        // 分离链表
        p = head;
        Node n_head = p.next, n_p = n_head;
        while (p != null){
            p.next = n_p.next;
            n_p.next = n_p.next == null ? null : n_p.next.next;
            p = p.next;
            n_p = n_p.next;
        }

        return n_head;
    }
}
