class Solution {
    // 有效的括号
    // {} [] ()
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1)
            return false; // 最后的长度不是偶数肯定是错的

        Map<Character, Character> pairs = new HashMap<>(){{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        // 调栈
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; ++i){
            char c = s.charAt(i);
            if (pairs.containsKey(c)){ //右括号要检查
                if (stack.isEmpty() || stack.peek() != pairs.get(c)){
                    return false;
                }
                stack.pop();
            } else{
                // 此时必是左括号
                stack.push(c);
            }
            
        }
        return stack.isEmpty();
    }
}
