class Solution {
    public int solution(int n, int[][] connections) {
        int answer = 0;
        boolean[] networkFound = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (networkFound[i]) {
                continue;
            }
            findNetwork(i, networkFound, connections);
            answer++;
        }

        return answer;
    }
    
    private static void findNetwork(int computer, boolean[] networkFound, int[][] connections) {
        networkFound[computer] = true;
        for (int i = 0; i < connections.length; i++) {
            if (!networkFound[i] && connections[computer][i] == 1) {
                findNetwork(i, networkFound, connections);
            }
        }
    }
}