public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int start = 0;
        long costWhenHeightIsStart = getCostToMakeHeightOfLandSame(land, P, Q, start);
        if (costWhenHeightIsStart <= getCostToMakeHeightOfLandSame(land, P, Q, start + 1)) {
            return costWhenHeightIsStart;
        }

        int end = 1000000000;
        long costWhenHeightIsEnd = getCostToMakeHeightOfLandSame(land, P, Q, end);
        if (costWhenHeightIsEnd <= getCostToMakeHeightOfLandSame(land, P, Q, end - 1)) {
            return getCostToMakeHeightOfLandSame(land, P, Q, end);
        }

        long answer = 0;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            long costWhenHeightIsMid = getCostToMakeHeightOfLandSame(land, P, Q, mid);
            long costWhenHeightIsMidPlusOne = getCostToMakeHeightOfLandSame(land, P, Q, mid + 1);
            long costWhenHeightIsMidMinusOne = getCostToMakeHeightOfLandSame(land, P, Q, mid - 1);

            if (costWhenHeightIsMidMinusOne >= costWhenHeightIsMid && costWhenHeightIsMid <= costWhenHeightIsMidPlusOne) {
                answer = costWhenHeightIsMid;
                break;
            }

            if (costWhenHeightIsMidMinusOne >= costWhenHeightIsMid) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return answer;
    }
    
    private static long getCostToMakeHeightOfLandSame(int[][] land, int P, int Q, int height) {
        long cost = 0;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land.length; j++) {
                if (land[i][j] == height) {
                    continue;
                }

                if (land[i][j] > height) {
                    cost += (long) Q * (land[i][j] - height);
                } else {
                    cost += (long) P * (height - land[i][j]);
                }
            }
        }
        return cost;
    }
}