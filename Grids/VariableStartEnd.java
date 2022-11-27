import java.util.Arrays;

/**
 * VariableStartEnd
 */
public class VariableStartEnd {


    // Standard Recursive Solution
    public int getMaxSum(int i, int j, int[][] grid) {
        if(j<0 || j>=grid[0].length) return -1*(int)Math.pow(10, 8);
        if(i==0) return grid[i][j];
        int u = grid[i][j] + getMaxSum(i-1, j, grid);
        int lu = grid[i][j] + getMaxSum(i-1, j-1, grid);
        int ru = grid[i][j] + getMaxSum(i-1, j+1, grid);
        return Math.max(u,Math.max(lu,ru));
    }
    public int maxPathSum(int[][] grid){
        int maxi = -1*(int)Math.pow(10, 8);
        for(int j=0;j<grid[0].length;j++){
            maxi = Math.max(maxi, getMaxSum(grid.length-1, j, grid));
        }
        return maxi;
    }

    //Memoization Solution
    public int getMaxSumMemo(int i, int j, int[][] grid, int[][] dp) {
        if(j<0 || j>=grid[0].length) return -1*(int)Math.pow(10, 8);
        if(i==0) return grid[i][j];
        if(dp[i][j]!=-1) return dp[i][j];
        int u = grid[i][j] + getMaxSum(i-1, j, grid);
        int lu = grid[i][j] + getMaxSum(i-1, j-1, grid);
        int ru = grid[i][j] + getMaxSum(i-1, j+1, grid);
        dp[i][j] = Math.max(u,Math.max(lu,ru));
        return dp[i][j];
    }
    public int maxPathSumMemo(int[][] grid) {
        int maxi = -1*(int)Math.pow(10, 8);
        int[][] dp = new int[grid.length+1][grid[0].length+1];
        for (int[] is : dp) {
            Arrays.fill(is,-1);
        }
        for(int j=0;j<grid[0].length;j++){
            maxi = Math.max(maxi, getMaxSumMemo(grid.length-1, j, grid,dp));
        }
        return maxi;
    }

    //Tabulation Solution
    public int  maxPathSumTab(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for(int j=0;j<grid[0].length;j++) dp[0][j] = grid[0][j];
        for(int i=1;i<grid.length;i++){
            for (int j = 0; j < grid[0].length; j++) {
                int u = grid[i][j] +  dp[i-1][j];  
                int lu = grid[i][j];
                if(j-1>=0) lu += dp[i-1][j-1];
                int ru = grid[i][j];
                if(j+1<grid[0].length) ru += dp[i-1][j+1];
                dp[i][j] = Math.max(u,Math.max(lu,ru));
            }
        }
        int maxi=dp[grid[0].length-1][0];
        for(int j=1;j<grid[0].length;j++) maxi = Math.max(maxi, dp[grid[0].length-1][j]);
        return maxi;
    }
    public static void main(String[] args) {
        VariableStartEnd obj = new VariableStartEnd();
        System.out.println(obj.maxPathSumTab(new int[][]{{1,2,10,4},{100,3,2,1},{1,1,20,2},{1,2,2,1}}));//105
    }
}