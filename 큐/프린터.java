import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Document> printQueue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            priorityQueue.offer(priorities[i]);
            printQueue.offer(new Document(i, priorities[i]));
        }
        
        while (!printQueue.isEmpty()) {
            Document currentDocument = printQueue.poll();
            if (priorityQueue.peek() > currentDocument.getPriority()) {
                printQueue.offer(currentDocument);
            } else {
                priorityQueue.poll();
                answer++;
                if (currentDocument.getLocation() == location) {
                    break;
                }
            }
        }
        return answer;
    }
    
    private static class Document {
        private int location;
        private int priority;

        public Document(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }

        public int getLocation() {
            return location;
        }

        public int getPriority() {
            return priority;
        }
    }
}