import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// [백준3190] 뱀
public class Main {

  static int N; // 보드 크기
  static int K; // 사과 개수
  static int L; // 뱀의 방향 변환 횟수
  static int result; // 게임이 끝나는 시간
  static int[] dirR = new int[]{-1,0,1,0}; // 행 방향 벡터
  static int[] dirC = new int[]{0,1,0,-1}; // 열 방향 벡터
  static int[][] board; // 사과 위치
  static Map<Integer, Character> directions; // 뱀의 방향 변환 정보

  public static void main(String[] args) throws IOException  {
    // 값 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    board = new int[N+1][N+1];
    board[1][1] = 2; // 뱀의 맨 위, 맨 좌측에서 시작한다

    for(int i=0; i<K; i++){
      StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
      int r = Integer.parseInt(stk.nextToken());
      int c = Integer.parseInt(stk.nextToken());
      board[r][c] = 1;
    }

    L = Integer.parseInt(br.readLine());
    directions = new HashMap<Integer, Character>();

    for(int i=0; i<L; i++){
      StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
      int time = Integer.parseInt(stk.nextToken());
      char direction = stk.nextToken().charAt(0);
      directions.put(time, direction);
    }

    // Dummy 게임 플레이
    result = 0;
    playDummy();

    // 결과 출력
    System.out.println(result);
  }

  // Dummy 게임을 플레이한다
  public static void playDummy(){
    // 뱀은 오른쪽을 보고 움직이기 시작한다
    int dir = 1;
    ArrayDeque<int[]> snake = new ArrayDeque<>();
    snake.offer(new int[]{1, 1});

    // 뱀이 움직인다
    while(true){
      // 시간 증가
      result++;

      // 뱀의 머리를 옮겼을 때 충돌하는지 체크, 충돌 시 게임 종료
      int[] head = snake.peek();
      int[] next = new int[]{head[0] + dirR[dir], head[1] + dirC[dir]};
      if(next[0] < 1 || next[0] > N  || next[1] < 1 || next[1] > N) return;
      if(board[next[0]][next[1]] == 2) return;

      // 옮긴 자리에 사과가 있는지 체크
      if(board[next[0]][next[1]] != 1) {
        // 사과 미존재
        int[] tail = snake.pollLast();
        board[tail[0]][tail[1]] = 0;
      }

      board[next[0]][next[1]] = 2;
      snake.offerFirst(next);

      // 현재 시간에서 방향 변환이 필요한지 체크, 방향전환
      if(directions.containsKey(result)){
        if(directions.get(result)=='L') dir = (dir==0) ? 3 : (dir - 1);
        else dir = (dir + 1) % 4;
      }
    }
  }
}
