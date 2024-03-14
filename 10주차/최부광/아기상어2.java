import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준17086] 아기 상어2
public class Main {
	
	static int result = 0;
	static int[][] dir = new int[][] {{-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		Queue<int[]> queue;
		boolean[][] visited;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				// 아기 상어가 있는 칸
				if(map[i][j]==1) continue;
				
				// 아기 상어가 없는 칸
				queue = new ArrayDeque<int[]>();
				visited = new boolean[N][M];
				
				queue.offer(new int[] {i,j,0});
				visited[i][j] = true;
				
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					// 가장 가까운 아기 상어를 만나면 BREAK
					if(map[cur[0]][cur[1]]==1) {
						result = (result < cur[2]) ? cur[2] : result;
						break;
					}
					
					// 상어를 만나지 못했으면 더 나아간다
					for(int[] d : dir) {
						int nr = cur[0] + d[0];
						int nc = cur[1] + d[1];
						if(nr>=0 && nc>=0 && nr<N && nc<M && !visited[nr][nc]) {
							queue.offer(new int[]{nr,nc,cur[2]+1});
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		// 결과 출력
		System.out.println(result);
	}
}
