import java.util.*;
class Solution {
    private static int maxPermutationNum;
    public int solution(String numbers) {
        maxPermutationNum = 0;
        Set<Integer> permutationNumber = new HashSet<>();
        makePermutations(numbers, 0, 0, new boolean[numbers.length()], permutationNumber);

        Set<Integer> notPrimeNumbers = new HashSet<>();
        makeNotPrimeNumbers(notPrimeNumbers);

        int answer = 0;
        for (Integer number : permutationNumber) {
            if (!notPrimeNumbers.contains(number)) {
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
    private static void makeNotPrimeNumbers(Set<Integer> notPrimeNumbers) {
        notPrimeNumbers.add(0);
        notPrimeNumbers.add(1);
        for (int i = 2; i * i <= maxPermutationNum; i++) {
            if (notPrimeNumbers.contains(i)) {
                continue;
            }

            for (int j = i * i; j <= maxPermutationNum; j += i) {
                notPrimeNumbers.add(j);
            }
        }
    }
}