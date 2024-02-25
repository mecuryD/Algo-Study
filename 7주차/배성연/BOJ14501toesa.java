package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501toesa {
	
	static int[][] map;
	static int N, ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][2];
		ans = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(bf.readLine());
			map[i][0] = Integer.parseInt(stk.nextToken());
			map[i][1] = Integer.parseInt(stk.nextToken());
		}
		
		dfs(0, 0);
		System.out.println(ans);
	}
	
	static void dfs(int cur, int curP) {
		// 범위 넘으면 정답 갱신 : 현재 페이로
		if(cur >= N) {
			if(ans < curP) {
				ans = curP;
			}
			return;
		}
		
		// 현재 일부터 하루씩 늘려가며 다 해보기
		for(int j = cur; j < N; j++) {
			int next = j + map[j][0];	// 현재 일 수행 끝난 날 계산
			if(next <= N) {				// 현재 일까지 가능하면 현재 일 페이도 추가
				dfs(next, curP + map[j][1]);
			}else {						// 현재 일까지 못하면 이전 일 페이까지만
				dfs(next, curP);
			}
		}
	}
	
}
