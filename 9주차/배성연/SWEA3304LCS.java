package codingTest0220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3304LCS {
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			int ans = 0;
			
			StringTokenizer stk = new StringTokenizer(bf.readLine());
			String st1 = stk.nextToken();
			String st2 = stk.nextToken();
			int length1 = st1.length();
			int length2 = st2.length();
			
//		      c a p c a k
//		    a 0 1 1 1 1 1
//		    c 1 1 1 2 2 2
//		    a 1 2 2 2 3 3
//		    y
//		    k
//		    p
			
			int[][] LCS = new int[length1][length2];
			for(int i = 0; i < length1; i++) {
				for(int j = 0; j < length2; j++) {
					// 가로 세로가 같은지 확인 : 다르면 가로세로 중 큰 수 당겨오기
					if(st1.charAt(i) != st2.charAt(j)) {
						if(i == 0 && j == 0) continue;
						else if(i == 0 && j != 0) {
							LCS[i][j] = LCS[i][j-1];
						}else if(i != 0 && j == 0) {
							LCS[i][j] = LCS[i-1][j];
						}else {
							LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
						}
						continue;
					}
					// 가로 세로 같으면
					if(j == 0) {					// 첫 문자면 1
						LCS[i][j] = 1;
					}else if(i == 0 && j != 0) {	// 첫 줄 문자면 옆+1
						LCS[i][j] = LCS[i][j-1] + 1;
					}else {							// 가운데면 대각선 +1
						LCS[i][j] = LCS[i-1][j-1] + 1;
					}
				}
			}
			
			ans = LCS[length1-1][length2-1];
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}
	
}
