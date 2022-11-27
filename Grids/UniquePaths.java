public class UniquePaths {

    // Basic Recursive Solution
    public int getPaths(int m, int n){
        if(m<1 || n<1) return 0;
        if(m==1 && n==1) return 1;
        
        int left = getPaths(m, n-1);
        int up = getPaths(m-1, n);
        return left+up;
    }

    // Memoization Solution
    public int getPathsMemo(int m, int n, int[][] dp){
        if(m<0 || n<0) return 0;
        if(m==0 && n==0) return 1;
        if (dp[m][n]!=-1) {
            return dp[m][n];
        }
        int up =   getPathsMemo(m-1,n,dp);
        int left = getPathsMemo(m,n-1,dp);
        dp[m][n] = up+left;
        return dp[m][n];
    }

    // Tabualtion Solution
    public int getPathsTab(int m, int n){
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 || j==0){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    // Tabulation Solution Space Optimized
    public int getPathsTabSO(int m, int n){
        int[] dp = new int[n];
        for(int i=0;i<m;i++){
            int temp[] = new int[n];
            for(int j=0;j<n;j++){
                if(i==0 && j==0){
                    temp[j] = 1;
                }
                else{
                    if(i>0) temp[j] += dp[j];
                    if(j>0) temp[j] += temp[j-1];
                }
            }
            dp = temp;
        }
        return dp[n-1];
    }


    // Competitive Coding Solution
    public int getPathsCP(int m, int n)
    {
        int N = m+n-2;
        int r = m-1;
        double res =1;
        for(int i=1;i<=r;i++){
            res = res*(N-r+i)/i;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        UniquePaths obj = new UniquePaths();
        // int[][] dp = new int[1000][1000]; Used for Memoization
        // for(int[] ar:dp){
        //     Arrays.fill(ar, -1);
        // }
        System.out.println(obj.getPathsCP(3, 2));//3
        // for(int[] ar:dp){
        //     Arrays.fill(ar, -1);
        // }
        System.out.println(obj.getPathsCP(1, 6));//1
        // for(int[] ar:dp){
        //     Arrays.fill(ar, -1);
        // }
        System.out.println(obj.getPathsCP(1, 1));//1



    }
}
