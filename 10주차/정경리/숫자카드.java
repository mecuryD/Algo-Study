package boj;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class 숫자카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        int[] cards = new int[N];

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; ++i) cards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cards);

        int M = Integer.parseInt(bf.readLine());
        int tmp;
        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<M; ++i){
            tmp = Integer.parseInt(st.nextToken());
            if(Arrays.binarySearch(cards,tmp) < 0) sb.append("0 ");
            else sb.append("1 ");
        }

        System.out.println(sb);
//        for(int i=0; i<M; ++i) {
//            tmp = Integer.parseInt(st.nextToken());
//            System.out.print(binarySearch(cards, N, tmp)+" ");
//        }

    }
}
//    static int binarySearch(int[] cards, int N, int find){
//        int start = 0;
//        int end = N-1;
//        int mid = 0;
//
//        while(start<=end){
//            mid = (start+end)/2;
//            if(cards[mid] == find) return 1;
//            if(cards[mid] < find ) start = mid+1;//중간값이 찾는 수보다 작으면
//            else end = mid-1;
//        }
//
//
//        return 0;
//    }


