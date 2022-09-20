// import java.util.Arrays;

public class Triangle {
    //Standard Recurisve Solution
    public int bestPath(int i, int j, int[][] grid){
        if(i==grid.length-1) return grid[i][j];
        int down = grid[i][j] + bestPath(i+1, j, grid);
        int diagonal = grid[i][j] + bestPath(i+1, j+1, grid);
        return Math.min(down,diagonal);
    }

    //Memoization Solution
    public int bestPathMemo(int i, int j, int[][] grid, int[][] dp) {
        if(i==grid.length-1) return grid[i][j];
        if(dp[i][j]!=-1) return dp[i][j];
        int down = grid[i][j] + bestPath(i+1, j, grid);
        int diagonal = grid[i][j] + bestPath(i+1, j+1, grid);
        dp[i][j] = Math.min(down,diagonal);
        return dp[i][j];
    }

    // Tabulation Solution
    public int bestPathTab(int i, int j, int[][] grid){
        int[][] dp = new int[grid.length][grid.length];
        for(int y=grid.length-1;y>=0;y--){
            dp[grid.length-1][y] = grid[grid.length-1][y];
        }
        for(int m=grid.length-2;m>=0;m--){
            for(int n=m;n>=0;n--){
                int down = grid[m][n] + dp[m+1][n];
                int diagonal = grid[m][n] + dp[m+1][n+1];
                dp[m][n] = Math.min(down, diagonal);
            }
        }
        return dp[0][0];
    }

    // Space Optimized Solution
    public int bestPathTabSO(int n, int[][] grid) {
        int[] dp = new int[n];
        for(int j=0;j<n;j++) dp[j] = grid[n-1][j];
        for (int i = n-2; i >=0 ; i--) {
            int[] curr = new int[n];
            for (int j = i; j >= 0; j--) {
                int down = grid[i][j] + dp[j];
                int diagonal = grid[i][j] + dp[j+1];
                curr[j] = Math.min(down, diagonal);
            }
            dp = curr;
        }
        return dp[0];
    }
    public static void main(String[] args) {
        Triangle obj = new Triangle();
        // System.out.println(obj.bestPath(0,0,new int[][]{{2,0,0,0},{3,4,0,0},{6,5,7,0},{4,1,8,3}}));//11
        // int dp[][]=new int[5][5];
        // for(int row[]: dp)
        //     Arrays.fill(row,-1);
        // System.out.println(obj.bestPathMemo(0,0, new int[][]{{2,0,0,0},{3,4,0,0},{6,5,7,0},{4,1,8,3}}, dp));
        // System.out.println(obj.bestPathTab(0, 0, new int[][]{{2,0,0,0},{3,4,0,0},{6,5,7,0},{4,1,8,3}}));
        System.out.println(obj.bestPathTabSO(4, new int[][]{{2,0,0,0},{3,4,0,0},{6,5,7,0},{4,1,8,3}}));
    }
}
