import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LongestDivisibleSubset
 */
public class LongestDivisibleSubset {

    public List<Integer> LDS(int[] arr)
    {
        Arrays.sort(arr);
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int[] hash = new int[arr.length];
        for (int ind = 0; ind < arr.length; ind++) {
            hash[ind] = ind;
            for(int prev_ind = 0; prev_ind < ind ; prev_ind++){
                if(arr[ind]%arr[prev_ind]==0 && dp[ind]<dp[prev_ind]+1){
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
        List<Integer> ret = new ArrayList<>();
        ret.add(arr[lastInd]);
        while(hash[lastInd]!=lastInd){
            lastInd = hash[lastInd];
            ret.add(arr[lastInd]);
        }
        Collections.reverse(ret);
        return ret;
    }
}