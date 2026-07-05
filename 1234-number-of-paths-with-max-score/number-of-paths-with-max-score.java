class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1000000007;
        int[][] dpSum = new int[n][n];
        int[][] dpPaths = new int[n][n];
        
        for (int[] row : dpSum) Arrays.fill(row, -1);
        
        dpSum[n - 1][n - 1] = 0;
        dpPaths[n - 1][n - 1] = 1;
        
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r == n - 1 && c == n - 1) continue;
                if (board.get(r).charAt(c) == 'X') continue;
                
                int maxScore = -1;
                int pathsCount = 0;
                
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if (nr < n && nc < n && dpSum[nr][nc] != -1) {
                        int currentVal = dpSum[nr][nc];
                        if (currentVal > maxScore) {
                            maxScore = currentVal;
                            pathsCount = dpPaths[nr][nc];
                        } else if (currentVal == maxScore) {
                            pathsCount = (pathsCount + dpPaths[nr][nc]) % mod;
                        }
                    }
                }
                
                if (maxScore != -1) {
                    int val = (r == 0 && c == 0) ? 0 : board.get(r).charAt(c) - '0';
                    dpSum[r][c] = maxScore + val;
                    dpPaths[r][c] = pathsCount;
                }
            }
        }
        
        if (dpSum[0][0] == -1) {
            return new int[]{0, 0};
        }
        return new int[]{dpSum[0][0], dpPaths[0][0]};
    }
}
