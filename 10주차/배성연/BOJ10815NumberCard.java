package algo0221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ10815NumberCard {
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(bf.readLine());
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			map.put(Integer.parseInt(stk.nextToken()), 0);
		}
		
		int M = Integer.parseInt(bf.readLine());
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < M; i++) {
			int temp = Integer.parseInt(stk.nextToken());
			if(map.get(temp) != null) {
				sb.append(1 + " ");
			}else {
				sb.append(0 + " ");
			}
		}
		
		System.out.println(sb);
	}
	
}
