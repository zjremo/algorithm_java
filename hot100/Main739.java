class Solution {
    // 每日温度
    // 方法一: 暴力方法，超时
    public int[] dailyTemperatures(int[] temperatures) {
        // 73 74 75 71 69 72 76 73 
        // 73 76 72 69 71 75 74 73 逆序遍历，每次查看当前到末尾的第一个比它大的数
        List<Integer> ans = new ArrayList<>();
        int n = temperatures.length;
        for (int i = n - 1; i >= 0 ; --i){
            int idx = -1;
            for (int j = i + 1; j < n ; ++j){
                if (temperatures[j] > temperatures[i]){
                    idx = j;
                    break;
                }
            }
            if (idx != -1){
                // 代表此时找到了
                ans.add(idx - i);
            }
            else{
                ans.add(0); // 没有找到
            }
        }
        // ans逆序
        Collections.reverse(ans);
        // 拷贝
        int[] ansArray = new int[n];
        int p = 0;
        for (int val : ans){
            ansArray[p++] = val;
        }
        return ansArray;
    }

    // 方法二: 暴力优化
    public int[] dailyTemperatures1(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        int[] next = new int[101]; // 0 -> 100 各个温度第一次出现的索引
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = len - 1 ; i >= 0; --i){
            int idx = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t){
                if (next[t] < idx){
                    idx = next[t];
                }
            }
            if (idx != Integer.MAX_VALUE)
                ans[i] = idx - i;
            // 更新next数组
            next[temperatures[i]] = i;
        }
        return ans;
    }

    // 方法三: 单调栈
    public int[] dailyTemperatures2(int[] temperatures) {
        int len = temperatures.length;
        if (len == 0)
            return null;

        int[] ans = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        // 依次入栈
        for (int i = 0; i < len; ++i){
            int t = temperatures[i];
            while (!stack.isEmpty() && t > temperatures[stack.peek()]) {
                int prev = stack.pop();
                ans[prev] = i - prev; // 因为维护的是一个单调栈
            }

            stack.push(i);
        }
        return ans;
    }
}
