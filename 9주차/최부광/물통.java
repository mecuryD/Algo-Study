import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준2251] 물통
public class Main {

	static int[] bucket = new int[3];	
	static int[] from = new int[] {0, 0, 1, 1, 2, 2};
	static int[] to = new int[] {1, 2, 0, 2, 0, 1};
	
	public static void main(String[] args) throws IOException {
		// 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		bucket[0] = Integer.parseInt(stk.nextToken());
		bucket[1] = Integer.parseInt(stk.nextToken());
		bucket[2] = Integer.parseInt(stk.nextToken());
		
		// 가능한 모든 물의 양을 구한다
		Queue<int[]> queue = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();
		boolean[][][] visited = new boolean[bucket[0]+1][bucket[1]+1][bucket[2]+1];
		
		queue.offer(new int[] {0, 0, bucket[2]});
		visited[0][0][bucket[2]] = true;
		list.add(bucket[2]);
		
		while(!queue.isEmpty()) {
			// 버킷 상태를 꺼낸다
			int[] cur = queue.poll();
			
			// 6가지 방법을 시도한다
			for(int i=0; i<6; i++) {
				// 옮길 물 양을 측정한다
				int liter = Math.min(cur[from[i]], bucket[to[i]] - cur[to[i]]);
				
				// 물을 옮긴다
				int[] next = new int[] {cur[0], cur[1], cur[2]};
				next[from[i]] -= liter;
				next[to[i]] += liter;
				
				if(!visited[next[0]][next[1]][next[2]]) {
					if(next[0]==0) list.add(next[2]);
					visited[next[0]][next[1]][next[2]] = true;
					queue.offer(next);
				}				
			}
		}
		
		// 결과 출력
		Collections.sort(list);
		
		for(int a : list) {
			System.out.print(a + " ");
		}
	}	
}
