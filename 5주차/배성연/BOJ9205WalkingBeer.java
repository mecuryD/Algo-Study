import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk;
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			boolean ans = false;
			
			int N = Integer.parseInt(bf.readLine()) + 2;	//집 + 편의점 개수 + 페스티벌
			
			int[][] com = new int[N][2];					//집 좌표 + 편의점 좌표 + 페스티벌 좌표 저장
			for(int i = 0; i < N; i++) {
				stk = new StringTokenizer(bf.readLine());
				com[i][0] = Integer.parseInt(stk.nextToken());
				com[i][1] = Integer.parseInt(stk.nextToken());
			}
			
			// 박스당 맥주 최대 20개, 한 병 있어야 50미터 => 경로사이 거리가 1000 초과하면 안됨
			// 플로이드워샬로 각 지점 간 최단경로 구하기, 가다가 1000 넘는거 버리기
			int[][] D = new int[N][N];
			boolean[][] flag = new boolean[N][N];
			for(int i = 0; i < N; i++) {		//D 초기화 : 각 좌표 간 거리
				for(int j = 0; j < N; j++) {
					int x = Math.abs(com[i][0] - com[j][0]);
					int y = Math.abs(com[i][1] - com[j][1]);
					D[i][j] = x + y;
					
					if(D[i][j] <= 1000) {
						flag[i][j] = true;
					}
				}
			}
			
			for(int k = 0; k < N; k++) {		//집부터 페스티벌까지
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
//						D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);	//k번째 편의점을 경유
						if((flag[i][k] && flag[k][j]) || flag[i][j]) {
							flag[i][j] = true;
						}
					}
				}
			}
			
			ans = flag[0][N-1];
			
			if(ans) {
				sb.append("happy");
			}else {
				sb.append("sad");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		
		// 19분!!!!!!
	}
	
}
