import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        int sizeOfPQ = 0;
        for (String operation : operations) {
            StringTokenizer tokenizer = new StringTokenizer(operation);
            char instruction = tokenizer.nextToken().charAt(0);
            int number = Integer.parseInt(tokenizer.nextToken());
            switch (instruction) {
                case 'D':
                    if (sizeOfPQ <= 0) {
                        break;
                    }

                    if (number == 1) {
                        maxPQ.poll();
                    } else {
                        minPQ.poll();
                    }
                    sizeOfPQ--;
                    if (sizeOfPQ <= 0) {
                        maxPQ.clear();
                        minPQ.clear();
                    }
                    break;
                default:
                    maxPQ.offer(number);
                    minPQ.offer(number);
                    sizeOfPQ++;
                    break;
            }
        }

        if (sizeOfPQ == 0) {
            return new int[]{0, 0};
        }
        return new int[]{maxPQ.peek(), minPQ.peek()};
    }
}