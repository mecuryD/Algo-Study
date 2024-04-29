import java.util.*;

public class Solution {

    public int solution(int k, int[] tangerine) {
        
        // 귤을 종류별로 배열에 저장
        int type = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int t : tangerine){
            if(map.containsKey(t)){
                map.put(t, map.get(t)+1);
            }else {
                map.put(t, 1);
                type++;
            }
        }

        // 내림차순으로 버블 정렬
        Integer[] list = map.values().toArray(new Integer[0]);

        for(int i=0; i<type; i++){
            for(int j=i+1; j<type; j++){
                if(list[i] < list[j]){
                    int tmp = list[i];
                    list[i] = list[j];
                    list[j] = tmp;
                }
            }
        }

        // 귤을 상자에 담는다
        int count = 0;
        int result = 0;
        for(int l : list){
            if(count >= k) {
                break;
            }
            
            count+= l;
            result+=1;
        }

        return result;
    }
}
