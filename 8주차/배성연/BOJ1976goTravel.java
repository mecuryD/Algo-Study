package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976goTravel {
	
	static int[] parents;
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
		int[] plan = new int[M];
		String ans = "YES";
		
		parents = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer stk = new StringTokenizer(bf.readLine());
			for(int j = 1; j <= N; j++) {
				if(Integer.parseInt(stk.nextToken()) == 1) {
					union(i, j);
				}
			}
		}
		
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(stk.nextToken());
			if(find(plan[i]) != find(plan[0])) {
				ans = "NO";
				break;
			}
		}
		
		System.out.println(ans);
		
	}
}
