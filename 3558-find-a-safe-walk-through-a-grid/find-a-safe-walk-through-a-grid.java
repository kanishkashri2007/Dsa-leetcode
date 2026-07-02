class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        int[][] maxHealth = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHealth[i][j] = -1;
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        int initialHealth = health - grid.get(0).get(0);
        if (initialHealth <= 0) {
            return false;
        }
        
        queue.offer(new int[]{0, 0, initialHealth});
        maxHealth[0][0] = initialHealth;
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int h = curr[2];
            
            if (h < maxHealth[r][c]) {
                continue;
            }
            
            if (r == m - 1 && c == n - 1) {
                return true;
            }
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextHealth = h - grid.get(nr).get(nc);
                    if (nextHealth > 0 && nextHealth > maxHealth[nr][nc]) {
                        maxHealth[nr][nc] = nextHealth;
                        queue.offer(new int[]{nr, nc, nextHealth});
                    }
                }
            }
        }
        
        return false;
    }
}
