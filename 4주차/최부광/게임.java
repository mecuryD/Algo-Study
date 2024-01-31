import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준1072] 게임
public class Main{

    // 입력
    static int X;
    static int Y;
    static double Z;
    // 이분탐색에 사용
    static int count; //  게임 횟수

    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        X = Integer.parseInt(stk.nextToken());
        Y = Integer.parseInt(stk.nextToken());
        Z = (int)Math.floor((Y * 100.0 / X));

        // 이분 탐색
        count = -1;
        int start = 0;
        int end = 1_000_000_000;
        while(start<=end){
            int mid = (start+end)/2;
            // 새로운 승률 계산
            int ratio = (int) Math.floor((Y+mid) * 100.0 / (X+mid));
            if(ratio > Z){
                end = mid-1;
                count = mid;
            }else{
                start = mid+1;
            }
        }

        // 결과 출력
        if(count == -1) System.out.println(-1);
        else System.out.println(count);
    }
}
