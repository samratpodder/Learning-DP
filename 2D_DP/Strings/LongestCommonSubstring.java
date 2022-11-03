class LongestCommonSubstring {
    
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
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j] = 1+dp[i-1][j-1];
                    ans = Math.max(ans,dp[i][j]);
                }
                else dp[i][j] = 0;
            }
        }
        return ans;
    }

    //Tabulation Approach Space Optimized
    public static int LCSTabSO(String str1, String str2){
        int n = str1.length();
        int m = str2.length();
        int[] dp = new int[m+1];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++) {
            int[] cur = new int[m+1];
            for (int j = 1; j < m+1; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    cur[j] = 1+dp[j-1];
                    ans = Math.max(ans, cur[j]);
                }
                else cur[j] = 0;
            }
            dp = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        String a = "abcdjjhf";
        String b = "ppsjhfmolcdjj";
        System.out.println(LCSTab(a, b));
        System.out.println(LCSTabSO(a, b));
    }
}
