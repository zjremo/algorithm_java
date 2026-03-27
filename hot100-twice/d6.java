class MinStack {
    // 一个栈维护正常顺序
    private Deque<Integer> s1;
    // 一个栈维护递减顺序
    private Deque<Integer> s2;

    public MinStack() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }
    
    public void push(int val) {
        s1.push(val);

        Deque<Integer> tmp = new ArrayDeque<>();
        while (!s2.isEmpty()){
            int peek = s2.peek();
            if (peek >= val){
                // 找到位置
                break;
            }
            tmp.push(s2.pop());
        }

        s2.push(val);
        while (!tmp.isEmpty()){
            s2.push(tmp.pop());
        }
    }
    
    public void pop() {
        int target = s1.pop();
        
        Deque<Integer> tmp = new ArrayDeque<>();
        while (!s2.isEmpty()){
            int peek = s2.peek();
            if (peek == target){
                s2.pop();
                break;
            }
            tmp.push(s2.pop());
        }

        while (!tmp.isEmpty()){
            s2.push(tmp.pop());
        }

    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
class Solution{
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return -1;
        if (n == 1){
            return nums[0];
        }

        return findMin(nums, 0, n - 1);
    }

    public int findMin(int[] nums, int l, int r){
        if (l > r){
            return Integer.MAX_VALUE;
        }

        int mid = ((r - l) >> 1) + l;
        int sortMin, unsortMin;
        if (nums[l] <= nums[mid]){
            sortMin = nums[l];
            unsortMin = findMin(nums, mid + 1, r);
        } else {
            sortMin = nums[mid];
            unsortMin = findMin(nums, l, mid - 1);
        }

        return Math.min(sortMin, unsortMin);
    }

    // 左括号入栈，右括号时栈顶必须是匹配的左括号
    public boolean isValid(String s) {
        Deque<Character> statck = new ArrayDeque<>();

        char[] cArray = s.toCharArray();
        for (char c: cArray){
            if (isLeft(c)){
                statck.push(c);
            } else {
                // 检查栈顶
                if (statck.isEmpty()){
                    return false;
                }
                char c1 = statck.pop();
                if (map.get(c) != c1){
                    return false;
                }
            }
        }

        return statck.isEmpty();
    }

    public boolean isLeft(Character c){
        return c == '(' || c == '{' || c == '[';
    }

    private Map<Character, Character> map = new HashMap<>(){
        {
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }
    };

    public String decodeString(String s) {
        Deque<Integer> s1 = new ArrayDeque<>(); // 操作数
        Deque<String> s2 = new ArrayDeque<>(); // 字符

        int i = 0 , n = s.length();
        while (i < n) {
            int cur = 0;
            if (isAlpha(s.charAt(i))){
                // 拼接完整的数
                while (i < n && isAlpha(s.charAt(i))){
                    cur = 10 * cur + (s.charAt(i) - '0');
                    ++i;
                }
                // 此时i位置对应的不为数
                s1.push(cur);
            } else{
                // 不为右括号
                if (s.charAt(i) != ']'){
                    // 直接入栈
                    s2.push(s.charAt(i) + "");
                } else{
                    // 此时是右括号
                    List<String> list = new ArrayList<>();
                    while (!s2.isEmpty()){
                        String tmp = s2.pop(); 
                        if (!tmp.equals("[")){
                            list.add(tmp);
                        } else{ // 此时为[就要跳出
                            break;
                        }
                    }
                    // a bc  -> bc a
                    Collections.reverse(list);
                    int times = s1.pop();
                    // concat
                    String str = concat(list, times);
                    // 入栈
                    s2.push(str);
                }
                ++i;
            }
        }

        List<String> res = new ArrayList<>();
        while (!s2.isEmpty()){
            res.add(s2.pop());
        }
        Collections.reverse(res);
        return concat(res, 1);
    }

    public boolean isAlpha(char c){
        return c >= '0' && c <= '9';
    }

    public String concat(List<String> list, int times){
        StringBuilder res = new StringBuilder();
        for (String str : list){
            res.append(str);
        }

        int i = 0;
        String str = res.toString();
        while (i < times - 1){
            res.append(str);
            ++i;
        }
        return res.toString();
    }

    public int[] dailyTemperatures(int[] temperatures) {
        // 单调栈
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;

        int[] res = new int[n];

        for (int i = 0; i < n; ++i){
            if (stack.isEmpty()){
                stack.push(i);
            } else{
                // 检查栈顶
                while (!stack.isEmpty()){
                    int top = stack.peek();
                    if (temperatures[top] >= temperatures[i]){
                        stack.push(i);
                        break;
                    } else{
                        // top < i
                        res[top] = i - top;
                        stack.pop();
                    }
                }
                if (stack.isEmpty()){
                    stack.push(i);
                }
            }
        }

        // 栈里面剩下的其实就是赋值为0的，不用管，直接默认值
        return res;
    }

    // nums = [1,2,1,2,1,2,3,1,3,2], k = 2
    // [1, 2]
    private record Node(int val, int times){};

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : nums){
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        List<Node> list = new ArrayList<>();

        for (int key : map.keySet()){
            list.add(new Node(key, map.get(key)));
        }

        // 写比较器比较
        Collections.sort(list, new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return Integer.compare(o1.times, o2.times);
            }
        });

        return list.stream().skip(list.size() - k).mapToInt(node -> node.val).toArray();
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1){
            return 0;
        }
        int profit = 0;
        // 维护每一天前面最小的股票价格   
        int min = prices[0];

        for (int i = 1; i < n; ++i){
            if (prices[i] > min){
                profit = Math.max(profit, prices[i] - min);
            } else {
                min = prices[i];
            }
        }
        return profit;

    }

    public boolean canJump(int[] nums) {
        // 覆盖范围
        int n = nums.length;
        if (n == 0)
            return false;

        if (n == 1)
            return true;
        
        int maxId = 0;
        for (int i = 0; i < n; ++i){
            // 判断是否需要更新覆盖范围
            if (i <= maxId){
                maxId = Math.max(i + nums[i], maxId);
                if (maxId >= n - 1){
                    return true;
                }
            }         
        }

        return false;
    }

    public int jump(int[] nums) {
        // 覆盖范围要尽可能逐渐变大
        // 每次到边界处更新一次覆盖范围
        int n = nums.length;
        if (n <= 1)
            return 0;

        int maxId = 0, steps = 0, curId = 0;

        for (int i = 0; i < n; ++i){
            maxId = Math.max(maxId, i + nums[i]);
            if (i == curId){
                // 更新覆盖范围?
                if (maxId > curId){
                    ++steps;
                    curId = maxId;
                }
                if (curId >= n - 1){
                    break;
                }
            }
        }
        return steps;
    }

}
