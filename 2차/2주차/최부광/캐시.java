import java.util.*;

// [프로그래머스] 캐시
class Solution {
    public int solution(int cacheSize, String[] cities) {
        // 캐시 크기가 0이면 모든 경우에 대해 Cache Miss
        if(cacheSize == 0) return (5 * cities.length);
        
        // 캐시 크기가 1 이상인 경우
        Queue<String> queue = new ArrayDeque<>();
        int totalTime = 0;
        
        // 모든 도시에 대해 반복
        for(String city : cities){
            // 대소문자 구분하지 않으므로 소문자로 변환 
            city = city.toLowerCase(); 
            int time = 0; 
            
            // 캐시에 존재하는 경우
            if(queue.contains(city)){
                // 최근 사용 시점 업데이트를 위해 해당 원소 제거
                queue.remove(city);
                time = 1;
            }else{
                // LRU 알고리즘에 따라 원소 1개 제거
                if(queue.size() == cacheSize) {
                    queue.poll();
                }
            }
            
            totalTime += (isExist) ? 1 : 5;
            queue.offer(city);
        }
        
        return totalTime;
    }
}
