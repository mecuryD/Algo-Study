package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573BingSan {
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(stk.nextToken());		// 행
		int M = Integer.parseInt(stk.nextToken());		// 열
		int ans = 0;
		
		int[][] map = new int[N][M];
		int[][] newMap = new int[N][M];
		Queue<int[]> Q = new LinkedList<int[]>();
		
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				newMap[i][j] = map[i][j];
				if(map[i][j] > 0) Q.add(new int[] {i, j});
			}
		}
		
		int size = Q.size();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int y = 0;
		while(!Q.isEmpty()) {		// Q에 뭐 남아있는 동안 년수 높여가며 반복
			y++;
			// Q 돌면서 기존 map 4방향의 0 수만큼 빼서 newMap에 넣기
			for(int i = 0; i < size; i++) {
				// 기존 거 뽑아서
				int[] cur = Q.poll();
				int diff = 0;
				// 4방향 탐색 후
				for(int j = 0; j < 4; j++) {
					int nx = cur[0] + dx[j];
					int ny = cur[1] + dy[j];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					if(map[nx][ny] == 0) diff++;
				}
				
				int temp = map[cur[0]][cur[1]] - diff;
				// 0보다 높으면 newMap에 넣기
				if(temp > 0) {
					newMap[cur[0]][cur[1]] = temp;
					Q.add(new int[] {cur[0], cur[1]});
				}else {
					newMap[cur[0]][cur[1]] = 0;
				}
			}
			
			// 다 옮기면 newMap 다시 map으로 넣기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					map[i][j] = newMap[i][j];
				}
			}
			
//			System.out.println("map!!");
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("====================");
			
			// Q 사이즈 갱신
			size = Q.size();
			
			// newMap 방문체크배열로 이용하며 bfs로 조각 나뉜 거 찾기
			int cnt = 0;
			Queue<int[]> tempQ = new LinkedList<int[]>();
			if(!Q.isEmpty()) tempQ.add(Q.peek());			// 처음 거 집어넣기
			while(!tempQ.isEmpty()) {
				// 기존 거 뽑아서
				int[] cur = tempQ.poll();
				
				if(newMap[cur[0]][cur[1]] > 20) continue;	// 방문체크했으면 넘기기
				
				newMap[cur[0]][cur[1]] = 21;				// 방문체크
				cnt++;										// 들른 얼음 수+1
				// 4방향 탐색 후
				for(int j = 0; j < 4; j++) {
					int nx = cur[0] + dx[j];
					int ny = cur[1] + dy[j];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					if(newMap[nx][ny] != 0) {				//주변에 다른 얼음 있으면 tempQ에 추가
						tempQ.add(new int[] {nx, ny});
					}
				}
			}
			
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(newMap[i]));
//			}
//			System.out.println("====================");
//			System.out.println("Q size : " + size);
//			System.out.println("하나인 것 : " + cnt);
//			System.out.println("Y : " + y);
			
			// 한 곳에서 시작해서 연결된 곳 다 돌았는데 전체를 돈 것이 아니다 = 2조각 이상 분할됨
			if(size != cnt) {
				ans = y;
				break;
			}
		}
		System.out.println(ans);
	}
	
}
