import java.util.*;
class Solution {
    public static int[] solution(int[] answers) {
        List<int[]> stamp = new ArrayList<>();
        stamp.add(new int[] {1, 2, 3, 4, 5});
        stamp.add(new int[] {2, 1, 2, 3, 2, 4, 2, 5});
        stamp.add(new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

        List<Student> students = new ArrayList<>();
        int highestScore = 0;
        for (int number = 0; number < 3; number++) {
            students.add(new Student(number, 0));
            for (int index = 0; index < answers.length; index++) {
                int correctAnswer = answers[index];
                int problemNum = (index % stamp.get(number).length);
                int answerOfStudent = stamp.get(number)[problemNum];
                if (answerOfStudent == correctAnswer) {
                    students.get(number).score++;
                }
            }
            highestScore = Math.max(highestScore, students.get(number).score);
        }

        final int finalHighestScore = highestScore;
        return students.stream().filter(student -> student.getScore() == finalHighestScore).mapToInt(student -> student.getNumber() + 1).sorted().toArray();
    }

    private static class Student {
        private int number;
        private int score;

        public Student(int number, int score) {
            this.number = number;
            this.score = score;
        }

        public int getNumber() {
            return number;
        }

        public int getScore() {
            return score;
        }
    }
}