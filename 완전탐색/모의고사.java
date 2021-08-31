import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        List<int[]> stamp = new ArrayList<>();
        stamp.add(new int[] {1, 2, 3, 4, 5});
        stamp.add(new int[] {2, 1, 2, 3, 2, 4, 2, 5});
        stamp.add(new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        int[] answerCount = new int[3];
        for (int index = 0; index < answers.length; index++) {
            int correctAnswer = answers[index];
            for (int person = 0; person < 3; person++) {
                int temp = (index % stamp.get(person).length);
                int answerOfPerson = stamp.get(person)[temp];
                if (answerOfPerson == correctAnswer) {
                    answerCount[person]++;
                }
            }
        }

        PriorityQueue<Scorer> answerCountPQ = new PriorityQueue<>(Comparator.comparingInt(Scorer::getScore).reversed());
        for (int i = 0; i < answerCount.length; i++) {
            answerCountPQ.add(new Scorer(i + 1, answerCount[i]));
        }

        List<Integer> highScorers = new ArrayList<>();
        Scorer highScorer = answerCountPQ.poll();
        highScorers.add(highScorer.person);
        while (!answerCountPQ.isEmpty()) {
            Scorer nextHighScorer = answerCountPQ.poll();
            if (highScorer.score > nextHighScorer.score) {
                break;
            }
            highScorers.add(nextHighScorer.person);
        }
        
        return highScorers.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
    
    private static class Scorer {
        private int person;
        private int score;

        public Scorer(int person, int score) {
            this.person = person;
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }
}