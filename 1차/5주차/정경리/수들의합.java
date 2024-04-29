package BOJ;

import java.io.*;
public class 수들의합 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(bf.readLine());//S: 서로다른 N개의 자연수의 합
        long N=1;
        while(true){
            //1~N 까지 N개를 더한 수가 처음으로 S를 넘어서면, 그 합에서 특정 숫자 하나를 빼면 S를 만들 수 있다.
            //따라서 N-1개가 최대값이 됨.
            if(N*(N+1)/2 > S) break;
            ++N;
        }
        System.out.println(N-1);
    }
}
