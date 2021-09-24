class Solution {
    private static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        getAnswer(0, 0, target, numbers);
        return answer;
    }
    
    private static void getAnswer(int index, int sum, int target, int[] numbers) {
        if(index >= numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }
        
        getAnswer(index + 1, sum + numbers[index], target, numbers);
        getAnswer(index + 1, sum - numbers[index], target, numbers);
    }
}