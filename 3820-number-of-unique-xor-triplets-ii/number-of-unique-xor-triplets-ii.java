import java.util.ArrayList;
import java.util.List;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Identify all unique elements present in the input array
        boolean[] uniqueElements = new boolean[2048];
        List<Integer> distinctList = new ArrayList<>();
        
        for (int num : nums) {
            if (!uniqueElements[num]) {
                uniqueElements[num] = true;
                distinctList.add(num);
            }
        }
        
        // Step 2: Compute all unique pair-wise XOR combinations -> O(U^2)
        boolean[] pairXors = new boolean[2048];
        List<Integer> distinctPairs = new ArrayList<>();
        int u = distinctList.size();
        
        for (int i = 0; i < u; i++) {
            for (int j = i; j < u; j++) {
                int pairXor = distinctList.get(i) ^ distinctList.get(j);
                if (!pairXors[pairXor]) {
                    pairXors[pairXor] = true;
                    distinctPairs.add(pairXor);
                }
            }
        }
        
        // Step 3: Compute final triplets combining pairs with single unique values -> O(U * Pairs)
        boolean[] tripletXors = new boolean[2048];
        int uniqueTripletCount = 0;
        
        for (int pair : distinctPairs) {
            for (int singleVal : distinctList) {
                int tripletXor = pair ^ singleVal;
                if (!tripletXors[tripletXor]) {
                    tripletXors[tripletXor] = true;
                    uniqueTripletCount++;
                }
            }
        }
        
        return uniqueTripletCount;
    }
}
