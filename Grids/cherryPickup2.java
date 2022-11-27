import java.util.Arrays;

public class cherryPickup2 {
    public int getMaxCherries(int r, int c, int[][] grid) {
        int[][][] dp = new int[r+1][c+1][c+1];
        for(int[][] a:dp){
            for(int[] b:a){
                Arrays.fill(b,-1);
            }
        }
        return funcMemo(0,0,c-1,r,c,grid,dp);
    }
    // public int func(int i, int j1, int j2, int r, int c, int[][] grid){
    //     if(j1<0 || j2<0 || j1>=c || j2>=c){
    //         return (int)-1e8;
    //     }
    //     if(i==r-1){
    //         if(j1==j2) return grid[i][j1];
    //         else return grid[i][j1] + grid[i][j2];
    //     }
    //     int maxres = Integer.MIN_VALUE;
    //     for(int dj1=-1;dj1<=1;dj1++){
    //         for (int dj2 = -1; dj2 <= 1; dj2++) {
    //             int val = grid[i][j1];
    //             if(j1!=j2) val += grid[i][j2];
    //             val+= func(i+1,j1+dj1,j2+dj2,r,c,grid);
    //             maxres = Math.max(val, maxres);
    //         }
    //     }
    //     return maxres;
    // }

    public int funcMemo(int i, int j1, int j2, int r, int c, int[][] grid,int[][][] dp){
        if(j1<0 || j2<0 || j1>=c || j2>=c){
            return (int)-1e8;
        }
        if(i==r-1){
            if(j1==j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }
        if(dp[i][j1][j2]!=-1) return dp[i][j1][j2];
        int maxres = Integer.MIN_VALUE;
        for(int dj1=-1;dj1<=1;dj1++){
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int val = grid[i][j1];
                if(j1!=j2) val += grid[i][j2];
                val+= funcMemo(i+1,j1+dj1,j2+dj2,r,c,grid,dp);
                maxres = Math.max(val, maxres);
            }
        }
        dp[i][j1][j2] = maxres;
        return dp[i][j1][j2];
    }

    public int getMaxCherriesTab(int r, int c, int[][] grid){
        int[][][] dp = new int[r][c][c];
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
              if (j1 == j2)
                dp[r - 1][j1][j2] = grid[r - 1][j1];
              else
                dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
            }
        }
        for(int i = r-2;i>=0;i--){
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {


                    int maxres = Integer.MIN_VALUE;
                    for(int dj1=-1;dj1<=1;dj1++){
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int val = grid[i][j1];
                            if(j1!=j2) val += grid[i][j2];
                            if ((j1 + dj1 < 0 || j1 + dj1 >= c) ||
                                (j2 + dj2 < 0 || j2 + dj2 >= c)) 
                                val+=-1e9;
                            else
                                val+= dp[i+1][j1+dj1][j2+dj2];
                            maxres = Math.max(val, maxres);
                        }
                    }

                    dp[i][j1][j2] = maxres;
                }
            }
        }
        return dp[0][0][c-1];
    }

    public static void main(String[] args) {
        cherryPickup2 obj = new cherryPickup2();
        System.out.println(obj.getMaxCherriesTab(4, 3, new int[][]{{3,1,1},{2,5,1},{1,5,5},{2,1,1}}));//24

    }
}
