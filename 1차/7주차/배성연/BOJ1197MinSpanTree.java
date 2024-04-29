package algo0221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1197MinSpanTree {
	// Kruskal 알고리즘 사용
	// +union find
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static Edge[] edgeList;
	static int V, E;
	static int[] parents;
	
	static void make() {
		parents = new int[V+1];
		for(int i = 0; i < V+1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		// 루트 같으면 같은 사이클 내 => 사이클 발생!!
		if(aRoot == bRoot) return false;
		// 같은 사이클 아니면 합치기
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		int ans = 0;
		int cntEdge = 0;
		make();
		
		edgeList = new Edge[E];
		// 무향 그래프이지만 사이클 구하기 쉽도록 유향인 것처럼 납작 누르기
		for(int i = 0; i < E; i++) {
			stk = new StringTokenizer(bf.readLine());
			int A = Integer.parseInt(stk.nextToken());
			int B = Integer.parseInt(stk.nextToken());
			int C = Integer.parseInt(stk.nextToken());
			edgeList[i] = new Edge(A, B, C);
		}
		
		// 가중치 작은 간선부터 보기 위해 오름차순 정렬
		Arrays.sort(edgeList);
		
		// 각 간선 뽑아서 걔 포함하면 사이클되면 버리고 아니면 포함하기
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				// 포함했다면 가중치 더하기
				ans += edge.weight;
				// 모든 노드 포함했으면 끝내기
				if(++cntEdge == V-1) {
					break;
				}
			}
		}
		System.out.println(ans);
	}
	
}
