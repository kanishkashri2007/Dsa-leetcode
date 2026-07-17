import java.util.Arrays;

public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
      
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

       
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

       
        long[] count = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                count[i] += freq[j];
            }
        }

       
        long[] gcdCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long totalPairs = (count[i] * (count[i] - 1)) / 2;
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdCount[j];
            }
            gcdCount[i] = totalPairs;
        }

        
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + gcdCount[i];
        }

        
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            answer[i] = binarySearch(prefixSum, target, maxVal);
        }

        return answer;
    }

    private int binarySearch(long[] prefixSum, long target, int maxVal) {
        int low = 1;
        int high = maxVal;
        int result = maxVal;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefixSum[mid] > target) {
                result = mid;
                high = mid - 1; 
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
