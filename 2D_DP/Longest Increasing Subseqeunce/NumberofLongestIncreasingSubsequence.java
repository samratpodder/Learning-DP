import java.util.Arrays;

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] ct = new int[n];
        int maxi = -1;
        Arrays.fill(dp, 1);
        Arrays.fill(ct, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j]<nums[i] && 1+dp[j]>dp[i]){
                    dp[i] = 1+dp[j];
                    ct[i] = ct[j];
                }
                else if(nums[j]<nums[i] && 1+dp[j]==dp[i]){
                    ct[i] += ct[j];
                }

            }
            maxi = Math.max(maxi, dp[i]);
        }
        int c=0;
        for (int i=0;i<n;i++) {
            if (dp[i]==maxi) {
                c+=ct[i];
            }
        }
        return c;
    }
}