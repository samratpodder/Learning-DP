import java.util.Arrays;

class UbK{
    // Recursive Solution
    public static int knapSack(int N, int W, int val[], int wt[])
    {
        return func(val.length-1,W,val,wt);
    }
    public static int func(int idx, int w, int[] val, int[] wt){
        if(idx==0){
            return (w/wt[0])*val[0];
        }
        int nottake = 0+func(idx-1,w,val,wt);
        int take = Integer.MIN_VALUE;
        if(w>=wt[idx]) take = val[idx] + func(idx, w-wt[idx], val, wt);
        return Math.max(nottake, take);
    }

    // Memoization Approach TOP DOWN
    public static int knapSackMemo(int N, int W, int val[], int wt[])
    {
        int[][] dp = new int[val.length][W+1];
        for(int[] is:dp) Arrays.fill(is, -1);
        return funcMemo(val.length-1,W,val,wt,dp);
    }
    public static int funcMemo(int idx, int w, int[] val, int[] wt, int[][] dp){
        if(idx==0){
            return (w/wt[0])*val[0];
        }
        if(dp[idx][w]!=-1) return dp[idx][w];
        int nottake = 0+funcMemo(idx-1,w,val,wt,dp);
        int take = Integer.MIN_VALUE;
        if(w>=wt[idx]) take = val[idx] + funcMemo(idx, w-wt[idx], val, wt, dp);
        dp[idx][w] = Math.max(nottake, take);
        return dp[idx][w];
    }

    // Tabulation Approach Bottom Down
    public static int knapSackTab(int N, int W, int val[], int wt[])
    {
        int[][] dp = new int[val.length][W+1];
        for (int i = wt[0]; i < W+1; i++) {
            dp[0][i] = ((int)i/wt[0])*val[0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int w = 0; w < W+1; w++) {
                int nottake = 0+dp[i-1][w];
                int take = Integer.MIN_VALUE;
                if(w>=wt[i]) take = val[i] + dp[i][w-wt[i]];
                dp[i][w] = Math.max(nottake, take);
            }
        }
        return dp[val.length-1][W];
    }

    // Tabulation Approach Space Optimized
    public static int knapSackTabSO(int N, int W, int val[], int wt[])
    {
        int[] dp = new int[W+1];
        for (int i = wt[0]; i < W+1; i++) {
            dp[i] = ((int)i/wt[0])*val[0];
        }
        for (int i = 1; i < N; i++) {
            int[] curr = new int[W+1];
            for (int w = 0; w < W+1; w++) {
                int nottake = 0+dp[w];
                int take = Integer.MIN_VALUE;
                if(w>=wt[i]) take = val[i] + curr[w-wt[i]];
                curr[w] = Math.max(nottake, take);
            }
            dp = curr;
        }
        return dp[W];
    }

}