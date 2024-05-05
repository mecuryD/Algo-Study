import java.util.*;

// [프로그래머스] 예상 대진표
class Solution
{
    public static int solution(int n, int a, int b) {
        // 항상 b의 값이 크다
        if(b < a){
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        // a와 b의 라운드에 따른 번호를 계산한다
        int round = 0;
        while(b > 1) {
            // 라운드 증가
            round++;
            
            // a와 b가 붙을 수 있는지 체크한다
            if(Math.abs(a-b)==1 && b%2==0) break;
            
            // 다음 라운드를 진행한다
            a = (a%2==0) ? a/2 : (a+1)/2;
            b = (b%2==0) ? b/2 : (b+1)/2;
        } 
        
        return round;
    }
}
