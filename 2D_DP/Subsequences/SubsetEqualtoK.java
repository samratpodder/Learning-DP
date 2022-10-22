
public class SubsetEqualtoK {
    public boolean hasSubset(int n, int target, int arr[]){
        if(target==0) return true;
        if(n==0) return target==arr[0];
        boolean notpick = hasSubset(n-1, target, arr);
        boolean pick = false;
        if(arr[n]<=target) pick = hasSubset(n-1, target-arr[n], arr);
        return pick|notpick;
    }
    public boolean hasSubsetMemo(int n, int target, int arr[], int[][] dp){
        if(target==0) return true;
        if(n==0) return target==arr[0];
        if(dp[n][target]!=-1) return dp[n][target]==0?false:true;
        boolean notpick = hasSubsetMemo(n-1, target, arr,dp);
        boolean pick = false;
        if(arr[n]<=target) pick = hasSubsetMemo(n-1, target-arr[n], arr,dp);
        dp[n][target] = pick|notpick?1:0; 
        return pick|notpick;
    }
    private boolean hasSubsetTab(int n, int target, int[] arr) {
        boolean[][] dp = new boolean[n][target+1];
        for(int i =0;i<n;i++) dp[i][0] = true;
        if(arr[0]<=target) dp[0][arr[0]] = true;
        for(int index = 1;index<n;index++){
            for(int targ = 1; targ<=target;targ++){
                boolean notTaken = dp[index-1][targ];
    
                boolean taken = false;
                if(arr[index]<=targ)
                    taken = dp[index-1][targ-arr[index]];
            
                dp[index][targ]= notTaken||taken;
            }
        }
        return dp[n-1][target];
    }  
    static boolean subsetSumToK(int n, int k,int[] arr){
    
    
        boolean dp[][]= new boolean[n][k+1];
        
        for(int i=0; i<n; i++){
            dp[i][0] = true;
        }
        
        if(arr[0]<=k)
            dp[0][arr[0]] = true;
        
        for(int ind = 1; ind<n; ind++){
            for(int target= 1; target<=k; target++){
                
                boolean notTaken = dp[ind-1][target];
        
                boolean taken = false;
                    if(arr[ind]<=target)
                        taken = dp[ind-1][target-arr[ind]];
            
                dp[ind][target]= notTaken||taken;
            }
        }
        
        return dp[n-1][k];
    }
    public static void main(String[] args) {
        SubsetEqualtoK obj = new SubsetEqualtoK();
        // int[][] dp = new int[10001][10001];
        // for (int[] is : dp) {
        //     Arrays.fill(is, -1);
        // }
        System.out.println(obj.hasSubsetTab(4-1, 5, new int[]{4,3,2,1}));//True
        // for (int[] is : dp) {
        //     Arrays.fill(is, -1);
        // }
        System.out.println(obj.hasSubsetTab(5-1, 4, new int[]{2,5,1,6,7}));//False
    }
}
