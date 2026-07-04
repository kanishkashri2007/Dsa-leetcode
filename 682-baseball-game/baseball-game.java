import java.util.Stack;
class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (String op : operations) {
            switch (op) {
                case "+":
                    int top = stack.pop();
                    int newScore = top + stack.peek();
                    stack.push(top);
                    stack.push(newScore);
                    break;

                case "D":
                    stack.push(2 * stack.peek());
                    break;

                case "C":
                    stack.pop();
                    break;

                default:
                    stack.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }
}