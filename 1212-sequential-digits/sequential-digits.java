

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";

        for (int length = 2; length <= 9; length++) {
            for (int i = 0; i <= 9 - length; i++) {
                int num = Integer.parseInt(digits.substring(i, i + length));
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}
