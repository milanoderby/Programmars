import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Stock> priceStack = new Stack<>();
        for (int currentSecond = 0; currentSecond < prices.length; currentSecond++) {
            while (!priceStack.isEmpty()) {
                Stock prev = priceStack.peek();
                if (prev.price > prices[currentSecond]) {
                    answer[prev.second] = currentSecond - prev.second;
                    priceStack.pop();
                } else {
                    break;
                }
            }
            priceStack.push(new Stock(currentSecond, prices[currentSecond]));
        }

        int lastSecond = prices.length - 1;
        while (!priceStack.isEmpty()) {
            Stock prev = priceStack.peek();
            answer[prev.second] = lastSecond - prev.second;
            priceStack.pop();
        }
        
        return answer;
    }
    
    private static class Stock {
        private int second;
        private int price;

        public Stock(int second, int price) {
            this.second = second;
            this.price = price;
        }
    }
}