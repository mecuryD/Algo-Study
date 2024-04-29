import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [백준1106] 호텔
public class Main {

	static int C;
	static int N;
	static int result;
	static int[] table;
	static int[][] info;
	
	public static void main(String[] args) throws IOException{
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		
		info = new int[N][2];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			info[i][1] = Integer.parseInt(stk.nextToken()); // 비용
			info[i][0] = Integer.parseInt(stk.nextToken()); // 고객 수
		}
		
		// DP 테이블 초기화
		table = new int[2001];
		for(int i=1; i<=2000; i++) {
			table[i] = 999999;
		}
		
		// DP
		result = 999999;
		for(int i=1; i<=2000; i++) {
			for(int j=0; j<N; j++) {
				int idx = i - info[j][0];
				if(idx >= 0) {
					table[i] = Math.min(table[i], table[idx] + info[j][1]);
				}
			}
			result = (i >= C) ? Math.min(result, table[i]) : result;
		}
        
		// 결과 출력
		System.out.print(result);
	}
}
