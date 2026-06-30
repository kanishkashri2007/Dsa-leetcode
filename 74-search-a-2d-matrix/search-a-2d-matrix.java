class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int low = 0,high = n*m-1;
        while(low<=high){
            int mid = (low + high)/2;
            int row = mid/m;
            int col = mid%m;
            int value = matrix[row][col];
            if(value==target){
                return true;
            }else if(value>target){
                high = mid-1;
            }else if(value<target){
                low = mid+1;
            }
        }
        return false;
    }
}