import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이진수표현 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T= Integer.parseInt(bf.readLine());
        for (int t = 1; t <= T; ++t) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            //M의 마지막 N비트가 모두 1인지 판별

            //2의 N -1 : 1111..(N비트)
            int check = (1<<N) - 1;
            if((M & check) == check) sb.append("ON\n");
            else sb.append("OFF\n");
        }
        System.out.println(sb);
    }
}
