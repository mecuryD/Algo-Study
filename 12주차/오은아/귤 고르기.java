import java.util.*;

class Solution {
    public int solution(int k, int[] tan) {
        
        //HashMap key는 귤의 크기, value는 뒷자리는 귤의 갯수
        int ans= 0;
        
        //map에 담기
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i: tan)
            map.put(i, map.getOrDefault(i, 0) + 1);
        
        
        //key 정렬
        List<Integer> keyList= new ArrayList<Integer>(map.keySet());
        
        keyList.sort((k1, k2) -> map.get(k2) - map.get(k1));
        
        //앞에서부터 k개 뽑기
        for(int key: keyList){
            k-= map.get(key);
            ans++;
            if(k <= 0) break;
        }
            
                    
        return ans;
        
        
    }
}
