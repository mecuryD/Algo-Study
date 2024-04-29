import java.util.*;

class Solution {
    
    static int lenW;
    static int lenD;
    static int result = 0;
    static Map<String, Integer> map = new HashMap<String, Integer>();
    
    public int solution(String[] want, int[] number, String[] discount) {
        // 변수 초기화
        result = 0;
        lenW = want.length;
        lenD = discount.length;

        for(String w : want){
            // 특정 날짜에 가입했을 때 정현이가 할인 받을 수 있는 품목 정보 초기화
            map.put(w, 0);
        }
        
        for(int i=0; i<10; i++){
            // 첫 10일동안의 할인 품목 초기화
            if(!map.containsKey(discount[i])) map.put(discount[i], 0);
            map.put(discount[i], map.get(discount[i]) + 1);
        }
        
        // 슬라이딩 윈도우
        int start = 0;
        int end = 9;

        while(true){
            // 할인 품목 달성 여부 체크
            if(canRegister(want, number)){
                result++;
            }

            // 포인터 한 칸 이동
            map.put(discount[start], map.get(discount[start])-1);
            start++;

            end++;
            if(end==lenD) break;
            if(!map.containsKey(discount[end])) map.put(discount[end], 0);
            else map.put(discount[end], map.get(discount[end])+1);
        }


        return result;
    }
    
    // 할인 품목을 모두 달성했는지 체크
    public boolean canRegister(String[] want, int[] number){
        boolean flag = true;
        
        for(int i=0; i<lenW; i++){
            if(map.get(want[i])!=number[i]){
                flag = false;
                break;
            } 
        }
        return flag;
    }
}
