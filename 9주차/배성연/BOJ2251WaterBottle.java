package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2251WaterBottle {
	
	static int A, B, C;
	static int[] canWater;
	static boolean[][] visitedAB;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		canWater = new int[601];
		visitedAB = new boolean[201][201];
		
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		dfs(0, 0, C);
		if(canWater[0] > 0)
			sb.append(0 + " ");
		for(int i = 1; i < 600; i++) {
			if(canWater[i] != 0) {
				sb.append(canWater[i] + " ");
			}
		}
		System.out.println(sb);
	}
	
	static void dfs(int Acur, int Bcur, int Ccur) {
		// 이미 해봤다면 넘어가기
		if(visitedAB[Acur][Bcur]) return;
		
		// 방문체크
		visitedAB[Acur][Bcur] = true;
		
		// C 갱신
		if(Acur == 0) {
//			System.out.println(Acur + ", " + Bcur + ", " + Ccur);
			if(Ccur == 0) {
				canWater[0] = 1;
			}else {
				canWater[Ccur] = Ccur;
			}
		}
		
		// 3개를 각각 옮겨서 dfs
		// A->B
		if(Acur + Bcur > B) {
			int diff = Acur + Bcur - B;
			dfs(diff, B, Ccur);
		}else {
			dfs(0, Acur + Bcur, Ccur);
		}
		// A->C
		if(Acur + Ccur > C) {
			int diff = Acur + Ccur - C;
			dfs(diff, Bcur, C);
		}else {
			dfs(0, Bcur, Acur + Ccur);
		}
		// B->A
		if(Bcur + Acur > A) {
			int diff = Bcur + Acur - A;
			dfs(A, diff, Ccur);
		}else {
			dfs(Bcur + Acur, 0, Ccur);
		}
		// B->C
		if(Bcur + Ccur > C) {
			int diff = Bcur + Ccur - C;
			dfs(Acur, diff, C);
		}else {
			dfs(Acur, 0, Bcur + Ccur);
		}
		// C->A
		if(Ccur + Acur > A) {
			int diff = Ccur + Acur - A;
			dfs(A, Bcur, diff);
		}else {
			dfs(Ccur + Acur, Bcur, 0);
		}
		// C->B
		if(Ccur + Bcur > B) {
			int diff = Ccur + Bcur - B;
			dfs(Acur, B, diff);
		}else {
			dfs(Acur, Ccur + Bcur, 0);
		}
	}
	
}
