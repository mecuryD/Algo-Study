import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int maxLength = book_time.length;
        
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        // 분으로 환산 후 입장 시간 기준 정렬 PQ에 집어넣기
        for(int i = 0; i < maxLength; i++){
            String[] enterStr = book_time[i][0].split(":");
            int enter = Integer.parseInt(enterStr[0]) * 60 + Integer.parseInt(enterStr[1]);
            String[] leaveStr = book_time[i][1].split(":");
            int leave = Integer.parseInt(leaveStr[0]) * 60 + Integer.parseInt(leaveStr[1]) + 10;
            PQ.add(new int[] {enter, leave});
        }
        
        int[] room = new int[maxLength];
        while(!PQ.isEmpty()){
            // PQ 하나씩 빼기
            int[] cur = PQ.poll();
            
            for(int i = 0; i < maxLength; i++){
                // 이전 종료시간보다 늦게 시작하면 그 자리 갱신
                if(room[i] <= cur[0]){
                    // 0이었던 자리면 방 수 늘리기
                    if(room[i] == 0) answer++;
                    
                    room[i] = cur[1];
                    break;
                }
                // 이전 종료시간보다 일찍 시작하면 뒤로 밀면서 넣을 수 있는 최소한에 넣기
            }
        }
        
        return answer;
    }
}
