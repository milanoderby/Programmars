import java.util.*;
class Solution {
    private static int maxPermutationNum;
    public int solution(String numbers) {
        maxPermutationNum = 0;
        Set<Integer> permutationNumber = new HashSet<>();
        makePermutations(numbers, 0, 0, new boolean[numbers.length()], permutationNumber);

        boolean[] isPrime = new boolean[maxPermutationNum + 1];
        for (int num = 2; num <= maxPermutationNum; num++) {
            isPrime[num] = true;
        }
        Arrays.fill(isPrime, 2, maxPermutationNum + 1, true);

        makePrimeNumbers(isPrime);
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
        // 종료조건 명시
        // 숫자를 numbers의 문자 개수만큼 뽑았을 때 종료
        if (count >= numbers.length()) {
            return;
        }

        // 숫자를 하나씩 뽑는 for문
        for (int i = 0; i < numbers.length(); i++) {
            if (isUsed[i]) {
                continue;
            }

            isUsed[i] = true;
            int newResult = (10 * result) + (numbers.charAt(i) - '0');
            maxPermutationNum = Math.max(maxPermutationNum, newResult);
            permutationNumber.add(newResult);
            makePermutations(numbers, count + 1, newResult, isUsed, permutationNumber);
            isUsed[i] = false;
        }
    }

    // 소수를 판별할 boolean 배열을 구합니다.
    private static void makePrimeNumbers(boolean[] isPrime) {
        // 에라토스테네스의 체
        // 2를 제외한 2의 배수를 다 제거
        // 3을 제외한 3의 배수를 다 제거
        // 4를 제외한 4의 배수를 다 제거
        // ...
        // N을 제외한 N의 배수를 다 제거

        for (int num = 2; num * num <= maxPermutationNum; num++) {
            if (!isPrime[num]) {
                continue;
            }

            // N을 제외한 N의 배수를 다 제거
            for (int multiple = num * num; multiple <= maxPermutationNum; multiple += num) {
                isPrime[multiple] = false;
            }
            // 5 - 25부터 5의배수를 제거합니다.
            // 2의 배수, 3의 배수, 4의 배수는 제거되었기 때문에

            // 10: 5 * 2
            // 15: 5 * 3
            // 20: 5 * 4
        }
    }
}