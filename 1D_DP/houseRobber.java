class houseRobber {
    //First and Last House is adjacent to each other

    public int rob(int[] nums) {
        NonAdjacentElementSum obj = new NonAdjacentElementSum();
        if(nums.length==1) return nums[0];
        int[] robFirst = new  int[nums.length-1];
        int[] robLast = new int[nums.length-1];
        for(int i=0;i<nums.length;i++){
            if(i!=0) robLast[i-1] = nums[i];
            if(i!=nums.length-1) robFirst[i] = nums[i];
        }
        return Math.max(obj.maxSumTabSO(nums.length-2,robFirst),obj.maxSumTabSO(nums.length-2,robLast));
    }
    
}