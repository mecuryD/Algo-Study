import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// [백준1976] 여행 가자
public class Main {

  static int N; // 도시의 수
  static int M; // 여행 계획에 속한 도시들의 수
  static int[][] graph; // 도시 양방향 연결 그래프
  static Set<Integer> set; // 여행 계획에 속한 도시 집합

  static int[] parents; // 유니온파인드 부모 배열

  public static void main(String[] args) throws IOException {
    // 값 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    graph = new int[N+1][N+1];
    for(int i=1; i<=N; i++){
      StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
      for(int j=1; j<=N; j++){
        graph[i][j] = Integer.parseInt(stk.nextToken());
      }
    }

    set = new HashSet<Integer>();
    StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
    for(int i=0; i<M; i++){
      set.add(Integer.parseInt(stk.nextToken()));
    }

    // 유니온 파인드로 같은 그룹끼리 묶는다
    set();

    for(int i=1; i<=N; i++){
      for(int j=i; j<=N; j++){
        if(graph[i][j] > 0){
          union(i, j);
        }
      }
    }

    // 여행 경로에 있는 모든 도시가 동일한 그룹에 있으면 YES 출력
    Integer[] plans = set.toArray(new Integer[0]);
    boolean flag = false;
    int root = find(plans[0]);
    for(int plan : plans){
      if(find(plan) != root){
        flag = true;
        break;
      }
    }

    if(flag) System.out.print("NO");
    else System.out.print("YES");

  }

  // 유니온 파인드
  public static void set(){
    parents = new int[N+1];
    for(int i=1; i<=N; i++){
      // 자기 자신을 루트로 초기화한다
      parents[i] = i;
    }
  }

  public static boolean union(int p, int c){
    p = find(p); // p의 부모노드 찾기
    c = find(c); // c의 부모노드 찾기

    // 이미 같은 그래프면 false 반환
    if(p == c) return false;
    // 작은 숫자가 부모 노드가 되도록 한다
    if(p <= c) parents[c] = p;
    else parents[p] = c;
    return true;
  }

  public static int find(int c){
    if(parents[c] == c) return c;
    else return find(parents[c]);
  }
}
