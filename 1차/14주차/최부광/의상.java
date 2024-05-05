import java.util.*;

// [프로그래머스] 의상
class Solution {
        
    public int solution(String[][] clothes) {
        // 의상 정보를 해시맵에 저장한다
        Map<String, Integer> map = new HashMap<>();
        for(String[] c : clothes){
            if(!map.containsKey(c[1])){
                map.put(c[1], 0);
            }
            
            // 해시맵에 의상 정보 저장
            map.put(c[1], map.get(c[1]) + 1);
        }
        
        // 주어진 의상에 대해, 서로 다른 옷의 조합의 수를 구한다
        Set<String> categories = map.keySet();
        int count = categories.size();
        int result = 1;
        
        for(String c : categories){
            result *= (map.get(c) + 1);
        }
        
        return result - 1;
    }
}
