class Solution {
    public long sumAndMultiply(int n) {
        // Use long variables internally to prevent arithmetic overflow
        long x = 0;
        long sum = 0;
        long place = 1;
        
        // Handle negative input if applicable
        long temp = Math.abs((long) n);

        // Extract digits from right to left
        while (temp > 0) {
            long digit = temp % 10;
            if (digit != 0) {
                x = (digit * place) + x;
                place *= 10;
                sum += digit;
            }
            temp /= 10;
        }

        // Return as long to maintain the full value
        return x * sum;
    }
}
