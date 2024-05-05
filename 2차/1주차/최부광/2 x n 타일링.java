// [프로그래머스] 2 x n 타일링
// M[N] = M[N-1] + M[N-2]
// M[N-1] : 가로 1, 세로 2인 타일을 새롭게 놓는 경우
// M[N-2] : 가로 2, 세로 1인 타일을 새롭게 놓는 경우
class Solution {
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        
        for(int i=3; i<=n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        
        return dp[n];
    }
}
