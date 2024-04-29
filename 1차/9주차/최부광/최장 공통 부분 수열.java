import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
// [SWEA3304] 최장공통부분수열
public class Solution {
 
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++){
      // 문자열 입력
      StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
      char[] A = stk.nextToken().toCharArray();
      char[] B = stk.nextToken().toCharArray();
 
      int max = 0;
      int la = A.length;
      int lb = B.length;
 
      // DP
      int[][] dp = new int[la+1][lb+1];
      for(int i=1; i<=la; i++){
        for(int j=1; j<=lb; j++){
          if(A[i-1] == B[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
          else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
      }
 
        // 결과 출력
      System.out.println(String.format("#%d %d", t, dp[la][lb]));
    }
  }
}
