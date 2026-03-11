// 回溯部分重点题目
class Solution {
    // Method1: 全排列
    // 每个元素必须被选到
    private List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backtrace(new ArrayList<>(nums.length), used, 0, nums);
        return lists;
    }

    public void backtrace(List<Integer> list, boolean[] used, int count, int[] nums){
        if (count == nums.length){
            // 此时是终止条件，copy到res中
            lists.add(new ArrayList<>(list));
            return ;
        }

        // 遍历所有可能结果
        for (int i = 0; i < used.length; ++i){
            if (!used[i]){ // 条件
                list.add(nums[i]);
                used[i] = true;
                // 回溯调用
                backtrace(list, used, count + 1, nums);
                // 现场清理
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }

    // Method2: 子集问题
    // 每个元素可有可无，结果是每次我加一个元素就可以算一个结果
    private List<List<Integer>> subSetLists = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrace(nums, 0, new ArrayList<>());
        return subSetLists;
    }

    public void backtrace(int[] nums, int count, List<Integer> list){
        // 统计进行了几步操作，每步可放可不放元素
        if (count == nums.length){
            subSetLists.add(new ArrayList<>(list));
            return ;
        }

        list.add(nums[count]);
        backtrace(nums, count + 1, list); // 当前有操作
        list.remove(list.size() - 1);
        backtrace(nums, count + 1, list); // 当前没有操作
    }

    // Method3: 电话号码里的字母组合
    private Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;
        backtrace1(res, digits, 0, new StringBuilder());
        return res;
    }

    public  void backtrace1(List<String> list, String digits, int count, StringBuilder sb){
        // 终止条件编写，长度此时已经达到digits长度
        if (count == digits.length()){
            list.add(sb.toString());
            return ;
        }

        // 遍历所有可能性
        // 加入当前元素，然后更新count
        String candidate = phoneMap.get(digits.charAt(count));
        int len = candidate.length();
        for (int i = 0; i < len; ++i){
            sb.append(candidate.charAt(i));
            backtrace1(list, digits, count + 1, sb);
            // 恢复现场
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    // Method4: 组合总和
    List<List<Integer>> rescombinationSumList = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 数组是0个元素
        if (candidates.length == 0){
            return new ArrayList<>();
        }

        backtrace1(candidates, new ArrayList<>(), 0, target, 0);
        return rescombinationSumList;
    }

    public void backtrace1(int[] candidates, List<Integer> list, int sum, int target, int count){
        if (sum >= target){
            if (sum == target)
                rescombinationSumList.add(new ArrayList<>(list));
            return ;
        }

        for (int i = count; i < candidates.length; ++i){
            list.add(candidates[i]);
            // 如果不能重复选当前元素，我们就需要更新count
            // 本题可以重复选当前元素，于是我们不更新count
            backtrace1(candidates, list, sum + candidates[i], target, i);
            list.remove(list.size() - 1);
        }
    }

    // Method5: 括号生成
    // n代表要用到n个左括号，n个右括号
    private List<String> parenthesisList = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        // 维护两个变量，代表剩余的左括号和右括号数目，用完则退出
        if (n == 0)
            return new ArrayList<>();
        backtrace2(new StringBuilder(), n, n);
        return parenthesisList;
    }

    public void backtrace2(StringBuilder sb, int leftNum, int rightNum){
        if (leftNum == 0 && rightNum == 0){
            parenthesisList.add(sb.toString());
            return ;
        }
        
        // 左括号剩余 < 右括号剩余，此时左括号放的多，左右随便放
        // 左括号放完了此时只能放右括号
        if (leftNum < rightNum){
            // 左右都可以放
            if (leftNum != 0){
                sb.append("(");
                backtrace2(sb, leftNum - 1, rightNum);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (rightNum != 0){
                sb.append(")");
                backtrace2(sb, leftNum, rightNum - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else if (leftNum == rightNum){
            sb.append("(");
            backtrace2(sb, leftNum - 1, rightNum);
            sb.deleteCharAt(sb.length() - 1);
        } else{
            // 不合要求
            return ;
        }
    }

    //Method6: 单词搜索
    // 上下左右
    private int[][] directions = new int[][]{
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public void initUsed(boolean[][] used, int m){
        int i = 0;
        while (i < m) {
            Arrays.fill(used[i], false);
            ++i;
        }
    }
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        // boolean数组 代表是否完成遍历
        boolean[][] used = new boolean[m][n];

        // 以不同的起点开始向下递归
        boolean res = false;
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                if (board[i][j] != word.charAt(0))
                    continue;  // 剪枝
                // 填充used数组, 完成重置与初始化
                initUsed(used, m);
                used[i][j] = true;
                res = backtrace(board, new StringBuilder(board[i][j] + ""), used, word, i, j, m, n);
                if (res)
                    return res; // true
            }
        }
        return false;
    }

    public boolean backtrace3(char[][] board, StringBuilder sb, boolean[][] used, String target, int row, int col, int m, int n){
        // 每次有条件地添加元素，最终只要长度达标就ok
        if (sb.length() == target.length()){
            return true;
        }

        // 尝试四个方向
        for (int i = 0; i < 4; ++i){
            int tmp_row = row + directions[i][0], tmp_col = col + directions[i][1];
            int idx = sb.length();
            // check condition for tmp_row and tmp_col
            if (tmp_row >= 0 && tmp_row < m && tmp_col >= 0 && tmp_col < n && !used[tmp_row][tmp_col] && board[tmp_row][tmp_col] == target.charAt(idx)){
                used[tmp_row][tmp_col] = true;
                // 加入向后选择的元素 
                sb.append(board[tmp_row][tmp_col] + "");
                boolean res = backtrace3(board, sb, used, target, tmp_row, tmp_col, m, n);
                if (res){
                    return true;
                } else{
                    // 此时需要回退，向另一个方向寻找出路
                    sb.deleteCharAt(sb.length() - 1);
                    used[tmp_row][tmp_col] = false;
                }
            }
        }

        // 四个方向都无法进行尝试
        return false;
    }

    //Method7: 分割回文串
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
    public void backtrace4(List<Integer> list, String s, int curIdx){
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
            backtrace4(list, s, curIdx + 1);
            list.remove(list.size() - 1);
        }
        // 2. 一定可以不加逗号
        backtrace4(list, s, curIdx + 1);
    }
}
