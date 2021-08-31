import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> workingDays = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            int remained = 100 - progresses[i];
            int workingDay = remained / speeds[i] + (remained % speeds[i] == 0 ? 0 : 1);
            workingDays.offer(workingDay);
        }


        while (!workingDays.isEmpty()) {
            int prevWork = workingDays.poll();
            int deployedCountOfWork = 1;
            while (!workingDays.isEmpty()) {
                int nextWork = workingDays.peek();
                if (prevWork >= nextWork) {
                    workingDays.poll();
                    deployedCountOfWork++;
                } else {
                    break;
                }
            }
            answer.add(deployedCountOfWork);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}