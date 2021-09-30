import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        Map<String, Integer> wordIndexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordIndexMap.put(words[i], i);
        }

        if (!wordIndexMap.containsKey(target)) {
            return 0;
        }

        List<Integer>[] wordGraph = new List[words.length];
        for (int i = 0; i < words.length; i++) {
            wordGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }

                int countOfDifferetCharacter = 0;
                for (int k = 0; k < words[i].length(); k++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) {
                        countOfDifferetCharacter++;
                    }
                }

                if (countOfDifferetCharacter == 1) {
                    wordGraph[i].add(j);
                    wordGraph[j].add(i);
                }
            }
        }

        int end = wordIndexMap.get(target);

        Queue<Integer> wordIndexQueue = new ArrayDeque<>();
        Queue<Integer> stepQueue = new ArrayDeque<>();
        boolean[] isVisited = new boolean[words.length];

        for (int i = 0; i < words.length; i++) {
            int countOfDifferetCharacter = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) != words[i].charAt(j)) {
                    countOfDifferetCharacter++;
                }
            }
            
            if (countOfDifferetCharacter == 1) {
                wordIndexQueue.add(i);
                stepQueue.add(1);
                isVisited[i] = true;
            }
        }
        
        while (!wordIndexQueue.isEmpty()) {
            int cur = wordIndexQueue.poll();
            int curCountOfStep = stepQueue.poll();

            if (cur == end) {
                return curCountOfStep;
            }

            for (Integer next : wordGraph[cur]) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    wordIndexQueue.add(next);
                    stepQueue.add(curCountOfStep + 1);
                }
            }
        }
        return 0;
    }
}