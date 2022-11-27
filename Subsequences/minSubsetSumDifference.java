// import java.util.Arrays;

public class minSubsetSumDifference {
    public static void main(String[] args) {
        minSubsetSumDifference obj = new minSubsetSumDifference();
        int[] seq = new int[]{1,2,3,4};
        int[] se = new int[]{8,6,5};
        System.out.println(obj.getMinDiff(seq,seq.length-1));//0
        System.out.println(obj.getMinDiff(se,se.length-1));//3
    }

    private int getMinDiff(int[] arr, int n) {
        int totalSum = 0;
        for (int i : arr) {
            totalSum+=i;
        }
        boolean[][] dp = new boolean[n+1][totalSum+1];
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < n+1; i++) {
            for (int tar = 1; tar < totalSum+1; tar++) {
                boolean notTake = dp[i-1][tar];
                boolean take = false;
                if(arr[i]<=tar) take = dp[i-1][tar-arr[i]];
                dp[i][tar] = take | notTake;
            }
        }
        int mini = (int)1e9;
        for (int i = 0; i < totalSum+1; i++) {
            if(dp[n-1][i]==true){
                int s2 = totalSum-i;
                mini = Math.min(Math.abs(i-s2), mini);
            }
        }
        return mini;
    }    
}
