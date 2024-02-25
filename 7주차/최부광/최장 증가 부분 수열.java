import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [SWEA3307] 최장 증가 부분 수열
public class Solution {

    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            // 입력
            int N = Integer.parseInt(br.readLine());
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            int[] arr = new int[N];
            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            int[] table = new int[N];
            table[0] = 1;


            // 다이나믹 프로그래밍 - 보텀업
            for(int i=1; i<N; i++){
                table[i] = 1;
                for(int j=0; j<i; j++){
                    // arr[i]보다 작으면서 가장 긴 부분 수열의 길이를 찾는다
                    if(arr[j] < arr[i] && table[i] < table[j]+1){
                        table[i] = table[j]+1;
                    }
                }

                //  i번째까지 가장 긴 부분 수열의 길이 업데이트
                max = 1;
                for(int j=0; j<N; j++){
                   max = Math.max(max, table[j]);
                }
            }

            // 결과 출력
            System.out.println("#" + t + " " + max);
        }
    }
}
