package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1072game {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		
		int X = Integer.parseInt(stk.nextToken());
		int Y = Integer.parseInt(stk.nextToken());
		
		int Z = (int) ((long)Y * 100 / X);
		int ans = -1;
		
		if(Z >= 99) {
			System.out.println(-1);
		}else {
			long start = 0;
			long end = 2000000000;
			
			while(start < end) {
				long mid = (start + end) / 2;
				long tempZ = (Y + mid) * 100 / (X + mid);
				
				if(tempZ > Z) {
					end = mid;
				}else {
					start = mid + 1;
				}
			}
			
			System.out.println(end);
		}
		

	}

}
