import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * lengthLIS
 */
public class lengthLIS {

    // Recursion Approach
    public int func(int ind, int prev_ind, int[] arr){
        if(ind == arr.length) return 0;
        int nottake = 0+func(ind+1, prev_ind, arr);
        if(prev_ind==-1 || arr[ind]>arr[prev_ind]) return Math.max(nottake, 1+func(ind+1, ind, arr));
        return nottake;
    }

    // Memoization Approach
    public int funcMemo(int ind, int prev_ind, int[] arr, int[][] dp){
        if(ind == arr.length) return 0;
        if(dp[ind][prev_ind+1]!=-1) return dp[ind][prev_ind+1];
        int nottake = 0+funcMemo(ind+1, prev_ind, arr,dp);
        if(prev_ind==-1 || arr[ind]>arr[prev_ind]) {
            dp[ind][prev_ind]= Math.max(nottake, 1+funcMemo(ind+1, ind, arr,dp));
            return dp[ind][prev_ind];
        }
        dp[ind][prev_ind] = nottake;
        return dp[ind][prev_ind];
    }


    // Tabulation Approach
    public int getLength(int[] arr){
        int[][] dp = new int[arr.length+1][arr.length+1];
        for (int ind = arr.length-1; ind >= 0; ind--) {
            for(int prev_ind = ind-1; prev_ind>=-1 ; prev_ind--){
                int notTake = 0 + dp[ind+1][prev_ind +1];
                int take = 0;
                if(prev_ind == -1 || arr[ind] > arr[prev_ind]){
                    
                    take = 1 + dp[ind+1][ind+1];
                }
                dp[ind][prev_ind+1] = Math.max(notTake,take);
            }
        }
        return dp[0][0];
    }

    // Tabulation Space Optimized Approach
    public int getLengthSO(int[] arr){
        int[] dp = new int[arr.length+1];
        for (int ind = arr.length-1; ind >= 0; ind--) {
            int[] cur = new int[arr.length+1];
            for(int prev_ind = ind-1; prev_ind>=-1 ; prev_ind--){
                int notTake = 0 + dp[prev_ind +1];
                int take = 0;
                if(prev_ind == -1 || arr[ind] > arr[prev_ind]){
                    
                    take = 1 + dp[ind+1];
                }
                cur[prev_ind+1] = Math.max(notTake,take);
            }
            dp = cur;
        }
        return dp[0];
    }

    // Another Tabulation Approach
    public int getLengthSO2(int[] arr){
        int[] dp = new int[arr.length];
        for (int ind = 0; ind < arr.length; ind++) {
            for(int prev_ind = 0; prev_ind < ind ; prev_ind++){
                if(arr[ind]>arr[prev_ind]) dp[ind] = Math.max(dp[ind], 1+dp[prev_ind]);
            }
        }
        int retVal = Integer.MIN_VALUE;
        for(int ele:dp) retVal = Math.max(retVal,ele);
        return retVal;
    }

    // Most Optimized LIS Algorithm
    public int length_LIS(int[] nums) {
        int[] l = new int[nums.length];
        Arrays.fill(l,Integer.MAX_VALUE);
        l[0] = nums[0];
        int len=1;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]>l[len-1]){
                l[len++] = nums[i];
            }
            else{
                int ind = Arrays.binarySearch(l, nums[i]);
                System.out.println(ind);
                if(ind<0) ind = -(ind+1);
                l[ind] = nums[i];
            }
        }
        return len;
    }
}