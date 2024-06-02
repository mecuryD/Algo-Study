import java.util.*;

// [프로그래머스] 기능 개발
class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        int len = progresses.length;
        
        int max = (int) Math.ceil((100.0 - progresses[0]) / speeds[0]);
        stack.push(max);
        
        // progresses 배열 순회하면서 몇 개의 기능이 배포되는지 계산
        for(int i=1; i<len; i++){
            
            // i번째 기능이 배포될 수 있는 가장 빠른 날짜
            int complete = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            
            // 아직 기능 개발이 완료되지 않은 경우
            if(complete > max){
                
                // 한 번에 배포되는 기능 개수를 계산하고 스택 초기화
                list.add(stack.size());
                stack.clear();
                
                // 스택의 최대값 갱신
                max = complete;
            }
            
            // 스택에 추가
            stack.push(complete);   
        }
        
        // 스택에 남아 있는 값 처리
        list.add(stack.size());
        
        // 결과 배열
        int[] result = new int[list.size()];
        for(int i=0; i<result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}
