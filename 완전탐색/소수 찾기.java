import java.util.*;
class Solution {
    private static int maxPermutationNum;
    public int solution(String numbers) {
        maxPermutationNum = 0;
        Set<Integer> permutationNumber = new HashSet<>();
        makePermutations(numbers, 0, 0, new boolean[numbers.length()], permutationNumber);

        boolean[] isPrime = new boolean[10000000];
        for (int num = 2; num < 10000000; num++) {
            isPrime[num] = true;
        }
        makeNotPrimeNumbers(isPrime);

        int answer = 0;
        for (Integer number : permutationNumber) {
            if (isPrime[number]) {
                answer++;
            }
        }
        return answer;
    }
    
    // numbers의 숫자들로 만들 수 있는 모든 순열을 구합니다.
    private static void makePermutations(String numbers, int count, int result, boolean[] isUsed, Set<Integer> permutationNumber) {
        if (count >= numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (isUsed[i]) {
                continue;
            }

            isUsed[i] = true;
            int newResult = (10 * result) + numbers.charAt(i) - '0';
            maxPermutationNum = Math.max(maxPermutationNum, newResult);
            permutationNumber.add(newResult);
            makePermutations(numbers, count + 1, newResult, isUsed, permutationNumber);
            isUsed[i] = false;
        }
    }

    // 소수가 아닌 수들의 집합을 구합니다.
    private static void makeNotPrimeNumbers(boolean[] isPrime) {
        for (int num = 2; num * num <= maxPermutationNum; num++) {
            if (!isPrime[num]) {
                continue;
            }

            for (int multiple = num * num; multiple <= maxPermutationNum; multiple += num) {
                isPrime[multiple] = false;
            }
        }
    }
}