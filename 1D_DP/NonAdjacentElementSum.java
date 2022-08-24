import java.util.*;
class NonAdjacentElementSum{

    //Non Optimized Solution
    public int maxSum(int idx, int[] arr){
        if(idx<0) return 0;
        if(idx==0) return arr[0];
        
        int pick = maxSum(idx-2, arr) + arr[idx];
        int notpick = maxSum(idx-1, arr);
        return Math.max(pick, notpick);
    }

    // Memoization Approach
    public int maxSumMemo(int idx, int[] arr, int[] dp){
        if(idx<0) return 0;
        if(idx==0) return arr[0];

        if(dp[idx]!=-1) return dp[idx];
        int pick = maxSumMemo(idx-2, arr,dp) + arr[idx];
        int notpick = maxSumMemo(idx-1, arr,dp);
        dp[idx] = Math.max(pick, notpick);
        return dp[idx];
    }

    // Tabulation Approach
    public int maxSumTab(int idx, int[] arr){
        int[] dp = new int[idx+1];
        dp[0]  =arr[0];
        for (int i = 1; i < dp.length; i++) {
            int pick = arr[i];
            if(i>1) pick+=dp[i-2];
            int notpick = dp[i-1];
            dp[i] = Math.max(pick, notpick);
        }
        return dp[idx];
    }

    // Space Optimized Tabulation
    public int maxSumTabSO(int idx, int[] arr){
        int prev2 = 0;
        int prev  = arr[0];
        for (int i = 1; i <= idx; i++) {
            int pick = arr[i];
            if(i>1) pick+=prev2;
            int notpick = prev;
            int curr = Math.max(pick, notpick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        NonAdjacentElementSum obj = new NonAdjacentElementSum();
        int[] dp = new int[1001];
        Arrays.fill(dp, -1);
        System.out.println(obj.maxSumTabSO(0, new int[]{10}));   //10
        Arrays.fill(dp, -1);
        System.out.println(obj.maxSumTabSO(4, new int[]{9,9,3,4,0})); //13
        Arrays.fill(dp, -1);
        System.out.println(obj.maxSumTabSO(3, new int[]{2,1,4,9}));  //11
    }
}