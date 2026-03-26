class Solution{
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return res;
        backtrace(new ArrayList<>(), nums, 0);
        return res;
    }

    public void backtrace(List<Integer> path, int[] nums, int idx){
        if (idx == nums.length){
            res.add(new ArrayList<>(path));
            return ;
        }

        path.add(nums[idx]);
        backtrace(path, nums, idx + 1);
        // 清理现场
        path.remove(path.size() - 1);
        backtrace(path, nums, idx + 1);
    }

    private Map<Character, String> map = new HashMap<>(){
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null)
            return res;
        backtrace(new StringBuilder(), digits, 0);
        return res;
    }

    public void backtrace(StringBuilder sb, String digits, int idx){
        if (idx == digits.length()){
            res.add(sb.toString());
            return ;
        }

        char num = digits.charAt(idx);
        String candidate = map.get(num);

        for (int i = 0; i < candidate.length(); ++i){
            sb.append(candidate.charAt(i));
            backtrace(sb, digits, idx + 1);
            // 清理现场
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        if (n == 0)
            return res;
        backtrace(new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }

    public void backtrace(List<Integer> path, int[] candidates, int target, int sum, int idx){
        if (sum >= target){
            if (sum == target){
                res.add(new ArrayList<>(path));
            }
            return ;
        }

        for (int i = idx; i < candidates.length; ++i){
            path.add(candidates[i]);
            backtrace(path, candidates, target, sum + candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    private List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0)
            return res;
        backtrace(new StringBuilder(), n, n);
        return res;
    }

    public void backtrace(StringBuilder sb, int leftSum, int rightSum){
        // leftSum > rightSum 左括号用少了，此时必不合理
        // leftSum <= rightSum 左括号用多了，此时左右都可
        if (leftSum == 0 && rightSum == 0){
            res.add(sb.toString());
            return ;
        }

        // 左右不全为0
        if (leftSum > rightSum){
            return;
        } else {
            // 先尝试放左括号
            if (leftSum != 0){
                sb.append("(");
                backtrace(sb, leftSum - 1, rightSum);
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
                backtrace(sb, leftSum, rightSum - 1);
                sb.deleteCharAt(sb.length() - 1);
            } else{
                sb.append(")");
                backtrace(sb, leftSum, rightSum - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private int[][] directions = new int[][]{
        {0, -1}, {-1, 0}, {0, 1}, {1, 0}
    };

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        if (word == null)
            return false;

        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (board[i][j] == word.charAt(0)){
                    boolean[][] traverse = new boolean[m][n];
                    traverse[i][j] = true;
                    if (backtrace(traverse, board, word, new StringBuilder(board[i][j] + ""), i, j)){
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public boolean backtrace(boolean[][] traverse, char[][] board, String word, StringBuilder sb, int r, int c){
        if (word.length() == sb.length()){
            // check if equals
            if (sb.toString().equals(word)){
                return true;
            }
            return false;
        }

        int m = board.length, n = board[0].length;

        for (int i = 0; i < 4; ++i){
            int tmp_r = r + directions[i][0], tmp_c = c + directions[i][1];
            // check edge condition
            if (tmp_r >= 0 && tmp_r < m && tmp_c >= 0 && tmp_c < n && !traverse[tmp_r][tmp_c]){
                sb.append(board[tmp_r][tmp_c] + "");
                traverse[tmp_r][tmp_c] = true;
                boolean isfind = backtrace(traverse, board, word, sb, tmp_r, tmp_c);
                if (isfind){
                    return true;
                }
                // 清理现场
                traverse[tmp_r][tmp_c] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return false;
        
    }

    // 其实就是打逗号
    private List<List<String>> resPartitionList = new ArrayList<>();

    // a,a,b  某个位置后面打逗号 -> 0 -> len - 2 保证打完这个逗号之后前面的部分一定是回文的
    public List<List<String>> partition(String s) {
        if (s == null)
            return new ArrayList<>();
        backtrace(new ArrayList<>(), s, 0);
        return resPartitionList;
    }

    public boolean isPalindrome(String s, int begin, int end){
        boolean flag = true;
        while (begin <= end){
            if (s.charAt(begin++) != s.charAt(end--)){
                flag = false;
                break;
            }
        }
        return flag;
    }

    // 获取切分字符串 a, ab
    public void getSplitStr(List<String> strList, List<Integer> list, String s){
        int prev = 0;
        for (int cur: list){
            String str = s.substring(prev, cur + 1);
            strList.add(str);
            prev = cur + 1;
        }
        // 还差最后一个字符串
        strList.add(s.substring(prev, s.length()));
    }
    /*
     * list中存储逗号的位置
     * curIdx 存储当前遍历到的位置
     */
    public void backtrace(List<Integer> list, String s, int curIdx){
        // 终止条件: 所有能打逗号的位置都弄完了
        if (curIdx == s.length() - 1){
            List<String> strList = new ArrayList<>();
            // 判断此最后一个打逗号的位置之后的是否是回文串
            int begin = list.size() == 0 ? 0 : list.getLast() + 1;
            int end = s.length() - 1;
            if (isPalindrome(s, begin, end)){
                if (list.size() == 0){
                    strList.add(s); // 自己本身就是回文串
                }else{
                    // 根据逗号切分字符串加入到strList
                    getSplitStr(strList, list, s);
                }
                resPartitionList.add(strList);
            }
            return ;
        }

        // 判断能不能加上逗号
        // 1. 可以加逗号
        int begin = list.size() == 0 ? 0: list.getLast() + 1;
        if (isPalindrome(s, begin, curIdx)){
            list.add(curIdx);
            backtrace(list, s, curIdx + 1);
            list.remove(list.size() - 1);
        }
        // 2. 一定可以不加逗号
        backtrace(list, s, curIdx + 1);
    }

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
 
        // 找比target大，但是最接近的位置
        int ans = -1;
        while (l <= r){
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > target){
                ans = mid;
                r = mid - 1;
            } else if (nums[mid] == target){
                ans = mid;
                return ans;
            } else{
                l = mid + 1;
                ans = l;
            }
        }
        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; ++i){
            int l = 0, r = n - 1;
            while (l <= r){
                int mid = l + ((r - l) >> 1);
                if (matrix[i][mid] < target){
                    l = mid + 1;
                } else if (matrix[i][mid] == target){
                    return true;
                } else{
                    r = mid - 1;
                }
            }
        }
        return false;
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int idx = searchInsert(nums, target);
        if (idx != n && nums[idx] == target){
            int endIdx = idx;
            for (; endIdx < n && nums[endIdx] == target; ++endIdx);
            return new int[]{idx, endIdx - 1};
        }
        return new int[]{-1, -1};
    }

    public int searchInsert(int[] nums, int target){
        int n = nums.length;
        int l = 0, r = n - 1;
        int ans = n;
        while (l <= r){
            int mid = l + ((r - l) >> 1);
            if (target <= nums[mid]){
                ans = mid;
                r = mid - 1;
            } else{
                l = mid + 1;
            }
        }
        return ans;
    }

    // 1, 3  3
    public int search(int[] nums, int target) {
        int n = nums.length;

        int l = 0, r = n - 1;
        while (l <= r){
            int mid = l + ((r - l) >> 1);
            // 完全有序 l < r 
            // 完全逆序 + (有序 + 部分有序)
            if (nums[l] < nums[r]){
                // 直接判断
                if (target < nums[mid]){
                    r = mid - 1;
                } else if (target == nums[mid]){
                    return mid;
                } else {
                    l = mid + 1;
                }
            } else {
                // 逆序 + 部分有序
                if (l == r - 1){
                    // 全是逆序
                    if (target == nums[l])
                        return l;
                    else if (target == nums[r])
                        return r;
                    else 
                        return -1;
                }
                int sortBegin = nums[mid] > nums[r] ? l : mid;
                int sortEnd = sortBegin == mid ? r : mid;

                if (target == nums[mid]){
                    return mid;
                } else if (target <= nums[sortEnd] && target >= nums[sortBegin]){
                    l = sortBegin == mid ? mid + 1 : sortBegin;
                    r = sortEnd == mid ? mid - 1 : sortEnd;
                } else {
                    // 去部分有序的地方找
                    l = sortBegin == mid ? l : mid + 1;
                    r = sortBegin == mid ? mid - 1 : r;
                }
            }
        }

        return -1; // 没有发现
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0)
            return -1;
        if (n == 1)
            return nums[0] == target ? 0 : -1;

        int l = 0, r = n - 1;
        while (l <= r){
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target){
                return mid;
            }

            if (nums[l] <= nums[mid]){
                // 此时l -> mid 有序
                if (nums[l] <= target && target < nums[mid]){
                    r = mid - 1;
                } else { // 在不有序部分
                    l = mid + 1;
                }
            } else { // 右边有序
                if (nums[mid] < target && target <= nums[r]){
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}

