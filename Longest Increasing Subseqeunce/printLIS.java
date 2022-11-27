import java.util.Arrays;

public class printLIS {
    public static void print_LIS(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int[] hash = new int[arr.length];
        for (int ind = 0; ind < arr.length; ind++) {
            hash[ind] = ind;
            for(int prev_ind = 0; prev_ind < ind ; prev_ind++){
                if(arr[ind]>arr[prev_ind] && dp[ind]<dp[prev_ind]+1) {
                    dp[ind] = 1+dp[prev_ind];
                    hash[ind] = prev_ind;
                }
            }

        }
        int retVal = Integer.MIN_VALUE;
        int lastInd = -1;
        for(int i=0;i<dp.length;i++) {
            if(retVal<dp[i]) {
                retVal =dp[i];
                lastInd = i;
            }
        }
        int[] lis = new int[retVal];
        int i = retVal-1;
        lis[i--] = arr[lastInd];
        while(hash[lastInd]!=lastInd){
            lastInd = hash[lastInd];
            lis[i--] = arr[lastInd];
        }
        for (int el : lis) {
            System.out.print(el+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = new int[]{5,4,11,16,8};
        print_LIS(arr);//5 11 16 
        arr = new int[]{10,9,2,5,3,7,101,18};
        print_LIS(arr);//2 5 7 101
        arr = new int[]{0,1,0,3,2,3};
        print_LIS(arr);//0 1 2 3
    }
}
