package algo0221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15686chickenDelivery {
	
	static int[][] map;
	static int cLenMin = 0;
	static int M, cCnt, hCnt, ans;
	static boolean[] open;
	static int[][] cList;
	static int[][] hList;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][N];
		cList = new int[13][2];
		hList = new int[2*N][2];
		cCnt = 0;
		hCnt = 0;
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == 2) {
					cList[cCnt][0] = i;
					cList[cCnt][1] = j;
					cCnt++;
				}else if(map[i][j] == 1) {
					hList[hCnt][0] = i;
					hList[hCnt][1] = j;
					hCnt++;
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		open = new boolean[13];
		dfs(0, 0);
		
		System.out.println(ans);
	}
	public static void dfs(int start, int cnt) {
		if(cnt == M) {
			// 각 집의 최소치킨거리 합
			int sum = 0;
			for(int i = 0; i < hCnt; i++) {
				int tempMin = Integer.MAX_VALUE;
				for(int j = 0; j < cCnt; j++) {
					if(!open[j]) continue;
					int dist = Math.abs(hList[i][0] - cList[j][0])
							+ Math.abs(hList[i][1] - cList[j][1]);
					if(tempMin > dist) {
						tempMin = dist;
					}
				}
				sum+= tempMin;
			}
			if(ans > sum) {
				ans = sum;
			}
			return;
		}
		
		for(int i = start; i < cCnt; i++) {
			open[i] = true;
			dfs(i + 1, cnt + 1);
			open[i] = false;
		}
	}
	
}
