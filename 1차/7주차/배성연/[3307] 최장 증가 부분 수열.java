package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3307LIS {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			int ans = 0;
			
			int N = Integer.parseInt(bf.readLine());
			int[] map = new int[N];
			int[] LIS = new int[N];
			LIS[0] = 1;
			
			StringTokenizer stk = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				map[i] = Integer.parseInt(stk.nextToken());
			}
			// 모든 자리 수 돌면서
			for(int i = 1; i < N; i++) {
				// LIS배열 초기화(해당 자리가 끝에 들어가는 최소 LIS는 1)
				LIS[i] = 1;
				for(int j = 0; j < i; j++) {
					// 해당 자리가 끝에 들어가는 최소 LIS 중 제일 큰 값 찾아서 갱신
					// =해당 자리가 끝에 들어감(해당 자릿수가 더 큼)
					// && 최소 LIS가 제일 큼(이전 자리 LIS에 해당자리 추가한 값이 제일 큼)
					if((map[j] < map[i])&&(LIS[j] + 1 > LIS[i])) {
						LIS[i] = LIS[j] + 1;
					}
				}
				// LIS값 갱신했으면 ans 갱신
				if(ans < LIS[i]) {
					ans = LIS[i];
				}
			}
			sb.append(ans + "\n");
		}
			System.out.println(sb);
	}

}
