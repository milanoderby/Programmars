import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> foods = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            foods.offer((long) scoville[i]);
        }

        int count = 0;
        while (!foods.isEmpty()) {
            long minScoville = foods.poll();
            if (minScoville >= K) {
                 return count;
            }
            
            if (foods.isEmpty()) {
                return -1;
            }
            
            long nextMinScoville = foods.poll();
            count++;
            foods.offer(minScoville + 2 * nextMinScoville);
        }

        return -1;
    }
}