class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int totalCountOfGrid = brown + yellow;
        for (int num = 1; num * num <= yellow; num++) {
            if (yellow % num != 0) {
                continue;
            }

            int length = num + 2;
            int width = yellow / num + 2;
            if (totalCountOfGrid == length * width) {
                answer = new int[] {width, length};
                break;
            }
        }
        return answer;
    }
}