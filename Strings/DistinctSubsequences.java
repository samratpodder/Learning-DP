
/**
 * DistinctSubsequences
 */
public class DistinctSubsequences {


    // Recursive Approach
    public int func(int i, int j, String s, String t)
    {
        if(j<0) return 1;
        if(i<0) return 0;
        if(s.charAt(i)==t.charAt(j)) return func(i-1, j-1, s, t) + func(i-1, j, s, t);
        else return func(i-1,j,s,t);
    }

    // Memoization Approach
    public int funcMemo(int i, int j, String s, String t, int[][] dp) {
        if(j<0) return 1;
        if(i<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        if(s.charAt(i)==t.charAt(j)) {
            dp[i][j] = funcMemo(i-1, j-1, s, t,dp) + funcMemo(i-1, j, s, t,dp);
            return dp[i][j];
        }
        else {
            dp[i][j] = funcMemo(i-1,j,s,t,dp);
            return dp[i][j];
        }
    }
    // Tabulation Approach
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(s.charAt(i-1)==t.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][m];
    }
    // Tabulation Space Optimization Approach 
    public int numDistinctSpaceOptimization(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] dp = new int[m+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int[] cur = new int[m+1];
            cur[0] = 1;
            for (int j = 1; j <= m; j++) {
                if(s.charAt(i-1)==t.charAt(j-1)) cur[j] = dp[j-1] + dp[j];
                else cur[j] = dp[j];
            }
            dp = cur;
        }
        return dp[m];
    }
}