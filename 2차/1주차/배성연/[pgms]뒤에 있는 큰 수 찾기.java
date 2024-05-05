import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
        // -1 다 심어놓기
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = size-1; i > -1; i--){
            // 뒤에서부터 돌기
            
            // 스택 맨 처음 수가 선택한 수보다 클 때까지 pop
            while(!stack.isEmpty() && stack.peek() <= numbers[i]){
                stack.pop();
            }
            
            // 선택한 수보다 큰 수가 뒤에 남아있으면 집어넣기
            if(!stack.isEmpty()){
                answer[i] = stack.peek();
            }
            
            // 스택에 현재숫자 넣기(오름차순)
            stack.push(numbers[i]);
        }
        
        return answer;
    }
}
