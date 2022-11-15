/**
 * I
 */
public class I {

    // Optimum Solution
    // The track of past minimum price is the hint of dynamic programming in this problem
    public int maxProfit(int[] prices) {
        int minVal = prices[0];
        int maxProfit = 0;
        for(int i=1;i<prices.length;i++){
            maxProfit = Math.max(maxProfit,prices[i]-minVal);
            minVal = Math.min(minVal,prices[i]);
        }
        return maxProfit;
    }
}