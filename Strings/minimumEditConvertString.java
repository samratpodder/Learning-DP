class minimumEditConvertString {
    public int minOperations(String str1, String str2) 
	{ 
	    return str1.length() + str2.length() - (2*LongestCommonSubsequence.LCSTabSO(str1, str2));
	}     
}
