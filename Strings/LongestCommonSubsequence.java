import java.util.Arrays;

/**
 * LongestCommonSubsequence
 */
class LongestCommonSubsequence {

    public static int LCS(int idx1, int idx2, String str1, String str2){
        if (idx1<0 || idx2<0) return 0;
        if(str1.charAt(idx1)==str2.charAt(idx2)) return 1+LCS(idx1-1, idx2-1, str1, str2);
        return Math.max(LCS(idx1-1, idx2, str1, str2), LCS(idx1, idx2-1, str1, str2));
    }

    //Memoization Approach TOP DOWN
    public static int LCSMemo(int idx1, int idx2, String str1, String str2, int[][] dp){
        if (idx1<0 || idx2<0) return 0;
        if(dp[idx1][idx2]!=-1) return dp[idx1][idx2];
        if(str1.charAt(idx1)==str2.charAt(idx2)) {
            dp[idx1][idx2] = 1+LCSMemo(idx1-1, idx2-1, str1, str2,dp);
            return  dp[idx1][idx2];
        }
        dp[idx1][idx2] =Math.max(LCSMemo(idx1-1, idx2, str1, str2,dp), LCSMemo(idx1, idx2-1, str1, str2,dp));
        return dp[idx1][idx2];
    }

    //Tabulation Approach BOTTOM UP
    public static int LCSTab(String str1, String str2){
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < m+1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][m];
    }

    //Tabulation Approach Space Optimized
    public static int LCSTabSO(String str1, String str2){
        int n = str1.length();
        int m = str2.length();
        int[] dp = new int[m+1];
        for (int i = 0; i < m+1; i++) {
            dp[i] = 0;
        }
        for (int i = 1; i < n+1; i++) {
            int[] cur = new int[m+1];
            for (int j = 1; j < m+1; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    cur[j] = 1+dp[j-1];
                }
                else cur[j] = Math.max(dp[j], cur[j-1]);
            }
            dp = cur;
        }
        return dp[m];
    }

    //Printing the LCS by Backtracking the DP Table
    public static void printLCS(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < m+1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int len = dp[n][m];
        char[] lcs = new char[len];
        len--;
        int i=n;
        int j=m;
        while (i>0 && j>0) {
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                lcs[len] = str1.charAt(i-1);
                len--;
                i--;j--;
            }
            else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }
            else j--;
        }
        System.out.println(lcs);
    }

    public static void main(String[] args) {
        String a = "abcde";
        String b = "pacpe";
        int size = 5;
        int[][] dp = new int[size+1][size+1];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        System.out.println(LCSTabSO(a, b));
        printLCS(a, b);
    }
}