package BOJ;

import java.io.*;
import java.util.*;

public class 기타레슨 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];


        int left = 0;
        int right = 0;

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
            left = Math.max(left, arr[i]);
        }
        int sum=0, count=0;
        while (left <= right) {
            int mid = (left + right) / 2;

            sum = 0;
            count = 0;
            for (int i = 0; i < N; i++) {
                if (sum + arr[i] > mid) {
                    sum = 0;
                    count++;
                }
                sum += arr[i];
            }

            if(sum != 0) count++;

            if(count > M){left = mid + 1;}
            else{right = mid - 1;}
        }
        System.out.println(left);
    }

}
