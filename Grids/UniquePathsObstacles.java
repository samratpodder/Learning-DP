
public class UniquePathsObstacles {

    // Standard Recursive Approach
    public int getPaths(int n, int m, int[][] mat){
        if(n==0 && m==0) return 1;
        if(n<0 || m<0) return 0;
        if(mat[n][m]==-1) return 0;
        int up = getPaths(n-1, m, mat);
        int left = getPaths(n, m-1, mat);
        return up+left;
    }

    // Memoization Approach
    public int getPathsMemo(int n, int m, int[][] mat, int[][] dp){
        if(n==0 && m==0) return 1;
        if(n<0 || m<0) return 0;
        if(mat[n][m]==-1) return 0;
        if(dp[n][m]!=-1) return dp[n][m];
        int up = getPaths(n-1, m, mat);
        int left = getPaths(n, m-1, mat);
        dp[n][m] = up+left;
        return dp[n][m];
    }

    // Tabulation Approach
    public int getPathsTab(int n, int m, int[][] mat){
        int[][] dp = new int[n][m];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = 1;continue;
                }
                int left=0,up=0;
                if(i>0){
                    up+=dp[i-1][j];
                }
                if(j>0){
                    left+=dp[i][j-1];
                }
                dp[i][j] = up+left;
            }
        }
        return dp[n-1][m-1];
    }

    // Tabulation Space Optimized
    public int getPathsTabSO(int n, int m, int[][] mat){
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int[] temp = new int[m];
            for (int j = 0; j < m; j++) {
                if(i==0 && j==0) {
                    temp[j] = 1;continue;
                }
                int left=0,up=0;
                if(i>0){
                    up+=dp[j];
                }
                if(j>0){
                    left+=temp[j-1];
                }
                temp[j] = up+left;
            }
            dp=temp;
        }
        return dp[n-1];
    }
    public static void main(String[] args) {
        UniquePathsObstacles obj = new UniquePathsObstacles();
        // int[][] dp = new int[50][50];
        // for (int[] is : dp) {
        //     Arrays.fill(is, -1);
        // }
        System.out.println(obj.getPathsTabSO(3-1, 3-1, new int[][]{{0,0,0},{0,-1,0},{0,0,0}}));//2

    }
}
