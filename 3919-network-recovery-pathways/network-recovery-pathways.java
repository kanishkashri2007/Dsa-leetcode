import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int[] inDegree = new int[n];
        int right = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].add(new int[]{v, cost});
            inDegree[v]++;
            right = Math.max(right, cost);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);
            for (int[] next : adj[u]) {
                int v = next[0];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        
        int left = 0;
        int ans = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(mid, adj, topoOrder, online, k, n)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int minScore, List<int[]>[] adj, List<Integer> topoOrder, boolean[] online, long k, int n) {
        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;
        
        for (int u : topoOrder) {
            if (u != 0 && !online[u]) continue;
            if (minCost[u] == Long.MAX_VALUE) continue;
            
            for (int[] next : adj[u]) {
                int v = next[0];
                int cost = next[1];
                
                if (v != n - 1 && !online[v]) continue;
                if (cost >= minScore) {
                    if (minCost[u] + cost <= k) {
                        minCost[v] = Math.min(minCost[v], minCost[u] + cost);
                    }
                }
            }
        }
        
        return minCost[n - 1] <= k;
    }
}
