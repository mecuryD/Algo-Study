import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] t1, t2, win, lose, draw;
    static boolean avail;

    public static void main(String[] args)throws Exception {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();

        int cnt= 0;
        t1= new int[15];
        t2= new int[15];

        //t1[]= {0 0 0 0 0 1 1 1 1 2 2 2 3 3 4};
        //t2[]= {1 2 3 4 5 2 3 4 5 3 4 5 4 5 5};
        for(int i=0; i<5; i++){
            for(int j= i+1; j<6; j++){
                t1[cnt] =i;
                t2[cnt++]= j;
            }
        }

        for(int i=0; i<4; i++){
            String str= br.readLine();
            StringTokenizer st= new StringTokenizer(str);
            win= new int[6];
            lose= new int[6];
            draw= new int[6];
            avail= false;
            int w=0, l=0, d=0;
            for(int j=0; j<6; j++){
                w+= win[j]= Integer.parseInt(st.nextToken());
                d+= draw[j]= Integer.parseInt(st.nextToken());
                l+= lose[j]= Integer.parseInt(st.nextToken());
            }

            //다 더해서 30이란거.. 굳이 필요할까?
            if(w+d+l != 30) avail= false;
            else dfs(0);

            if(avail) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.print(sb);

    }


    private static void dfs(int idx) {

        if(idx==15){
            avail= true;
            return;
        }

        int a= t1[idx];
        int b= t2[idx];

        //a가 이기는 경우
        if(win[a]>0 && lose[b]>0) {
            win[a]--;
            lose[b]--;
            dfs(idx+1);
            win[a]++;
            lose[b]++;
        }
        //a와 b가 비기는 경우
        if(draw[a]>0 && draw[b]>0) {
            draw[a]--;
            draw[b]--;
            dfs(idx+1);
            draw[a]++;
            draw[b]++;
        }
        //a가 지는 경우
        if(lose[a]>0 && win[b]>0) {
            lose[a]--;
            win[b]--;
            dfs(idx+1);
            lose[a]++;
            win[b]++;
        }
    }


}