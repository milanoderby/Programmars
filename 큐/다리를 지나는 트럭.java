import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;

        Queue<Truck> waitingQueue = new ArrayDeque<>();
        for (int i = 0; i < truck_weights.length; i++) {
            waitingQueue.offer(new Truck(0, truck_weights[i]));
        }

        int weightOfTruckOnBridge = 0;
        Queue<Truck> bridgeQueue = new ArrayDeque<>();
        while (!waitingQueue.isEmpty() || !bridgeQueue.isEmpty()) {

            if (!bridgeQueue.isEmpty()) {
                Truck truckOnBridge = bridgeQueue.peek();
                if ((time - truckOnBridge.entryTime) >= bridge_length) {
                    bridgeQueue.poll();
                    weightOfTruckOnBridge -= truckOnBridge.weight;
                }
            }

            if (!waitingQueue.isEmpty()) {
                Truck truckOnWaiting = waitingQueue.peek();
                if (bridgeQueue.size() < bridge_length && (weightOfTruckOnBridge + truckOnWaiting.weight) <= weight) {
                    waitingQueue.poll();

                    truckOnWaiting.entryTime = time;
                    bridgeQueue.offer(truckOnWaiting);
                    weightOfTruckOnBridge += truckOnWaiting.weight;
                }
            }

            time++;
        }

        return time;
    }
    
    private static class Truck {
        private int entryTime;
        private int weight;

        public Truck(int entryTime, int weight) {
            this.entryTime = entryTime;
            this.weight = weight;
        }
    }
}