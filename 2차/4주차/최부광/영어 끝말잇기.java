import java.util.*;

// [프로그래머스] 영어 끝말잇기
class Solution {
    
    static int[] result = new int[2];
    static Set<String> history = new HashSet<String>();
    
    public int[] solution(int n, String[] words) {
        history.add(words[0]);
        
        // 단어 배열을 순회하면서 끝말잇기 조건 확인
        int turn = 1;
        int player = 1;
        int lenW = words.length;
        
        for(int i=1; i<lenW; i++){
            boolean flag = false;
            
            // 기존 출제 여부 체크
            if(!history.add(words[i])) flag = true;

            // 끝말잇기 조건 체크
            if(words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)) flag = true;
            
            // 만약 탈락자가 생긴다면
            if(flag){
                result[1] = i/n + 1;
                result[0] = i%n + 1;
                break;
            }
        }
        
        return result;
    }
}
