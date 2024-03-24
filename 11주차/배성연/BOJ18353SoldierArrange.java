package algo0221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18353SoldierArrange {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		int[] map = new int[N];
		
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(stk.nextToken());
		}
		
		// 최장 감소 부분 수열(LDS)
		int[] LDS = new int[N];
		int maxLen = 0;
		for(int i = N-1; i > -1; i--) {
			// 뒤에서부터 돌면서
			LDS[i] = 1;
			for(int j = N-1; j > i; j--){
				// 뒤쪽에 있는 게 더 작으면 ++
				if(map[j] < map[i] && LDS[j] + 1 > LDS[i]) {
					LDS[i] = LDS[j] + 1;
				}
			}
			if(LDS[i] > maxLen) {
				maxLen = LDS[i];
			}
//			System.out.println(Arrays.toString(LDS));
			
		}
		
		System.out.println(N - maxLen);
	}
	
}
