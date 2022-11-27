class LongestPalindromicSubsequence {

    
    public static int getLPS(String s){
        String rev = reverseString(s);
        LongestCommonSubsequence.printLCS(s, rev);
        return LongestCommonSubsequence.LCSTabSO(s, rev);
    }
    public static void main(String[] args) {
        String s= "bbabcbcab";
        System.out.println(getLPS(s));//babcbab or bacbcab
    }

    // Helper Function
    public static String reverseString(String str) {
        char[] arr = str.toCharArray();
        int l=0,r=arr.length-1;
        while (l<r) {
            char c = arr[l];
            arr[l] = arr[r];
            arr[r] = c;
            l++;r--;
        }
        return new String(arr);
    }
}
