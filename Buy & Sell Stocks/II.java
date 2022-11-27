public class II {

    // Recursive Solution
    public int func(int ind, int buy, int[] prices){
        // Buy = 1 means we bought previously, = 0 means we can buy now standing at index ind
        if(ind==prices.length) return 0;
        int profit = 0;
        if(buy==0){
            profit = Math.max(-prices[ind] + func(ind+1, 1, prices), 0+func(ind+1, buy, prices));
        }
        else{
            profit = Math.max(prices[ind]+func(ind+1, 0, prices), 0+func(ind+1, buy, prices));
        }
        return profit;
    }

    // Memoization Approach - Top Down 
    public int funcMemo(int ind, int buy, int[] prices, int[][] dp){
        // Buy = 1 means we bought previously, = 0 means we can buy now standing at index ind
        if(ind==prices.length) return 0;
        if(dp[ind][buy]!=-1) return dp[ind][buy];
        int profit = 0;
        if(buy==0){
            profit = Math.max(-prices[ind] + funcMemo(ind+1, 1, prices,dp), 0+funcMemo(ind+1, buy, prices,dp));
        }
        else{
            profit = Math.max(prices[ind]+funcMemo(ind+1, 0, prices,dp), 0+funcMemo(ind+1, buy, prices,dp));
        }
        dp[ind][buy] = profit;
        return dp[ind][buy];
    }

    // Tabualtion Approach - Bottom Up
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        dp[n][0] = 0;
        dp[n][1] = 0;
        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if(buy==0){
                    profit = Math.max(-prices[ind] + dp[ind+1][1], dp[ind+1][buy]);
                }
                else{
                    profit = Math.max(prices[ind]+dp[ind+1][0], 0+dp[ind+1][buy]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }

    // Tabulation Space Optimization 
    public int maxProfitSO(int[] prices) {
        int n = prices.length;
        int[] dp = new int[2];
        int[] cur = new int[2];
        for (int ind = n-1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if(buy==0){
                    profit = Math.max(-prices[ind] + dp[1], dp[buy]);
                }
                else{
                    profit = Math.max(prices[ind]+dp[0], 0+dp[buy]);
                }
                cur[buy] = profit;
            }
            dp = cur;
        }
        return dp[0];
    }
}
