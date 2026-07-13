class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int left = 0;
        int minlength=Integer.MAX_VALUE;
        for(int right=0;right<nums.length;right++){
            sum+=nums[right];
            while(sum>=target){
                int len = right-left+1;
                minlength=Math.min(len,minlength);
                sum=sum-nums[left];
                left++;
            }
        }
        if(minlength==Integer.MAX_VALUE)
        return 0;
        return minlength;
    }
}