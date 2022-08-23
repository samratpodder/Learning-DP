import java.util.Arrays;

/**
 * FrogJump
 * Sample Input 1:
2
4

10 20 30 10
3
10 50 10
Sample Output 1:
20
0
Explanation Of Sample Input 1:
For the first test case,
The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
Then a jump from the 2nd stair to the last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20 which is the minimum. 
Hence, the answer is 20.

For the second test case:
The frog can jump from 1st stair to 3rd stair (|10-10| = 0 energy lost).
So, the total energy lost is 0 which is the minimum. 
Hence, the answer is 0.
 */
public class FrogJump {

    // Non Optimized Solution
    public int frogJump(int n, int[] heights){
        if(n<=0) return 0;
        int jump1 = Math.abs(heights[n]-heights[n-1]) + frogJump(n-1, heights);
        int jump2=Integer.MAX_VALUE;
        if(n>1) jump2 = Math.abs(heights[n]-heights[n-2]) + frogJump(n-2, heights);
        return Math.min(jump1, jump2);
    }



    // Memoization Approach
    public int frogjumpMemo(int n, int[] heights, int[] dp){
        if(n==0) return 0;
        if (dp[n]!=-1) {
            return dp[n];
        }
        int jump1 = Math.abs(heights[n]-heights[n-1]) + frogjumpMemo(n-1, heights,dp);
        int jump2=Integer.MAX_VALUE;
        if(n>1) jump2 = Math.abs(heights[n]-heights[n-2]) + frogjumpMemo(n-2, heights,dp);
        dp[n] = Math.min(jump1, jump2);
        return dp[n];
    } 

    // Tabulation Approach
    public int frogjumpTab(int n, int[] heights){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<=n;i++)
        {
            int jump1 = dp[i-1]+Math.abs(heights[i]-heights[i-1]);
            int jump2 = Integer.MAX_VALUE;
            if(i>1) jump2 = dp[i-2]+Math.abs(heights[i]-heights[i-2]);
            dp[i] = Math.min(jump1,jump2);
        }
        return dp[n];
    }


    // Space Optimized Tabulation Approach
    public int frogjumpTabSO(int n, int[] heights){
        
        int prev = 0;
        int prev2 = 0;
        for(int i=1;i<=n;i++)
        {
            int jump1 =prev+Math.abs(heights[i]-heights[i-1]);
            int jump2 =Integer.MAX_VALUE;
            if(i>1) jump2 = prev2+Math.abs(heights[i]-heights[i-2]);
            int curr = Math.min(jump1,jump2);
            prev2 = prev;
            prev=curr;
        }
        return prev;
    }



    //Helper Function
    public static void main(String[] args) {
        int[] dp = new int[100];
        FrogJump obj = new FrogJump();
        Arrays.fill(dp, -1);
        System.out.println(obj.frogjumpTabSO(3, new int[]{10,20,30,10}));
        Arrays.fill(dp, -1);
        System.out.println(obj.frogjumpTabSO(2, new int[]{10,50,10}));
        Arrays.fill(dp, -1);
        System.out.println(obj.frogjumpTabSO(98, new int[]{
            34,31,6,5,33,34,28,38,29,11,10,40,7,30,3,27,25,48,8,
            17,10,3,46,18,6,1,14,33,3,44,18,10,4,40,28,14,35,19,
            43,22,41,27,37,50,40,18,22,9,34,34,27,3,30,43,20,12,6,
            12,27,19,18,21,23,4,45,25,27,40,16,12,18,34,26,34,4,4,
            30,35,26,35,11,13,21,46,7,12,45,8,24,38,48,33,6,19,26,
            50,43,21,18 
        }));
    }
}