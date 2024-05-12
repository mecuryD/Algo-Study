import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        // 투포인터
        int[] answer = {0, 0};
        
        int start = 0;
        int end = 0;
        long sum = 0;
        int minLength = Integer.MAX_VALUE;
        
        while(true){
            if(sum >= k){
                if(sum == k && end-start < minLength){
                    // 합이 k이고 최소 길이이면 최소값 업데이트
                    minLength = end - start;
                    answer[0] = start;
                    answer[1] = end - 1;
                }
                // 만족했으므로 start값 옮기기
                sum -= sequence[start++];
            }else{
                // 끝났으면 종료
                if (end == sequence.length) break;
                // 만족 못했고 끝도 안났으므로 더 늘리기
                sum += sequence[end++];
            }
        }
        return answer;
    }
}
