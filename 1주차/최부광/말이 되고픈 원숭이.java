import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준1600] 말이 되고픈 원숭이
public class Main {
	
	static int K;
	static int W;
	static int H;
	static int[][] map;
	
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int[][] horse = {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,-2}, {2,-1},{2,1},{1,2}};
	
	
	static int min = Integer.MAX_VALUE; // 결과값
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		
		map = new int[H][W];
		for(int i=0; i<H; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 원숭이가 도착지점을 향해 이동한다
		moveMonkey();		
		
		
		// 결과 출력
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	// 원숭이 이동 메서드
	public static void moveMonkey() {
		// 너비 우선 탐색
		Queue<Pos> queue = new ArrayDeque<Pos>();
		boolean[][][] visited = new boolean[H][W][K+1];
		
		queue.offer(new Pos(0,0,0,0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			// 위치 하나를 꺼낸다
			Pos cur = queue.poll();
			// 만약 도착 지점이라면 리턴
			if(isDestination(cur)) {
				min = cur.cnt;
				return;
			}
			
			// 도착 지점이 아니라면 원숭이가 이동
			
			// 말처럼 먼저 움직인다
			if(cur.horse < K) {
				for(int i=0; i<8; i++) {
					int nr = cur.r + horse[i][0];
					int nc = cur.c + horse[i][1];
					// 말 이동 횟수에 따라 최단거리를 저장한다
					if(canMove(nr, nc, cur.horse+1,visited)) {
						visited[nr][nc][cur.horse+1] = true;
						queue.offer(new Pos(nr, nc, cur.cnt+1, cur.horse+1));
					}
				}
			}
			
			// 인접 지역으로 움직인다
			for(int i=0; i<4; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];
				// 말 이동 횟수에 따라 최단거리를 저장한다
				if(canMove(nr, nc, cur.horse, visited)) {
					visited[nr][nc][cur.horse] = true;
					queue.offer(new Pos(nr, nc, cur.cnt+1, cur.horse));
				}
			}
		}
	}
	
	// 이동 가능한 곳인지 확인하는 메서드
	public static boolean canMove(int r, int c, int l, boolean[][][] visited) {
		if(r<0 || r>=H || c<0 || c>=W || map[r][c]==1 || visited[r][c][l]) return false;
		return true;
	}

	// 도착지점 확인 메서드
	public static boolean isDestination(Pos cur) {
		if(cur.r==H-1 && cur.c==W-1) return true;
		else return false;
	}
  
	// 위치 클래스
	static class Pos {
		int r;
		int c;
		int cnt;  // 이동 횟수
		int horse; // 말 이동 횟수
		
		public Pos(int r, int c, int cnt, int horse) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.horse = horse;
		}
	}
}
