import java.util.*;
class Solution {
    private static long[][] minLossOfTeam;
    private static List<Integer>[] teamGraph;
    public int solution(int[] sales, int[][] links) {
        minLossOfTeam = new long[sales.length + 1][2];
        for (int i = 0; i <= sales.length; i++) {
            minLossOfTeam[i][0] = -1;
            minLossOfTeam[i][1] = -1;
        }

        teamGraph = new List[sales.length + 1];
        for (int i = 0; i <= sales.length; i++) {
            teamGraph[i] = new ArrayList<>();
        }

        for (int[] link : links) {
            int teamLeader = link[0];
            int teamMember = link[1];
            teamGraph[teamLeader].add(teamMember);
        }

        int ceo = 1;
        long answer = Math.min(getMinLossOfTeam(ceo, 0, sales), getMinLossOfTeam(ceo, 1, sales));
        return (int) answer;
    }
    
    private static long getMinLossOfTeam(int teamLeader, int teamLeaderAttended, int[] sales) {
        if (minLossOfTeam[teamLeader][teamLeaderAttended] != -1) {
            return minLossOfTeam[teamLeader][teamLeaderAttended];
        }

        if (teamGraph[teamLeader].isEmpty()) {
            if (teamLeaderAttended > 0) {
                minLossOfTeam[teamLeader][teamLeaderAttended] = sales[teamLeader - 1];
            } else {
                minLossOfTeam[teamLeader][teamLeaderAttended] = 0;
            }
            return minLossOfTeam[teamLeader][teamLeaderAttended];
        }

        long sumOfLoss = 0;
        boolean teamMemberAttended = false;
        long minDiffOfAttendAndNonAttendance = Long.MAX_VALUE;
        for (int teamMember : teamGraph[teamLeader]) {
            long minSalesWhenTeamMemberAttended = getMinLossOfTeam(teamMember, 1, sales);
            long minSalesWhenTeamMemberNotAttended = getMinLossOfTeam(teamMember, 0, sales);
            if (minSalesWhenTeamMemberAttended <= minSalesWhenTeamMemberNotAttended) {
                teamMemberAttended = true;
                sumOfLoss += minSalesWhenTeamMemberAttended;
            } else {
                sumOfLoss += minSalesWhenTeamMemberNotAttended;
            }

            minDiffOfAttendAndNonAttendance = Math.min(minDiffOfAttendAndNonAttendance, Math.abs(minSalesWhenTeamMemberAttended - minSalesWhenTeamMemberNotAttended));
        }
        
        if (teamLeaderAttended > 0) {
            minLossOfTeam[teamLeader][teamLeaderAttended] = sumOfLoss + sales[teamLeader - 1];
        } else {
            if (!teamMemberAttended) {
                sumOfLoss += minDiffOfAttendAndNonAttendance;
            }
            minLossOfTeam[teamLeader][teamLeaderAttended] = sumOfLoss;
        }
        return minLossOfTeam[teamLeader][teamLeaderAttended];
    }
}