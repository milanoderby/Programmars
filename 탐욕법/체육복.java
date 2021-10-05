import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] countOfClothes = new int[n + 2];
        for (int i = 0; i < n + 2; i++) {
            countOfClothes[i] = 1;
        }

        for (int i = 0; i < lost.length; i++) {
            int numOfLostStudent = lost[i];
            countOfClothes[numOfLostStudent] -= 1;
        }

        for (int i = 0; i < reserve.length; i++) {
            int numOfReserveStudent = reserve[i];
            countOfClothes[numOfReserveStudent] += 1;
        }

        for (int i = 1; i <= n; i++) {
            if (countOfClothes[i] == 0) {
                if (countOfClothes[i - 1] == 2) {
                    countOfClothes[i - 1] -= 1;
                    countOfClothes[i] += 1;
                } else if (countOfClothes[i + 1] == 2) {
                    countOfClothes[i + 1] -= 1;
                    countOfClothes[i] += 1;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (countOfClothes[i] >= 1) {
                answer++;
            }
        }
        return answer;
    }
}