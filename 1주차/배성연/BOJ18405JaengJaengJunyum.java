package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18405JaengJaengJunyum {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		//상하좌우로 1초마다 증식, 번호 낮은 놈부터 증식, 이미 뭐 있으면 증식x
		
		stk = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		//NxN 시험관에 각각 바이러스 1~K 집어넣기
		int[][] map = new int[N][N];
		//바이러스 작은 것부터 위치 저장해둘 linkedlist queue(내용물로 비교)
		PriorityQueue<int[]> virus = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
		
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());	//map에 저장
				if(map[i][j] != 0) {	//바이러스면 저장
					virus.add(new int[] {i, j, map[i][j]});		//x좌표, y좌표, 내용물
				}
			}
		}
		stk = new StringTokenizer(bf.readLine());
		int S = Integer.parseInt(stk.nextToken());	//지나는 초
		int X = Integer.parseInt(stk.nextToken()) - 1;	//확인할 좌표
		int Y = Integer.parseInt(stk.nextToken()) - 1;
		
		//입력 끝
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		for(int s = 0; s < S; s++) {	//S초만큼 반복
			int size = virus.size();
			//새로 나온 거 넣을 임시 큐
			PriorityQueue<int[]> temp = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
			
			for(int re = 0; re < size; re++) {	//모든 바이러스 돌면서
				int[] cur = virus.poll();		//앞에서부터 뽑아
				
				for(int i = 0; i < 4; i++) {	//그 4방위 돌면서
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					
					if(map[nx][ny] == 0) {		//비어있으면
						map[nx][ny] = cur[2];	//map 채우고
						temp.add(new int[] {nx, ny, cur[2]});	//바이러스 임시 큐에 추가
					}
				}
			}
			//다 돌았으면 임시 큐 옮기기
			virus.addAll(temp);
		}
		
		System.out.println(map[X][Y]);
	}
	
}
