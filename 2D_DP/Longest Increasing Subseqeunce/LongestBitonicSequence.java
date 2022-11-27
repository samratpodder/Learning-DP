//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine().trim();
            String[] s1 = s.split(" ");
            int[] nums = new int[n];
            for(int i = 0; i < s1.length; i++)
                nums[i] = Integer.parseInt(s1[i]);
            Solution ob = new Solution();
            int ans = ob.LongestBitonicSequence(nums);
            System.out.println(ans);           
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    public int LongestBitonicSequence(int[] nums)
    {
        int n = nums.length;
        int[] dpf = new int[n];
        int[] dpb = new int[n];
        Arrays.fill(dpf,1);
        Arrays.fill(dpb,1);
        for (int i = 0; i < n; i++) {
            for (int prev_i = 0; prev_i < i; prev_i++) {
                if(nums[prev_i]<nums[i]) dpf[i] = Math.max(dpf[i],1+dpf[prev_i]);
            }
        }
        for(int i=n-1;i>=0;i--)
        {
            for(int prev_i = n-1; prev_i>i; prev_i--){
                if (nums[i]>nums[prev_i]) {
                    dpb[i] = Math.max(dpb[i],1+dpb[prev_i]);
                }
            }
        }
        int maxi = -1;
    
        for(int i=0; i<n; i++){
            maxi = Math.max(maxi, dpf[i] + dpb[i] - 1);
        }

        return maxi;
    }
}