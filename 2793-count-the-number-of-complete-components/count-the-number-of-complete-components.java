class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        java.util.List<java.util.List<Integer>> adj = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int vertexCount = 0;
                int edgeCount = 0;
                
                java.util.Queue<Integer> queue = new java.util.LinkedList<>();
                queue.offer(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    vertexCount++;
                    edgeCount += adj.get(curr).size();

                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }

                if (edgeCount / 2 == (vertexCount * (vertexCount - 1)) / 2) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }
}
