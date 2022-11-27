import java.util.Arrays;

class Solution {

    //Recursive Solution
    //
    public int change(int amount, int[] coins) {
        return ways(coins.length-1,amount,coins);
    }
    public int ways(int i, int target, int[] coins)
    {
        if(i==0) return target%coins[0]==0?1:0;
        int nottake = ways(i-1,target,coins);
        int take = 0;
        if(target>=coins[i]) take = ways(i,target-coins[i],coins);
        return take+nottake;
    }

    // Memoization Solution TOP-DOWN
    public int changeMemo(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for(int[] is:dp){
            Arrays.fill(is,-1);
        }
        return ways(coins.length-1,amount,coins,dp);
    }
    public int ways(int i, int target, int[] coins, int[][] dp)
    {
        if(i==0) return target%coins[0]==0?1:0;
        if(dp[i][target]!=-1) return dp[i][target];
        int nottake = ways(i-1,target,coins,dp);
        int take = 0;
        if(target>=coins[i]) take = ways(i,target-coins[i],coins,dp);
        dp[i][target] = take+nottake;
        return dp[i][target];
    }

    // Tabulation Approach BOTTOM-UP
    public int changeTab(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for(int i=0;i<=amount;i++){
            dp[0][i] = (i%coins[0]==0)?1:0;
        }
        for(int idx=1;idx<coins.length;idx++){
            for(int t=0;t<=amount;t++){
                int nottake = dp[idx-1][t];
                int take = 0;
                if(t>=coins[idx]) take = dp[idx][t-coins[idx]];
                dp[idx][t] = nottake+take;
            }
        }
        return dp[coins.length-1][amount];
    }

    // Tabulation Approach BOTTOM-UP Space Optimized
    public int changeTabSO(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        for(int i=0;i<=amount;i++){
            dp[i] = (i%coins[0]==0)?1:0;
        }
        for(int idx=1;idx<coins.length;idx++){
            int[] cur = new int[amount+1];
            for(int t=0;t<=amount;t++){
                int nottake = dp[t];
                int take = 0;
                if(t>=coins[idx]) take = cur[t-coins[idx]];
                cur[t] = nottake+take;
            }
            dp = cur;
        }
        return dp[amount];
    }
}