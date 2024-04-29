import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Stack<Integer> stackDel = new Stack<>();
        Stack<Integer> stackPick = new Stack<>();
        int cntDel = cap;
        int cntPick = cap;
        for(int i = 0; i < n; i++){
            // 배달/수거할 집 index 각각 stack에 저장
            if(deliveries[i] != 0){
                stackDel.push(i);
            }
            if(pickups[i] != 0){
                stackPick.push(i);
            }
        }
        
        // 배달, 수거 둘 다 남아있을 때
        while(!stackDel.isEmpty() && !stackPick.isEmpty()){
            if(stackDel.peek() > stackPick.peek()){
                answer += 2*(stackDel.peek() + 1);
            }else{
                answer += 2*(stackPick.peek() + 1);
            }
            
            // 모든 배달 다 할 때까지 배달
            while(cntDel > 0 && !stackDel.isEmpty()){
                int delNum = deliveries[stackDel.peek()];
                
                if(cntDel >= delNum){
                    cntDel -= delNum;
                    if(!stackDel.isEmpty()){
                        stackDel.pop();
                    }
                }else{
                    deliveries[stackDel.peek()] = delNum - cntDel;
                    cntDel = 0;
                }
            }
            // 모든 수거 다 할 때까지 수거
            while(cntPick > 0 && !stackPick.isEmpty()){
                int pickNum = pickups[stackPick.peek()];
                
                if(cntPick >= pickNum){
                    cntPick -= pickNum;
                    if(!stackPick.isEmpty()){
                        stackPick.pop();
                    }
                }else{
                    pickups[stackPick.peek()] = pickNum - cntPick;
                    cntPick = 0;
                }
            }
            cntDel = cap;
            cntPick = cap;
        }
        
        while(!stackDel.isEmpty()){
            answer += 2*(stackDel.peek() + 1);
            while(cntDel > 0 && !stackDel.isEmpty()){
                int delNum = deliveries[stackDel.peek()];
                
                if(cntDel >= delNum){
                    cntDel -= delNum;
                    if(!stackDel.isEmpty()){
                        stackDel.pop();
                    }
                }else{
                    deliveries[stackDel.peek()] = delNum - cntDel;
                    cntDel = 0;
                }
            }
            cntDel = cap;
        }
        while(!stackPick.isEmpty()){
            answer += 2*(stackPick.peek() + 1);
            while(cntPick > 0 && !stackPick.isEmpty()){
                int pickNum = pickups[stackPick.peek()];
                
                if(cntPick >= pickNum){
                    cntPick -= pickNum;
                    if(!stackPick.isEmpty()){
                        stackPick.pop();
                    }
                }else{
                    pickups[stackPick.peek()] = pickNum - cntPick;
                    cntPick = 0;
                }
            }
            cntPick = cap;
        }
        
        return answer;
    }
}
