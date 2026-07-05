class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        int n = s.length();

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i == n) {
            return 0;
        }

        int sign = 1;
        char firstChar = s.charAt(i);
        if (firstChar == '+') {
            i++;
        } else if (firstChar == '-') {
            sign = -1;
            i++;
        }

        long result = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '1') { 
                if (ch >= '2' && ch <= '9') {
                   
                } else {
                    break;
                }
            }
            
            if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                result = result * 10 + digit;

                if (sign == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (sign == -1 && -result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
                i++;
            } else {
                break;
            }
        }

        return (int) (sign * result);
    }
}
