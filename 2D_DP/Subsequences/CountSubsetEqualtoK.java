import java.util.Arrays;

public class CountSubsetEqualtoK {
    public int count(int i, int[] num, int tar){
        if(tar==0) return 1;
        if(i==num.length-1) return num[i]==tar?1:0;
        int pick = count(i+1, num, tar-num[i]);
        int notpick = count(i+1, num, tar);
        return pick + notpick;
    }

    public int countMemo(int i, int[] num, int tar, int[][] dp){
        if(tar==0) return 1;
        if(i==num.length-1) return num[i]==tar?1:0;
        if(dp[i][tar]!=-1) return dp[i][tar];
        int pick = count(i+1, num, tar-num[i]);
        int notpick = count(i+1, num, tar);
        dp[i][tar] = pick + notpick;
        return dp[i][tar];
    }

    public int countTab(int []num, int tar) {
        int[][] dp = new int[num.length][tar+1];
        for(int i=0;i<num.length;i++) dp[i][0]=1;
        if(num[0]<=tar) dp[0][num[0]] = 1;
        for(int target = 1;target<=tar;target++){
            for (int i = 1; i < dp.length; i++) {
                int taken = 0;
                if(num[i]<=target) taken+=dp[i-1][target-num[i]];
                int nottaken = dp[i-1][target];
                dp[i][target] = taken+nottaken;
            }
        }
        return dp[num.length-1][tar];
    }

    public int countTabSO(int[] num, int k){
        int n = num.length;
        int prev[]=new int[k+1];
        prev[0] =1;
        if(num[0]<=k) prev[num[0]] = 1;
        for(int ind = 1; ind<n; ind++){
            int cur[]=new int[k+1];
            cur[0]=1;
            for(int target= 1; target<=k; target++){
                int notTaken = prev[target];
                int taken = 0;
                if(num[ind]<=target) taken = prev[target-num[ind]];
                cur[target]= notTaken + taken;
            }
            prev = cur;
        }
        return prev[k];
    }

    public static void main(String[] args) {
        int[][] dp = new int[1000][1000];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        int[] s = new int[]{1,2,3,3};
        int tar = 6;
        CountSubsetEqualtoK obj = new CountSubsetEqualtoK();
        // System.out.println(obj.countMemo(0,s, tar,dp));
        System.out.println(obj.countTabSO(s, tar));
    }
}
