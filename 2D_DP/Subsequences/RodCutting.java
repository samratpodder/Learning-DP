import java.util.Arrays;

public class RodCutting {
    // Recursion Approach
    public int cutRod(int price[], int n) {
        return func(n-1,n,price);
    }
    public int func(int idx, int n, int[] price){
        if(idx==0) return n*price[0];
        int nottake = 0+func(idx-1, n, price);
        int take = Integer.MIN_VALUE;
        if(idx+1<=n) take = price[idx] + func(idx, n-(idx+1), price);
        return Math.max(nottake, take);
    }

    // Memoization Approach TOP DOWN
    public int cutRodMemo(int price[], int n) {
        int[][] dp = new int[price.length][n+1];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        return funcMemo(n-1,n,price,dp);
    }
    public int funcMemo(int idx, int n, int[] price, int[][] dp){
        if(idx==0) return n*price[0];
        if(dp[idx][n]!=-1) return dp[idx][n];
        int nottake = 0+funcMemo(idx-1, n, price, dp);
        int take = Integer.MIN_VALUE;
        if(idx+1<=n) take = price[idx] + funcMemo(idx, n-(idx+1), price, dp);
        dp[idx][n] = Math.max(nottake, take);
        return dp[idx][n];
    }

    // Tabulation Approach BOTTOM UP
    public int cutRodTab(int price[], int n) {
        int[][] dp = new int[price.length][n+1];
        for (int i = 0; i < n+1; i++) {
            dp[0][i] = i*price[0]; 
        }
        for (int ind = 1; ind < price.length; ind++) {
            for (int length = 0; length < n+1; length++) {
                int nottake = 0+dp[ind-1][length];
                int take = Integer.MIN_VALUE;
                if(ind+1<=length) take = price[ind] + dp[ind][length-(ind+1)];
                dp[ind][length] = Math.max(nottake, take);
            }
        }
        return dp[price.length-1][n];
    }

    //Tabulation Approach Space Optimized
    public int cutRodTabSO(int price[], int n) {
        int[] dp = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            dp[i] = i*price[0]; 
        }
        for (int ind = 1; ind < price.length; ind++) {
            int[] cur = new int[n+1];
            for (int length = 0; length < n+1; length++) {
                int nottake = 0+dp[length];
                int take = Integer.MIN_VALUE;
                if(ind+1<=length) take = price[ind] + cur[length-(ind+1)];
                cur[length] = Math.max(nottake, take);
            }
            dp=cur;
        }
        return dp[n];
    }
}
