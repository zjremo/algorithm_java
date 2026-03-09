class Solution {
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

    // 字母组合
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;
        backtrack(res, digits, 0, new StringBuilder());
        return res;
    }

    public void backtrack(List<String> res, String digits, int index, StringBuilder temp){
        if (index == digits.length()){
            res.add(temp.toString());
            return ;
        }

        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        int letterCount = letters.length();

        for (int i = 0; i < letterCount; ++i){
            temp.append(letters.charAt(i));
            backtrack(res, digits, index + 1, temp);
            temp.deleteCharAt(index);
        }
    }
}
