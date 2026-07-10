class Solution {
    public int maxSubArray(int[] arr) {
        int currentSum = arr[0];
        int maxSum = arr[0];
        for(int i=1;i<arr.length;i++){
            currentSum=Math.max(currentSum+arr[i],arr[i]);
            maxSum=Math.max(currentSum,maxSum);
        }
        return maxSum;
    }
}