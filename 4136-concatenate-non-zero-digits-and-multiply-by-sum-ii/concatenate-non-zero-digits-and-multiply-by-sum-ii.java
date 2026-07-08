class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        
        // Precompute powers of 10 and their modular inverses
        long[] pow10 = new long[m + 1];
        long[] invPow10 = new long[m + 1];
        pow10[0] = 1;
        invPow10[0] = 1;
        
        long inv10 = power(10, MOD - 2); // Modular inverse of 10
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
            invPow10[i] = (invPow10[i - 1] * inv10) % MOD;
        }

        // Prefix arrays
        int[] cnt = new int[m + 1];
        long[] sumOfDigits = new long[m + 1];
        long[] prefX = new long[m + 1];

        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';
            
            cnt[i + 1] = cnt[i];
            sumOfDigits[i + 1] = sumOfDigits[i] + digit;
            prefX[i + 1] = prefX[i];

            if (digit > 0) {
                cnt[i + 1]++;
                // Extract positional contribution scaled down by 10^cnt
                long term = (digit * invPow10[cnt[i + 1]]) % MOD;
                prefX[i + 1] = (prefX[i + 1] + term) % MOD;
            }
        }

        // Process each query
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // 1. Calculate sum of digits in range [l, r]
            long currentSum = sumOfDigits[r + 1] - sumOfDigits[l];
            
            if (currentSum == 0) {
                answer[i] = 0;
                continue;
            }

            // 2. Extract x using prefix sums and rescale up by 10^cnt[r+1]
            long rawX = (prefX[r + 1] - prefX[l] + MOD) % MOD;
            long x = (rawX * pow10[cnt[r + 1]]) % MOD;

            // 3. Final answer formula: (x * sum) % MOD
            answer[i] = (int) ((x * (currentSum % MOD)) % MOD);
        }

        return answer;
    }

    // Helper method for fast modular exponentiation
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}
