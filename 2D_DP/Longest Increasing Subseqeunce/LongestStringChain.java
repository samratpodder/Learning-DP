import java.util.Arrays;

class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (str1,str2)->str1.length()-str2.length());
        int[] dp = new int[words.length];
        Arrays.fill(dp,1);
        int retVal = Integer.MIN_VALUE;
        for (int ind = 0; ind < words.length; ind++) {
            for(int prev_ind = 0; prev_ind < ind ; prev_ind++){
                if(compare(words[ind], words[prev_ind])) dp[ind] = Math.max(dp[ind], 1+dp[prev_ind]);
            }
            retVal = Math.max(retVal, dp[ind]);
        }
        return retVal;
    }
    public boolean compare(String s1, String s2){
        if(s1.length()!=s2.length()+1) return false;
        int f=0;
        int s=0;
        while(f<s1.length()){
            if(s<s2.length() && s1.charAt(f)==s2.charAt(s)) {f++;s++;}
            else f++;
        }
        return f==s1.length() && s==s2.length();
    }
}