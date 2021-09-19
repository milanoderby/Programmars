import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Job> requestedJob = new PriorityQueue<>(
                Comparator.comparingInt(Job::getRequestedTime));

        PriorityQueue<Job> jobQueue = new PriorityQueue<>(
                Comparator.comparingInt(Job::getBurstTime));

        for (int[] job : jobs) {
            int requestedTime = job[0];
            int burstTime = job[1];
            requestedJob.offer(new Job(requestedTime, burstTime));
        }

        int time = 0;
        int totalWaitingTime = 0;
        while (!requestedJob.isEmpty() || !jobQueue.isEmpty()) {
            while (!requestedJob.isEmpty()) {
                Job firstRequestedJob = requestedJob.peek();
                if (firstRequestedJob.getRequestedTime() <= time) {
                    jobQueue.offer(firstRequestedJob);
                    requestedJob.poll();
                } else {
                    break;
                }
            }

            if (!jobQueue.isEmpty()) {
                Job shortestJob = jobQueue.poll();
                time += shortestJob.getBurstTime();
                totalWaitingTime += time - shortestJob.getRequestedTime();
            } else {
                time++;
            }
        }
        return totalWaitingTime / jobs.length;
    }
    
    private static class Job {
        private int requestedTime;
        private int burstTime;

        public Job(int requestedTime, int burstTime) {
            this.requestedTime = requestedTime;
            this.burstTime = burstTime;
        }

        public int getRequestedTime() {
            return requestedTime;
        }

        public int getBurstTime() {
            return burstTime;
        }
    }
}