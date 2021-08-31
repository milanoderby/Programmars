import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothesMap = new HashMap<>();
        for (String[] clothe : clothes) {
            int countOfClothes = clothesMap.getOrDefault(clothe[1], 0);
            clothesMap.put(clothe[1], countOfClothes + 1);
        }

        int answer = 1;
        for (String s : clothesMap.keySet()) {
            answer *= (clothesMap.get(s) + 1);
        }

        return answer - 1;
    }
}