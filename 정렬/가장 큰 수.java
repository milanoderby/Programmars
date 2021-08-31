import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        List<String> numberList = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            numberList.add(String.valueOf(numbers[i]));
        }

        String answer = numberList.stream()
                .sorted((s1, s2) -> - (s1 + s2).compareTo(s2 + s1))
                .reduce((s1, s2) -> s1 + s2)
                .get();
        return answer.startsWith("0") ? "0" : answer;
    }
}