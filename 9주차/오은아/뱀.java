import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args)throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 보드의 크기

        int[][] map = new int[n][n]; // 맵 초기화
        int appleCnt = Integer.parseInt(br.readLine()); // 사과의 개수

        for(int i = 0; i < appleCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1] = 1; // 사과 위치 표시
        }

        int rotation = Integer.parseInt(br.readLine()); // 회전 횟수
        ArrayList<int[]> list = new ArrayList<>(); // 회전 정보 저장
        for(int i = 0; i < rotation; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int rotat = st.nextToken().equals("D") ? 3 : 2;
            list.add(new int[]{time, rotat});
        }

        map[0][0] = 2; // 뱀의 초기 위치 표시
        ArrayList<int[]> snake = new ArrayList<>(); // 뱀의 위치를 저장할 리스트
        snake.add(new int[]{0, 0}); // 초기 뱀의 위치 추가

        int t = -1;
        int d = 3; // 오른쪽을 바라보는 초기 방향

        while(true) {
            t++; // 시간 증가

            //위로 가다가 오른쪽/왼쪽 -> 오른쪽/왼쪽
            //아래로 가다가 오른쪽/왼쪽 -> 왼쪽/오른쪽
            //왼쪽으로 가다가 오른쪽/왼쪽 -> 위쪽/아래족
            //오른쪽으로 가다가 오른쪽/왼쪽 -> 아래쪽/위쪽

            if(!list.isEmpty()&& list.get(0)[0] == t) {
                int tmpD= list.get(0)[1];
                if(tmpD==3){ //오른쪽으로 가야할 때
                    if(d==0)//위쪽이면
                        d= 3;
                    else if (d==1)
                        d= 2;
                    else if(d==2)
                        d= 0;
                    else
                        d= 1;
                }else{ //왼쪽으로 가야할 때
                    if(d==0)//위쪽이면
                        d= 2;
                    else if (d==1)
                        d= 3;
                    else if(d==2)
                        d= 1;
                    else
                        d= 0;
                }
                list.remove(0);

            }

            int headX = snake.get(snake.size() - 1)[0];
            int headY = snake.get(snake.size() - 1)[1];
            int nx = headX, ny = headY;

            // 방향에 따른 다음 위치 계산
            if(d == 0) nx -= 1;
            else if(d == 1) nx += 1;
            else if(d == 2) ny -= 1;
            else ny += 1;

            // 벽이나 자기 자신과 부딪히는 경우 게임 종료
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 2) {
                break;
            }

            if(map[nx][ny] != 1) { // 사과를 먹지 않았다면 꼬리 이동
                int[] removeTail = snake.remove(0); // 꼬리 위치 제거
                map[removeTail[0]][removeTail[1]] = 0; // 맵에서 꼬리 위치 초기화
            }

            map[nx][ny] = 2; // 머리 위치 추가
            snake.add(new int[]{nx, ny}); // 뱀 리스트에 머리 위치 추가
        }

        System.out.print(t + 1); // 최종 시간 출력
    }
}
