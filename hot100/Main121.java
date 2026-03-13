class Solution {
    // 买卖股票的最佳时机
    // 方法一: 超时
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1){
            return 0; // 此时一分钱都赚不了
        }

        // 选择当前价格之后，要选择后面的最大值
        int profits = 0;
        for (int i = 0; i < n; ++i){
            int cur = prices[i];
            // 获取后面序列中的最大值
            int maxProfit = -1;
            for (int j = i + 1; j < n; ++j){
                maxProfit = Math.max(maxProfit, prices[j]);
            }
            int curProfits = maxProfit - cur <= 0? 0: maxProfit - cur;
            profits = Math.max(curProfits, profits);
        }
        return profits;
    }

    // 方法二: 贪心，每次买都要在历史最低点买入
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n <= 1){
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < n; ++i){
            int cur = prices[i];
            if (cur < minPrice){
                minPrice = cur; // 当前点不适合买入
            } else if (cur - minPrice > maxProfit){
                maxProfit = cur - minPrice; // 可以得到当前卖出的利润
            }
        }
        return maxProfit;
    }
}
