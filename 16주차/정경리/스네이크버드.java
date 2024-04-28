import java.io.*;
import java.util.*;

public class 스네이크버드 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());//과일의 개수
        int L = Integer.parseInt(st.nextToken());//초기길이
        int[] fruits = new int[N];

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; ++i){
            fruits[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<N; ++i){
            Arrays.sort(fruits);
            if(fruits[i]<=L) ++L;
        }
        System.out.println(L);
    }
}
