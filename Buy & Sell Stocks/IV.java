public class IV {


    // Recursive Approach
    public int func(int ind, int buy, int cap, int[] prices){
        // buy = 0 means that we can buy now. 1 means that we already bought and now we are ready to sell
        // Ind varies over 0 to n
        // Buy varies over 0 or 1
        // cap varies over 0/1/(2)
        if(cap==0) return 0;
        if(ind==prices.length) return 0;
        int profit = 0;
        if(buy==0){
            profit = Math.max(-prices[ind] + func(ind+1, 1, cap, prices) , 0 + func(ind+1, buy, cap, prices));
        }
        else{
            profit = Math.max(prices[ind] + func(ind+1, 0, cap-1, prices), 0+func(ind+1, buy, cap, prices));
        }
        return profit;
    }

    // Memoization Approach - TOP DOWN
    public int  funcMemo(int ind, int buy, int cap, int[] prices, int[][][] dp) {
        if(cap==0) return 0;
        if(ind==prices.length) return 0;
        if(dp[ind][buy][cap]!=-1) return dp[ind][buy][cap];
        int profit = 0;
        if(buy==0){
            profit = Math.max(-prices[ind] + funcMemo(ind+1, 1, cap, prices,dp) , 0 + funcMemo(ind+1, buy, cap, prices,dp));
        }
        else{
            profit = Math.max(prices[ind] + funcMemo(ind+1, 0, cap-1, prices,dp), 0+funcMemo(ind+1, buy, cap, prices,dp));
        }
        dp[ind][buy][cap] = profit;
        return profit;
    }

    // Tabulation Approach - BOTTOM UP
    public int maxProfit(int k, int[] prices){
        // The dp can be read like Given that we are on the ith day and can/cannot buy and we have cap capacity to purchase
        // how much profit can we make
        int[][][] dp = new int[prices.length+1][2][k+1];
        // Base Cases will always be zero because we exhausted the days of transaction and whatever be the buy/sell and capacity
        // we can only make 0 profit as there is no transaciton possible
        // Arrays are already initialized to zero so dont bother to initialize them to zero

        for (int ind = prices.length-1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    int profit = 0;
                    if(buy==0){
                        profit = Math.max(-prices[ind] + dp[ind+1][1][cap] , 0 + dp[ind+1][buy][cap]);
                    }
                    else{
                        profit = Math.max(prices[ind] + dp[ind+1][0][cap-1], 0+dp[ind+1][buy][cap]);
                    }
                    dp[ind][buy][cap] = profit;
                }
            }
        }
        return dp[0][0][k];
    }

    // Space Optimization on Tabulation
    public int maxProfitSO(int k, int[] prices){
        // The dp can be read like Given that we are on the ith day and can/cannot buy and we have cap capacity to purchase
        // how much profit can we make
        int[][] dp = new int[2][k+1];
        int[][] cur = new int[2][k+1];
        // Base Cases will always be zero because we exhausted the days of transaction and whatever be the buy/sell and capacity
        // we can only make 0 profit as there is no transaciton possible
        // Arrays are already initialized to zero so dont bother to initialize them to zero

        for (int ind = prices.length-1; ind >= 0; ind--) {
            for (int cap = 1; cap <= k; cap++) {
                
                    int buyprofit = Math.max(-prices[ind] + dp[1][cap] , 0 + dp[0][cap]);
                
                
                    int sellprofit = Math.max(prices[ind] + dp[0][cap-1], 0+dp[1][cap]);
                
                cur[0][cap] = buyprofit;
                cur[1][cap] = sellprofit;
            }
            dp =cur;
        }
        return dp[0][k];
    }
}
