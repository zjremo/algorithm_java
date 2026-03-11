class Solution {
    // 分割回文串
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
}

