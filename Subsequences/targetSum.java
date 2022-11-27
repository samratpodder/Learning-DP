import java.util.Arrays;

/**
 * targetSum
 */
public class targetSum {

    // Recursive Approach
    public static int getWays(int idx, int targetSum, int[] nums){
        if(idx==0){
            if(Math.abs(targetSum)==nums[0]) return 1;
            else return 0;
        }
        int plus = getWays(idx-1, targetSum-nums[idx], nums);
        int minus = getWays(idx-1, targetSum+nums[idx], nums);
        return plus+minus;
    }


    // Memoization Approach
    public static int getWaysMemo(int[] nums, int target){
        int total=Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2*total+1];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        return memoHelper(0,0,target, nums, dp,total);
    }
    public static int memoHelper(int idx, int sum, int targetSum, int[] nums, int[][] dp,int total){
        if(idx==nums.length){
            if(targetSum==sum) return 1;
            else return 0;
        }
        if(dp[idx][total+sum]!=-1) return dp[idx][total+sum];
        int plus = memoHelper(idx+1,sum+nums[idx],targetSum, nums,dp,total);
        int minus= memoHelper(idx+1,sum-nums[idx],targetSum, nums,dp,total);
        dp[idx][total+sum] = plus+minus;
        return dp[idx][total+sum];
    }

    

    // Tabulation Approach
    public static int getWaysTab(int[] nums, int target){
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2*total+1];
        dp[0][total+nums[0]] += 1;
        dp[0][total-nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -total; sum <= total; sum++) {
                if (dp[i - 1][sum + total] > 0){
                    dp[i][sum + total + nums[i]] += dp[i - 1][sum + total];
                    dp[i][sum + total - nums[i]] += dp[i - 1][sum + total];
                }
            }
        }
        return Math.abs(target) > total ? 0 : dp[nums.length - 1][target + total];
    }

    // Space Optimized Tabulation
    public static int getWaysTabSO(int[] nums, int target){
        int total = Arrays.stream(nums).sum();
        int[] dp = new int[2*total+1];
        dp[total+nums[0]] += 1;
        dp[total-nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] curr = new int[2*total+1];
            for (int sum = -total; sum <= total; sum++) {
                if (dp[sum + total] > 0){
                    curr[sum + total + nums[i]] += dp[sum + total];
                    curr[sum + total - nums[i]] += dp[sum + total];
                }
            }
            dp = curr;
        }
        return Math.abs(target) > total ? 0 : dp[target + total];
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,1,1};
        int n = 5;
        int sum = 3;
        System.out.println(getWays(n-1,sum,arr));
        System.out.println(getWaysMemo(arr,sum));
    }
}