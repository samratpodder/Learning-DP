class NinjaTraining {
    public int ninjaTraining(int days, int lastTask, int points[][]) {
        if(days==0){
            int maxi = Integer.MIN_VALUE;
            for(int i=0;i<points[days].length;i++){
                if(lastTask != i)   maxi = Math.max(maxi, points[days][i]);
            }
            return maxi;
        }
        int maxi = Integer.MIN_VALUE;
        for(int i=0;i<points[days].length;i++){
            if(lastTask!=i) maxi = Math.max(maxi, points[days][i]+ninjaTraining(days-1, i, points));
        }
        return maxi;
    }
    public int ninjaDP(int days, int lastTask, int points[][]){
        int[] dp = new int[4];
        dp[0] = Math.max(points[0][1], points[0][2]);
        dp[1] = Math.max(points[0][0], points[0][2]);
        dp[2] = Math.max(points[0][0], points[0][1]);
        dp[3] = Math.max(points[0][1], Math.max(points[0][0], points[0][2]));
        for (int day = 1; day <= days; day++) {
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        temp[last] = Math.max(temp[last], points[day][task] + dp[task]);
                    }
                }
            }
            dp = temp;
        }
        return dp[3];
    }
    public static void main(String[] args) {
        NinjaTraining obj = new NinjaTraining();
        System.out.println(obj.ninjaDP(3-1, -1, new int[][]{{1,2,5},{3,1,1},{3,3,3}}));//11
        System.out.println(obj.ninjaDP(3-1, -1, new int[][]{{10,40,70},{20,50,80},{30,60,90}}));//210
    }
}
