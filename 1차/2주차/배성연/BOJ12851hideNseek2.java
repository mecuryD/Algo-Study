package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851hideNseek2 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		int[] map = new int[100001];		//방문체크 맵, 최소 거리 저장
		map[N] = 200000;					//첫번째 자리 방문체크
		
		//이동 방식 : X-1, X+1, 2X
		int maxTime = Math.abs(N - K) + 1;
		int ansTime = 0;
		int ansCnt = 0;
		
		if(N == K) {						//처음에 같은 곳에 있으면 출력 후 종료
			System.out.println("0");
			System.out.println("1");
			return;
		}
		
		//처음에 갈 수 있는 곳 싹 다 집어넣기
		Queue<Integer> Q = new LinkedList<Integer>();
		if(N-1 > -1) Q.add(N-1);
		if(N+1 < 100001) Q.add(N+1);
		if(N*2 < 100001) Q.add(N*2);
		
		for(int t = 1; t < maxTime; t++) {
			int sizeOfQ = Q.size();				//t초에 갈 수 있는 곳 다 돌기
			
			for(int i = 0; i < sizeOfQ; i++) {
				int cur = Q.poll();
//				System.out.println("============================");
//				System.out.println("i : " + i + ", ansTime : "+ ansTime + ", ansCnt : " + ansCnt);
//				System.out.println("cur : " + cur);
//				System.out.println("Q : " + Arrays.toString(Q.toArray()));
//				System.out.println("map : " + Arrays.toString(map));
				
				if(cur == K) {				//이번에 간 곳이 동생 위치면
					ansTime = t;			//정답에 현 시간 넣고
					ansCnt++;				//정답 카운터 증가
				}
				
				if(map[cur] == 0) {			//이전에 방문한 적 없으면
					map[cur] = t;			//현 시간 집어넣기
				}
				
				//현 위치에서 갈 수 있는, 방문한 적 없는 곳 싹 넣기
				if(cur-1 > -1 && map[cur-1] == 0) Q.add(cur-1);
				if(cur+1 < 100001 && map[cur+1] == 0) Q.add(cur+1);
				if(cur*2 < 100001 && map[cur*2] == 0) Q.add(cur*2);
			}
			if(ansCnt > 0) break;			//정답에 1번 이상 도달했으면 종료
		}
		System.out.println(ansTime);
		System.out.println(ansCnt);
	}
	
}
