class Solution {
    public boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    public boolean isAlpha(char c){
        return c >= 'a' && c <= 'z';
    }

    public String reverseStr(String s){
        if (s == null)
            return null;
        int r = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (r >= 0){
            sb.append(s.charAt(r) + "");
            --r;
        }
        return sb.toString();
    }

    public String copyNumsOfStr(String s, int copy_num){
        StringBuilder sb = new StringBuilder();
        String tmp = s;
        while(copy_num != 0){
            sb.append(tmp);
            --copy_num;
        }
        return sb.toString();
    }
    // 字符串解码
    // 数字栈 
    // 字符栈 dcdc
    // 3[ab2[cd]]   abdcdc
    public String decodeString(String s) {
        Deque<Integer> n_stack = new ArrayDeque<>();
        Deque<Character> c_stack = new ArrayDeque<>();
        int len = s.length();
        String tmpNumStr = "";
        String tmpcStr = "";
        int p = 0; // 移动指针
        while (p != len) {
            char c = s.charAt(p);
            if (isNumber(c)){
                tmpNumStr += c + "";
                // 获取完整的整数字符串
                ++p;
                while (p != len && isNumber(s.charAt(p))) {
                    tmpNumStr += s.charAt(p++) + "";
                }
                n_stack.push(Integer.parseInt(tmpNumStr));
                tmpNumStr = ""; // 还原
            } else if (isAlpha(c) || c == '['){
                c_stack.push(c); // 字母和[直接栈
                ++p;
            } else{
                // 此时是]，开始推栈
                while (!c_stack.isEmpty()){
                    char tmp_c = c_stack.pop();
                    if (tmp_c != '['){
                        tmpcStr += tmp_c + "";
                    }else{ // 反转Sequence
                        String reverseStr = reverseStr(tmpcStr);
                        int tmp_n = n_stack.pop();
                        reverseStr = copyNumsOfStr(reverseStr, tmp_n);
                        // 推入栈
                        for (int i = 0; i < reverseStr.length(); ++i){
                            c_stack.push(reverseStr.charAt(i));
                        }
                        // 还原变量
                        tmpcStr = "";
                        break;
                    }
                }
                ++p;
            }
            
        }

        // c_stack中剩下的就是我的字符串
        StringBuilder sb = new StringBuilder();
        while (!c_stack.isEmpty()){
            char c = c_stack.pop();
            sb.append(c);
        }
        return reverseStr(sb.toString());
        
    }
}
