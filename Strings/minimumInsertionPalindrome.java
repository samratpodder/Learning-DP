/**
 * minimumInsertionPalindrome
 */
class minimumInsertionPalindrome {
    public int minInsertions(String s) {
        return s.length()-LongestPalindromicSubsequence.getLPS(s);
    }
}