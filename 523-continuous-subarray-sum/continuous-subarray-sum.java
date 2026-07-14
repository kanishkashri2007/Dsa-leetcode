class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int prefixsum=0;
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            prefixsum+=nums[i];
            int remainder=prefixsum%k;
            if(map.containsKey(remainder)){
                if(i-map.get(remainder)>=2){
                    return true;
                }
            }
            else{
                map.put(remainder,i);
            }
        }
        return false;
        
    }
}