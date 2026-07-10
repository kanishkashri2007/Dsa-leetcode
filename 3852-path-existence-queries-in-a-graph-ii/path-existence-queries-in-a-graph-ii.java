class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, Comparator.comparingInt(a -> nums[a]));

        int[] sortedNums = new int[n];
        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[idx[i]];
        }

        int[][] upRight = new int[n][18];
        int[][] upLeft = new int[n][18];

        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            int targetRight = val + maxDiff;
            int rIdx = binarySearchRightmost(sortedNums, targetRight);
            upRight[i][0] = idx[rIdx];

            int targetLeft = val - maxDiff;
            int lIdx = binarySearchLeftmost(sortedNums, targetLeft);
            upLeft[i][0] = idx[lIdx];
        }

        for (int j = 1; j < 18; j++) {
            for (int i = 0; i < n; i++) {
                upRight[i][j] = upRight[upRight[i][j - 1]][j - 1];
                upLeft[i][j] = upLeft[upLeft[i][j - 1]][j - 1];
            }
        }

        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            if (nums[u] < nums[v]) {
                if (nums[u] + maxDiff >= nums[v]) {
                    ans[q] = 1;
                    continue;
                }
                int curr = u;
                int steps = 0;
                for (int j = 17; j >= 0; j--) {
                    int next = upRight[curr][j];
                    if (nums[next] + maxDiff < nums[v] && nums[next] > nums[curr]) {
                        curr = next;
                        steps += (1 << j);
                    }
                }
                int next = upRight[curr][0];
                if (nums[next] <= nums[curr]) {
                    ans[q] = -1;
                } else {
                    steps++;
                    if (nums[next] + maxDiff >= nums[v]) {
                        ans[q] = steps + 1;
                    } else {
                        ans[q] = -1;
                    }
                }
            } else {
                if (nums[u] - maxDiff <= nums[v]) {
                    ans[q] = 1;
                    continue;
                }
                int curr = u;
                int steps = 0;
                for (int j = 17; j >= 0; j--) {
                    int next = upLeft[curr][j];
                    if (nums[next] - maxDiff > nums[v] && nums[next] < nums[curr]) {
                        curr = next;
                        steps += (1 << j);
                    }
                }
                int next = upLeft[curr][0];
                if (nums[next] >= nums[curr]) {
                    ans[q] = -1;
                } else {
                    steps++;
                    if (nums[next] - maxDiff <= nums[v]) {
                        ans[q] = steps + 1;
                    } else {
                        ans[q] = -1;
                    }
                }
            }
        }
        return ans;
    }

    private int binarySearchRightmost(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] <= target) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    private int binarySearchLeftmost(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        int ans = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}