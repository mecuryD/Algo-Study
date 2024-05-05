import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int time = 0;
        
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(y);
        bfs : while(!Q.isEmpty()){
            int size = Q.size();
            for(int i = 0; i < size; i++){
                int cur = Q.poll();
                
                if(cur == x){
                    answer = time;
                    break bfs;
                }
                
                if(cur - n >= x){
                    Q.add(cur - n);
                }
                if(cur % 2 == 0){
                    Q.add(cur / 2);
                }
                if(cur % 3 == 0){
                    Q.add(cur / 3);
                }
            }
            time++;
        }
        
        if(answer == 0 && x != y){
            answer = -1;
        }
        return answer;
    }
}
