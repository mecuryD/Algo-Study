package LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숨바꼭질2222 {

    public static void main(String[] args) throws IOException {
    
        int[] visited = new int[100001];
        int N,K;
        Queue<int[]> q = new ArrayDeque<>();
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        //1. 수빈이가 동생보다 뒤에 있는 경우-> -1씩 앞으로 가는 방법 하나밖에 없음.
        if(N>=K){
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

        int dx[];
        int tmp[], tx, nx, ntime, cnt=0;//ntime=현재까지 걸린 시간(횟수), cnt=최소시간으로 가는 방법의 수
        //int time=Integer.MAX_VALUE;//최소시간 저장용
        q.offer(new int[] {N,0});

        while(!q.isEmpty()){

            tmp = q.poll();
            tx = tmp[0];//이전 위치
            dx = new int[] {tx+1, tx-1,tx*2};//이번에 갈 위치
            ntime = tmp[1]+1;//tmp[1]: 이전 횟수, ntime: 이번 횟수
            
//            System.out.println(tx);
//            System.out.println();
            for(int i=0; i<3; ++i) {
                nx=dx[i];//이번에 갈 위치
                if(i==2 && tx>K) continue;
                if(nx<0 || nx>100000 || (visited[nx]!=0 && visited[nx]<ntime)) continue;
                if(nx==K){
//                	System.out.println(tx+" "+nx+" "+ntime+" "+visited[nx]);
                    if(cnt==0){//처음으로 도착한 경우
                    	visited[K]=ntime;
                    }
                    if(ntime==visited[K])++cnt;
                    continue;
                }
                	//아무데도 안걸렸으면 큐에 다시 넣기
//                if(visited[nx]==0 || ntime<=visited[nx]) {
                if(visited[nx]==0 || ntime==visited[nx]) {
                	q.offer(new int[] {nx, ntime});
                    visited[nx]=ntime;
                }
            }
       
        }
        System.out.println(visited[K]);
        System.out.println(cnt);
    }
}