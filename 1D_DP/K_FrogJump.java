import java.util.Arrays;

// Classic Frog Jump problem with a twist. the twist being that a frog can jump K places ahead at a given moment.
public class K_FrogJump {

    // Non Optimized Solution
    public int frogJump(int n, int[] heights,int K){
        if(n<=0) return 0;
        int minJump = Integer.MAX_VALUE;
        for(int i=1;i<=K;i++){
            if(n-i>=0){
                int jump = frogJump(n-i, heights, K)+Math.abs(heights[n]-heights[n-i]);
                minJump = Math.min(minJump, jump);
            }
        }
        return minJump;
    }

    // Memoization Approach
    public int frogJumpMemo(int n, int[] heights,int K, int[] dp){
        if(n<=0) return 0;
        if(dp[n]!=-1) return dp[n];
        int minJump = Integer.MAX_VALUE;
        for(int i=1;i<=K;i++){
            if(n-i>=0){
                int jump = frogJumpMemo(n-i, heights, K,dp)+Math.abs(heights[n]-heights[n-i]);
                minJump = Math.min(minJump, jump);
            }
        }
        dp[n] = minJump;
        return dp[n];
    }


    //Tabulation Approach
    public int frogJumpTab(int n, int[] heights, int k){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<=n;i++)
        {
            int minJump = Integer.MAX_VALUE;
            for(int j=1;j<=k;j++)
            {
                if(i-j>=0)
                {
                    minJump = Math.min(minJump, dp[i-j] +Math.abs(heights[i]-heights[i-j]));
                }
            }
            dp[i] = minJump;
        }
        return dp[n];
    } 

    // Space Optimization further is not possible as at worst case K=N and auxillary array would any ways will be O(N)

    //Helper Function
    public static void main(String[] args) {
        int[] dp = new int[100];
        K_FrogJump obj = new K_FrogJump();
        Arrays.fill(dp, -1);
        System.out.println(obj.frogJumpTab(3, new int[]{10,20,30,10},2));
        Arrays.fill(dp, -1);
        System.out.println(obj.frogJumpTab(2, new int[]{10,50,10},2));
        Arrays.fill(dp, -1);
        System.out.println(obj.frogJumpTab(98, new int[]{
            34,31,6,5,33,34,28,38,29,11,10,40,7,30,3,27,25,48,8,
            17,10,3,46,18,6,1,14,33,3,44,18,10,4,40,28,14,35,19,
            43,22,41,27,37,50,40,18,22,9,34,34,27,3,30,43,20,12,6,
            12,27,19,18,21,23,4,45,25,27,40,16,12,18,34,26,34,4,4,
            30,35,26,35,11,13,21,46,7,12,45,8,24,38,48,33,6,19,26,
            50,43,21,18 
        },2));
    }
}
