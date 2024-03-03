package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111minecraft {
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(stk.nextToken());		// 세로 크기
		int M = Integer.parseInt(stk.nextToken());		// 가로 크기
		int B = Integer.parseInt(stk.nextToken());		// 초기 블록 수
		int min = 256;
		int max = 0;
		
		// 일단 집어넣기
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] > max) {max = map[i][j];}
				if(map[i][j] < min) {min = map[i][j];}
			}
		}
		
		int minTime = Integer.MAX_VALUE;
		int height = 0;
		// 최소값으로 고르는 경우부터 최대값으로 고르는 경우까지(여러개면 후자여야 하기 때문)
		for(int t = min; t <= max; t++) {
			int time = 0;
			int tempB = B;
			// 모든 블럭 돌면서
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] > t) {			// 더 높으면 같을 때까지 깎아내기
						int gap = map[i][j] - t;
						time += 2 * gap;
						tempB += gap;
					}else if (map[i][j] < t) {	// 더 낮으면 같을 때까지 쌓기
						int gap = t - map[i][j];
						time += gap;
						tempB -= gap;
					}
				}
			}
			// 다 돌리고 나서 블럭이 마이너스 아니면
			if(tempB >= 0) {
				if(minTime >= time) {
					minTime = time;
					height = t;
				}
			}
		}
		System.out.println(minTime + " " + height);
		
	}
	
}
