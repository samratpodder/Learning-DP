import java.util.Arrays;

public class WildcardMatching {
    // Recursive Solution
    public boolean func(int i, int j, String s, String p){
        if(i<0 && j<0) return true;
        if(j<0) return false;
        if(i<0){
            for (int k = 0; k <= j; k++) {
                if(p.charAt(k)!='*') return false;
            }
            return true;
        }
        if (s.charAt(i)==p.charAt(j)||p.charAt(j)=='?') {
            return func(i-1, j-1, s, p);
        }
        else if(p.charAt(j)=='*') return func(i-1, j, s, p) || func(i-1, j-1, s, p);
        return false;
    }
    // Memoization Solution
    private boolean f(int i, int j, String p, String s, int[][] dp) {
        
        if (i < 0 && j < 0) return true;
        if (i < 0 && j >= 0) return false;
        if (j < 0 && i >= 0) {
            for (int k = 0; k <= i; k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (dp[i][j] != -1) {
            return dp[i][j] == 1 ? true : false;
        }
        if (p.charAt(i) == s.charAt(j) || p.charAt(i) == '?') {
            boolean flag = f(i - 1, j - 1, p, s, dp);
            dp[i][j] = (flag == true) ? 1 : 0;
            
            return flag;
        }
        if (p.charAt(i) == '*') {
            boolean flag = f (i - 1, j, p, s, dp) || f (i, j - 1, p, s, dp);
            dp[i][j] = (flag == true) ? 1 : 0;
            
            return flag;
        }
        dp[i][j] = 0;
        return false;
    }
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        int[][] dp = new int[n+1][m+1];
        for (int[] bs : dp) {
            Arrays.fill(bs, -1);   
        }
        return f(m-1, n-1, p, s,dp);
    }

    public boolean isMatchTab(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 1; j <= m; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int k = 1; k <= i; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
                else dp[i][j] = false;
            }
        }
        return dp[n][m];
    }

    // Tabulation Space Optimized Approach
    public boolean isMatchTabSO(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[] dp = new boolean[m + 1];
        
        dp[0] = true;
        for (int j = 1; j <= m; j++) {
            dp[j] = false;
        }
        for (int i = 1; i <= n; i++) {
            boolean[] cur = new boolean[m+1];
            boolean flag = true;
            for (int k = 1; k <= i; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            cur[0] = flag;
            for (int j = 1; j <= m; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    cur[j] = dp[j - 1];
                }
                else if (p.charAt(i - 1) == '*') {
                    cur[j] = dp[j] || cur[j - 1];
                }
                else cur[j] = false;
            }
            dp = cur;
        }
        return dp[m];
    }

}

