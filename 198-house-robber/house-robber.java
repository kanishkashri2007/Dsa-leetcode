class Solution {
    int[] dp;
    public int rob(int[] nums) {
        dp=new int[nums.length];
        for(int i=0;i<dp.length;i++){
            dp[i]=-2;
        }        
        return solve(nums,0);
    }
    public int solve(int[]nums,int index){
        if(index>=nums.length)
        return 0;
        if(dp[index]!=-2)
        return dp[index];
        int pick=nums[index]+solve(nums,index+2);
        int notpick=solve(nums,index+1);
        dp[index]=Math.max(pick,notpick);
        return dp[index];
    }
}