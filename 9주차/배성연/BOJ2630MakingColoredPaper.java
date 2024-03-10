package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630MakingColoredPaper {
	
	static int N;
	static int[][] paper;
	static int white, blue;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(bf.readLine());
		paper = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 자르기 : 전체가 같은 색X인거 가로세로 이등분씩
		// 자른 대상의 시작점과 끝점 기억하고 걔로 dfs
		dfs(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	static void dfs(int row, int col, int size) {
		// 하나짜리면 갱신 후 return
		if(size == 1) {
			if(paper[row][col] == 0) {
				white++;
			}else {
				blue++;
			}
			return;
		}
		
		// 다 돌아보면서 색 더하기
		int tempWhite = 0;
		int tempBlue = 0;
		int cnt = 0;
		int rowMax = row + size;
		int colMax = col + size;
		for(int i = row; i < rowMax; i++) {
			for(int j = col; j < colMax; j++) {
				if(paper[i][j] == 0) {
					tempWhite++;
				}else {
					tempBlue++;
				}
				cnt++;
			}
		}
		
		// 다른 거 없으면 갱신후 return
		if(cnt == tempWhite) {			// 전부 흰색이면
			white++;
			return;
		}else if(cnt == tempBlue) {
			blue++;
			return;
		}
		
		// 다른 거 있으면 4개로 나눠서 다시 dfs
		int diff = size/2;
		dfs(row, col, diff);
		dfs(row + diff, col, diff);
		dfs(row, col + diff, diff);
		dfs(row + diff, col + diff, diff);
		
	}
	
}
