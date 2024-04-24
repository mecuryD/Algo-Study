import java.util.*;

class Solution {
    int solution(int[][] land) {
        
        int[][] dp = new int[2][4];
        
        for(int[] cur : land){
            // 현재 블럭을 밟을 때의 최대 값을 구한다
            for(int i=0; i<4; i++){
                int max = dp[0][i];
                
                for(int j=0; j<4; j++){
                    if(i!=j){
                        max = (max < (dp[0][j] + cur[j])) ? (dp[0][j] + cur[j]) : max;   
                    }
                }
                
                dp[1][i] = max;
            }
            
            // 다음 행을 탐색하기 위해 DP 배열 복사
            dp[0] = new int[]{dp[1][0], dp[1][1], dp[1][2], dp[1][3]};
        }
        
        // 얻을 수 있는 점수의 최대값을 찾는다
        int answer = 0;
        for(int val : dp[0]){
            answer = (answer < val) ? val : answer;
        }

        return answer;
    }
}
