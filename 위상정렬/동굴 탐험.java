import java.util.*;
class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] p : path) {
            int src = p[0];
            int dest = p[1];

            graph[src].add(dest);
            graph[dest].add(src);
        }

        List<Integer>[] directedGraph = new List[n];
        for (int i = 0; i < directedGraph.length; i++) {
            directedGraph[i] = new ArrayList<>();
        }
        int[] countOfEntry = new int[n];

        Queue<Integer> roomQueue = new ArrayDeque<>();
        roomQueue.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (!roomQueue.isEmpty()) {
            int cur = roomQueue.poll();
            for (Integer next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    roomQueue.offer(next);
                    directedGraph[cur].add(next);
                    countOfEntry[next]++;
                }
            }
        }

        for (int[] o : order) {
            int src = o[0];
            int dest = o[1];

            directedGraph[src].add(dest);
            countOfEntry[dest]++;
        }
        
        if (countOfEntry[0] > 0) {
            return false;
        }

        roomQueue = new ArrayDeque<>();
        roomQueue.offer(0);
        visited = new boolean[n];
        visited[0] = true;
        while (!roomQueue.isEmpty()) {
            int cur = roomQueue.poll();
            for (Integer next : directedGraph[cur]) {
                countOfEntry[next]--;
                if (countOfEntry[next] == 0) {
                    roomQueue.offer(next);
                    visited[next] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}