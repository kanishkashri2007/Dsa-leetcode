class Solution {
    public boolean isValid(String s) {
        char[] stackarray = new char[s.length()];
        int top = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stackarray[++top] = ch;   
            } else {
                if (top == -1) {
                    return false;        
                }

                char popped = stackarray[top--]; 

                if ((ch == ')' && popped != '(') ||
                    (ch == '}' && popped != '{') ||
                    (ch == ']' && popped != '[')) {
                    return false;
                }
            }
        }

        return top == -1; 
    }
}