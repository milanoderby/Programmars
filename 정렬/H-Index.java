import java.util.*;
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int start = 0;
        int end = citations.length;

        if (citations[0] >= citations.length) {
            return citations.length;
        }

        // 0 1 3 5 6
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (citations[citations.length - mid] >= mid) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return start;
    }
}