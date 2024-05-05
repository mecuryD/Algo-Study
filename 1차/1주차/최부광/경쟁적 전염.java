import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// [백준18405] 경쟁적 전염
public class Main{

    static int N;
    static int K;
    static int S;
    static int X;
    static int Y;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};



    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        map = new int[N][N];
        List<Virus> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
                if(map[i][j] > 0) list.add(new Virus(i,j, map[i][j])); // 바이러스 시작 위치
            }
        }

        stk = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(stk.nextToken());
        X = Integer.parseInt(stk.nextToken());
        Y = Integer.parseInt(stk.nextToken());

        // 바이러스가 확산한다
        list.sort((o1, o2) -> (o1.t - o2.t)); // 바이러스 번호 오름차순 정렬
        spreadVirus(list);

        // 결과 출력
        System.out.println(map[X-1][Y-1]);
    }

    public static void spreadVirus(List<Virus> list){
        Queue<Virus> queue = new ArrayDeque<>();
        for(Virus virus : list){
            queue.offer(virus);
        }


        int time = 0;
        while(!queue.isEmpty()){
            if(time==S) return;

            int len = queue.size();
            for(int i=0; i<len; i++){
                // 바이러스 하나를 꺼낸다
                Virus cur = queue.poll();

                // 바이러스를 확산시킨다
                for(int j=0; j<4; j++){
                    int nr = cur.r + dr[j];
                    int nc = cur.c + dc[j];
                    if(canSpread(nr, nc)){ // 확산할 수 있는 위치면
                        map[nr][nc] = cur.t;
                        queue.offer(new Virus(nr, nc, map[nr][nc]));
                    }
                }
                if(map[X-1][Y-1] > 0){ // 바이러스가 목표 지점에 확산되었다면
                    return;
                }
            }
            time++;
        }
    }

    public static boolean canSpread(int r,  int c){
        if(r<0 || c<0 || r>=N || c>=N || map[r][c]>0) return false;
        return true;
    }

    public static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
           
        }
    }

    static class Virus {
        int r;
        int c;
        int t; // virus type

        public Virus(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }
}
