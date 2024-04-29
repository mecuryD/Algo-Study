import java.io.*;
import java.util.*;

public class 새로운불면증치료법 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T= Integer.parseInt(bf.readLine());

        for (int t = 1; t <= T; ++t) {
            sb.append("#").append(t).append(" ");
            int N = Integer.parseInt(bf.readLine());

            //비트 연산자 사용하여 방문체크

            // 아무리 운이 나빠도 100번까지만 돌리면 모두 찾을 수 있으므로...
            // int 자료형으로 처리 가능함...
            // 100번 정도 곱하면 모든 숫자가 등장하게 된다

            int cnt = 0;
            int n = N;
            int tmp = 0;
            int check = 0;
            while (true) {
                n = N * ++cnt;
                //System.out.println(n);

                while(n>0){
                    tmp = n % 10;
                    //System.out.println(tmp);
                    check |= (1<<tmp);
                    n /= 10;//마지막 자리 하나씩 없애면서 확인
                    //n = n>>1;
                }

                //System.out.println("--------------");
                if(check==1023) break;
            }

            sb.append(N*cnt).append("\n");
        }

        System.out.println(sb);
    }
}
