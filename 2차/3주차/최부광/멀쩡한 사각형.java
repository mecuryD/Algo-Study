import java.util.*;

// [프로그래머스] 멀쩡한 사각형
class Solution {
    
    static double degree; // 기울기
    
    public long solution(int w, int h) {
        // 모든 컬럼에 대해 사용하지 못하는 정사각형의 개수 계산
        long count = 0;
        for(int i=0; i<w; i++){
            long left = (long) Math.ceil(calc(w, h, i));
            long right = (long) Math.floor(calc(w, h, i+1));
            count = count + left - right;
        }
        
        // 가능한 정사각형의 개수를 구한다
        long answer = (long) w * (long) h -  count;
        return answer;
    }
    
    public double calc(double w, double h, int c){
        return h - (h * c / w);
    }
}
