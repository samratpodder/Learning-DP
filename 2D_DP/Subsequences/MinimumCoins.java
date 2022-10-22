import java.util.Arrays;

/**
 * MinimumCoins
 */
public class MinimumCoins {

    public static void main(String[] args) {
        int[] change = new int[]{12,1,3};
        int target = 4;
        System.out.println(new MinimumCoins().countCoins(change,target,change.length-1));
        change= new int[]{2,1};
        target=11;
        System.out.println(new MinimumCoins().countCoins(change,target,change.length-1));
        int[][] dp = new int[change.length+1][target+1];
        for (int[] is : dp) {
            Arrays.fill(is, -1);
        }
        System.out.println(new MinimumCoins().countCoinsMemo(change,target,change.length-1,dp));
        System.out.println(new MinimumCoins().countCoinsTab(change,target));
        System.out.println(new MinimumCoins().countCoinsSO(change,target));
    }

    private int countCoins(int num[], int x, int idx) {
        if(idx==0){
            if(x%num[0]==0) return x/num[0];
            else return (int)1e8;
        }
        int take = (int)1e8;
        if(x-num[idx]>=0) take=1+countCoins(num, x-num[idx], idx-1);
        int nottake = countCoins(num, x, idx-1);
        return Math.min(take,nottake);
    }

    private int countCoinsMemo(int num[], int x, int idx, int[][]dp) {
        if(idx==0){
            if(x%num[0]==0) return x/num[0];
            else return (int)1e8;
        }
        if(dp[idx][x]!=-1) return dp[idx][x];
        int take = (int)1e8;
        if(x-num[idx]>=0) take=1+countCoins(num, x-num[idx], idx-1);
        int nottake = countCoins(num, x, idx-1);
        dp[idx][x] = Math.min(take,nottake);
        return dp[idx][x];
    }

    private int countCoinsTab(int num[], int x) {
        int[][] dp = new int[num.length][x+1];
        for(int i=0;i<=x;i++){
            dp[0][i]= i%num[0]==0?i/num[0]:(int)1e8;
        }

        for(int s=0;s<=x;s++){
            for(int i=1;i<num.length;i++)
            {
                int take = (int)1e8;
                if(s-num[i]>=0) take=1+dp[i-1][s-num[i]];
                int nottake = dp[i-1][s];
                dp[i][s] =  Math.min(take,nottake);
            }
        }
        return dp[num.length-1][x];
    }

    private int countCoinsSO(int num[], int x) {
        int[] dp = new int[x+1];
        for(int i=0;i<=x;i++){
            dp[i]= i%num[0]==0?i/num[0]:(int)1e8;
        }
        for(int i=1;i<num.length;i++)
        {
            int[] curr = new int[x+1];
            for(int s=0;s<=x;s++)
            {
                int take = (int)1e8;
                if(s-num[i]>=0) take=1+dp[s-num[i]];
                int nottake = dp[s];
                curr[s] =  Math.min(take,nottake);
            }
            dp = curr;
        }
        return dp[x]>(int)10e8?-1:dp[x];
    }
}