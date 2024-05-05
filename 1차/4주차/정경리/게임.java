package BOJ;

import java.io.*;
import java.util.*;

public class 게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        //53 47
        long X = Integer.parseInt(st.nextToken());//총 횟수
        long Y = Integer.parseInt(st.nextToken());//이긴 횟수
        long Z = (long) Math.floor((double)Y*100/X);
        long ZZ = Z+1;
//        long ZZ = (long)Math.ceil(Y*100/X);//목표치(Z+1)

        //Z가 절대 변하지 않는 경우
        if(ZZ>=99){
            System.out.println(-1);
            return;
        }

////        double a1 = (ZZ*X);
////        dobule a2 = (long)Y*100;
//        double tmp = (ZZ*X-Y*100)/(100-ZZ);
        double a1 = (ZZ*X);
        double a2 = (long)Y*100;
        long tmp = (long)((a1-a2)/(100-ZZ));
        System.out.println(tmp);
        System.out.println(Math.ceil(tmp));
//        System.out.printf("%.5f\n", tmp);
//        System.out.printf("%.5f\n", Math.ceil(tmp));
        long a = (long)Math.ceil((ZZ*X-Y*100)/(100-ZZ));
//        double b = (100*Y-ZZ*X)/(ZZ-100);

        System.out.println(Y/X);//0.8867925
        System.out.println(ZZ);//88
        System.out.println(ZZ*X);
        System.out.println((ZZ*X-100*Y));
        System.out.println((100-ZZ));
        System.out.println(a);
    }
}
