package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806bubunSum {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int S = Integer.parseInt(stk.nextToken());
		
		int[] map = new int[N+1];
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(stk.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int len = Integer.MAX_VALUE;
		int sum = 0;
		//크기 범위가 맞는 동안
		while(start <= end && end <= N) {
//			System.out.println("start : " + start + ", end : " + end);
			if(sum < S) {
				//부분합이 S보다 작으면 끝 수 더하고 끝 늘리기
				sum += map[end];
				end++;
			}else {
				//end - start 길이 최소 길이랑 비교 후 더 작으면 저장
				if(len > end - start) len = end - start;
				//맨 앞 수 빼고 뒤로 한 칸 물러나기
				sum -= map[start];
				start++;
			}
		}
		if(len == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(len);
		}
	}
}
