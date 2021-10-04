import java.util.*;
class Solution {
    private static List<String> answer;
    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> ticketListMap = new HashMap<>();
        for (String[] ticket : tickets) {
            String src = ticket[0];
            String dest = ticket[1];

            List<Ticket> ticketListFromSrc = ticketListMap.getOrDefault(src, new ArrayList<>());
            ticketListFromSrc.add(new Ticket(dest, true));
            ticketListMap.put(src, ticketListFromSrc);
        }

        makeTravelPlan("ICN", ticketListMap, 0, tickets.length, new ArrayList<>(Arrays.asList("ICN")));
        return answer.toArray(new String[0]);
    }
    
    private static void makeTravelPlan(String src, Map<String, List<Ticket>> ticketListMap, int countOfTraveledAirport, int countOfTicket, List<String> travelPlan) {
        if (countOfTraveledAirport >= countOfTicket) {
            if (Objects.isNull(answer)) {
                answer = new ArrayList<>(travelPlan);
            } else {
                boolean needReplace = false;
                for (int i = 0; i < answer.size(); i++) {
                    if (Objects.compare(answer.get(i), travelPlan.get(i), String::compareTo) < 0) {
                        break;
                    } else if (Objects.compare(answer.get(i), travelPlan.get(i), String::compareTo) > 0) {
                        needReplace = true;
                        break;
                    }
                }

                if (needReplace) {
                    answer = new ArrayList<>(travelPlan);
                }
            }
            return;
        }

        if (!ticketListMap.containsKey(src)) {
            return;
        }

        List<Ticket> ticketListFromSrc = ticketListMap.get(src);
        for (int i = 0; i < ticketListFromSrc.size(); i++) {
            if (ticketListFromSrc.get(i).isUsable) {
                ticketListFromSrc.get(i).isUsable = false;
                String dest = ticketListFromSrc.get(i).destination;
                travelPlan.add(dest);

                makeTravelPlan(dest, ticketListMap, countOfTraveledAirport + 1, countOfTicket, travelPlan);

                travelPlan.remove(travelPlan.size() - 1);
                ticketListFromSrc.get(i).isUsable = true;
            }
        }
        return;
    }

    private static class Ticket {
        private String destination;
        private boolean isUsable;

        public Ticket(String destination, boolean isUsable) {
            this.destination = destination;
            this.isUsable = isUsable;
        }
    }
}