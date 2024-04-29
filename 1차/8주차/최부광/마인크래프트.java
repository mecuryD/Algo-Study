import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [백준18111] 마인크래프트
public class Main{
	
	static int N;
	static int M;
	static int B;
	static int[][] map;

	static int minT = Integer.MAX_VALUE;
	static int maxH = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(stk.nextToken()); // 집터 세로 크기
		M = Integer.parseInt(stk.nextToken()); // 집터 가로 크기
		B = Integer.parseInt(stk.nextToken()); // 인벤토리 블록 개수
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 높이 0부터 256까지 소요되는 시간을 체크한다
		for(int i=0; i<257; i++) {
			// 필요한 변수 초기화
			int need = 0; // 필요한 블록 개수
			int left = B; // 사용 가능한 블록 개수
			int time = 0; // 소요되는 시간
			
			// 각 위치에 대해 체크한다
			for(int j=0; j<N; j++) {
				// 필요한 블록 개수, 사용 가능한 블록 개수, 소요되는 시간을 구한다
				for(int k=0; k<M; k++) {
					if(map[j][k] == i) {
						continue; // 현재 높이와 목표 높이가 같다
					}else if(map[j][k] > i) { // 현재 높이가 목표 높이보다 크다
						int diff = map[j][k] - i;
						left += diff;
						time += diff * 2;
					}else if(map[j][k] < i) { // 현재 높이가 목표 높이보다 작다
						int diff = i - map[j][k];
						need += diff;
						time += diff;
					}
				}
			}
			// 해당 높이가 불가능하다면 고려하지 않는다
			if(need > left) continue;
			
			// 해당 높이가 가능하다면, 최소 시간과 최대 높이 갱신
			if(time <= minT && i > maxH) {
				minT = time;
				maxH = i;
			}
		}
		
		// 결과 출력
		System.out.println(String.format("%d %d", minT, maxH));
	}
}
