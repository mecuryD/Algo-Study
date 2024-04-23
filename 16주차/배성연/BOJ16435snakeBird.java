package algo0221;
import java.util.*;
import java.io.*;

public class BOJ16435snakeBird {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int L = Integer.parseInt(stk.nextToken());
		int[] fruitList = new int[11001];
		int temp = 0;
		int start = 1;
		int end = L+1;
		int diff = 0;
		
		// 해당 길이 인덱스에 해당하는 수 1
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			temp = Integer.parseInt(stk.nextToken());
			fruitList[temp]++;
		}
		
		// 0부터 N까지 먹을 수 있는 과일 세기
		for(int i = start; i < end; i++) {
			diff += fruitList[i];
		}
		// 다음 수까지 더해지는 숫자가 없거나 시작과 끝이 동일하면 끝
		while(diff != 0) {
			// 먹을 수 있는 과일만큼 성장
			start = end;
			end += diff;
			L += diff;
			diff = 0;
			// 성장 후 먹을 수 있는 과일만큼 세기
			for(int i = start; i < end; i++) {
				diff += fruitList[i];
			}
		}
		System.out.println(L);
	}
	
}
