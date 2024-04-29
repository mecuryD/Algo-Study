package boj;
import java.io.*;
import java.util.*;

public class 공주님을구해라2 {

    static class info{
        int x, y, sword, time;

        public info(int x, int y, int sword, int time) {
            this.x = x;
            this.y = y;
            this.sword = sword;
            this.time = time;
        }
    }

    static int N,M,T;
    static int [][]map;
    static boolean [][][]visited;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[2][N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

    }

    public static void bfs() {
        PriorityQueue<info> pq = new PriorityQueue<>(new Comparator<info>() {

            @Override
            public int compare(info o1, info o2) {
                return o1.time-o2.time;
            }
        });

        pq.offer(new info(0,0,0,0));
        visited[0][0][0]=true;

        while(!pq.isEmpty()) {
            info temp = pq.poll();

            if(temp.x==N-1 && temp.y==M-1) {
                if(temp.time<=T) {
                    System.out.println(temp.time);
                }else System.out.println("Fail");
                return;
            }
            if(temp.time>T) break;

            for(int i=0;i<4;i++) {
                int nx = temp.x+dx[i];
                int ny = temp.y+dy[i];

                if(range(nx,ny) && !visited[temp.sword][nx][ny]) {
                    if(temp.sword==0) {
                        if(map[nx][ny]==0) {
                            visited[0][nx][ny]=true;
                            pq.offer(new info(nx,ny,temp.sword,temp.time+1));
                        }
                        else if(map[nx][ny]==2) {
                            visited[1][nx][ny] =true;
                            pq.offer(new info(nx,ny,1,temp.time+1));
                        }
                    }else {
                        visited[1][nx][ny] = true;
                        pq.offer(new info(nx,ny,temp.sword,temp.time+1));
                    }
                }
            }
        }
        System.out.println("Fail");
    }

    public static boolean range(int x, int y) {
        return x>=0 && y>=0 && x<N && y<M;
    }
}
