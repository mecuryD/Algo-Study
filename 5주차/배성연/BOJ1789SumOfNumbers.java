package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1789SumOfNumbers {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(bf.readLine());

		int N = 1;
		
		while(true) {
			if(S >= N)
				S-= N;
			else break;
			N++;
		}
		System.out.println(--N);
	}

}
