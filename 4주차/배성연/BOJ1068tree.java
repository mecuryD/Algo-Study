package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1068tree {
	
	static List<Integer>[] graph;
	static int[] parents;
	static int N, target;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		graph = new ArrayList[N];
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		int root = 0;
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			parents[i] = Integer.parseInt(stk.nextToken());
			if(parents[i] > -1) {
				graph[parents[i]].add(i);
			}else {
				root = i;
			}
		}
		target = Integer.parseInt(bf.readLine());
		
		int targetParent = parents[target];
		deleteDfs(target);
		// 삭제한 놈 위에서 삭제한 놈으로 향하는 선 지우기
		if(targetParent > 0) {
			for(int i = 0; i < N; i++) {
				if(graph[targetParent].get(i) == target) {
					graph[targetParent].remove(i);
					break;
				}
			}
		}
		
//		System.out.println(Arrays.toString(parents));
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(graph[i].toArray()));
//		}
		
		// leaf 구하기 : BFS
		int ans = 0;
		boolean[] isVisited = new boolean[N];
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(root);
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			
			// leaf인지 확인
			if(graph[cur].isEmpty() && parents[cur] > -1) {
//				System.out.println("cur : "+cur);
				ans++;
				continue;
			}
			
			// leaf 아니면 다음 노드 전부 더하기
			for(int i = 0; i < graph[cur].size(); i++) {
				Q.add(graph[cur].get(i));
			}
		}
		
		//루트 노드를 더하게 되므로 루트 노드만 남는 경우 +1
		if(ans == 0 && graph[root].size() == 1) {
			ans++;
		}
		System.out.println(ans);
	}
	
	private static void deleteDfs(int cur) {
		// 자식 노드 탐색
		for(int i = 0; i < graph[cur].size(); i++) {
			deleteDfs(graph[cur].get(i));
		}
		//자식 노드 삭제
		parents[cur] = -10;
		graph[cur].clear();
	}
}
