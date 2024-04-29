import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// [백준16234] 인구 이동
public class Main {

    static int N;
    static int L;
    static int R;
    static int day;

    static int[][] A; // 각 나라의 인구수 배열
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());
        R = Integer.parseInt(stk.nextToken());

        A = new int[N][N];
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                A[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 인구 이동
        day = 0;
        while(true){
            if(!checkLine()) break;
            day++;
        }

        // 결과 출력
        System.out.print(day);
    }

    // 국경선 체크
    public static boolean checkLine(){
        boolean flag = false;
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]){
                    // BFS
                    int sum = 0;
                    List<int[]> list = new ArrayList<>();

                    Queue<int[]> queue = new ArrayDeque<int[]>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while(!queue.isEmpty()){
                        // 위치 하나를 꺼낸다
                        int[] cur = queue.poll();
                        list.add(cur);
                        sum += A[cur[0]][cur[1]];

                        // 4방 탐색
                        for(int k=0; k<4; k++){
                            int nr = cur[0] + dr[k];
                            int nc = cur[1] + dc[k];
                            if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
                            if(visited[nr][nc]) continue;

                            int diff = Math.abs(A[cur[0]][cur[1]] - A[nr][nc]);
                            if(diff>=L && diff<=R){
                                queue.offer(new int[]{nr, nc});
                                visited[nr][nc] = true;
                            }
                        }
                    }

                    // 만약 연합이 구성되면 값 업데이트
                    if(list.size() > 1){
                        flag = true;
                        int val = sum / list.size();
                        for(int[] pos : list){
                           A[pos[0]][pos[1]] = val;
                        }
                    }
                }
            }
        }
        return flag;
    }
}
