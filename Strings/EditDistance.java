
/**
 * EditDistance
 * @author Samrat Podder
 */
public class EditDistance {

    // Recursive Solution
    public int func(int i, int j, String s1, String s2){
        if (i<0) return j+1;
        if (j<0) return i+1;
        if(s1.charAt(i) == s2.charAt(j)) return func(i-1, j-1, s1, s2);
        else
        {
            int delete = func(i-1, j, s1, s2);
            int replace =func(i-1, j-1, s1, s2);
            int insert = func(i, j-1, s1, s2);
            return 1+Math.min(replace, Math.min(delete, insert));
        }
    }

    // Memoization Approach
    public int funcMemo(int i, int j, String s1, String s2, int[][] dp){
        if (i<0) return j+1;
        if (j<0) return i+1;
        if(dp[i][j]!=-1) return dp[i][j];
        if(s1.charAt(i) == s2.charAt(j)) {dp[i][j] =funcMemo(i-1, j-1, s1, s2,dp); return dp[i][j];}
        else
        {
            int delete = funcMemo(i-1, j, s1, s2,dp);
            int replace =funcMemo(i-1, j-1, s1, s2,dp);
            int insert = funcMemo(i, j-1, s1, s2,dp);
            dp[i][j] = 1+Math.min(replace, Math.min(delete, insert));
            return dp[i][j];
        }
    }

    // Tabulation Approach
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<=n;i++) dp[i][0] = i;
        for(int j=0;j<=m;j++) dp[0][j] = j;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {dp[i][j] = dp[i-1][j-1];}
                else
                {
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];
                    int insert = dp[i][j-1];
                    dp[i][j] = 1+Math.min(replace, Math.min(delete, insert));
                }
            }
        }
        return dp[n][m];
    }

    // Tabulation Space Optimized Approach
    public int minDistanceSO(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] dp = new int[m+1];
        
        for(int j=0;j<=m;j++) dp[j] = j;
        for (int i = 1; i <= n; i++) {
            int[] cur = new int[m+1];
            cur[0] = i;
            for (int j = 1; j <= m; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) cur[j] = dp[j-1];
                else cur[j] = 1+Math.min(dp[j], Math.min(cur[j-1],dp[j-1]));
            }
            dp=cur;
        }
        return dp[m];
    }
}