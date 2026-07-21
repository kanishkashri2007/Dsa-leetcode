public class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int originalOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                originalOnes++;
            }
        }
        
        String t = "1" + s + "1";
        
        int maxGain = 0;
        int preZeroes = -1;
        int i = 0;
        while (i < t.length()) {
            if (t.charAt(i) == '0') {
                int j = i;
                while (j < t.length() && t.charAt(j) == '0') {
                    j++;
                }
                int curZeroes = j - i;
                
                if (preZeroes != -1) {
                    maxGain = Math.max(maxGain, preZeroes + curZeroes);
                }
                
                preZeroes = curZeroes;
                i = j;
            } else {
                i++;
            }
        }
        
        // Step 4: The result is the original count plus the maximum gain achieved
        return originalOnes + maxGain;
    }
}
