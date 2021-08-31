import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> player = new HashMap<>();
        for (int i = 0; i < participant.length; i++) {
            int count = player.getOrDefault(participant[i], 0);
            player.put(participant[i], count + 1);
        }

        for (int i = 0; i < completion.length; i++) {
            int count = player.get(completion[i]);
            if (count > 1) {
                player.put(completion[i], count - 1);
            } else {
                player.remove(completion[i]);
            }
        }

        for (String p : player.keySet()) {
            answer = p;
        }
        return answer;
    }
}