import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [백준2343] 기타 레슨
public class Main{
    static int N; // 강의의 수
    static int M; // 블루레이의 수
    
    static int res = 999999; // 블루레이의 최소 크기

    static int[] arr; // 누적합 배열

    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        arr = new int[N+1];
        stk = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=N; i++){
            arr[i] = arr[i-1] + Integer.parseInt(stk.nextToken());
        }

        // 이분 탐색
        int start = 0;
        int end = arr[N];

        while(start<=end){
            // 임의 블루레이 크기
            int mid = (start+end) / 2;

            // 저장 가능한지 체크
            if(checkSave(mid)){
                res = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        // 결과 출력
        System.out.println(res);
    }

    public static boolean checkSave(int size){
        // 슬라이딩 윈도우
        int start = 0;
        int end = 0;
        int count = 0;

        while(end <= N){
            // 현재 블루레이에 저장할 크기
            int diff = arr[end] - arr[start];
            // 블루레이 용량을 초과하면
            if(diff > size){
                // 새로운 블루레이로 교체
                count++;
                // 해당 크기로 저장할 수 없는 경우
                if(count >= M) return false;
                
                start = end-1;
                continue;
            }
            end++;
        }
        return true;
    }
}
