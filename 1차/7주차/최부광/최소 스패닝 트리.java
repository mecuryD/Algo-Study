import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [백준1197] 최소 스패닝 트리
public class Main {

    static int V; // 정점 개수
    static int E; // 간선 개수
    static ArrayList<Vertex>[] graph; // 그래프

    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());

        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            stk = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(stk.nextToken());
            int B = Integer.parseInt(stk.nextToken());
            int W = Integer.parseInt(stk.nextToken());

            // 양방향 그래프 연결
            graph[A].add(new Vertex(B, W));
            graph[B].add(new Vertex(A, W));
        }

        // 최소 스패닝 트리를 찾는다
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return (o1.w - o2.w);
            }
        });
        boolean[] visited = new boolean[V+1];

        int sum = 0;  // 최소 스패닝 트리의 가중치
        int left = V; // 남은 정점의 개수
        pq.offer(new Vertex(1, 0)); // 1번 정점부터 탐색 시작

        while(!pq.isEmpty()){
            // 모든 정점이 연결되었으면 반복문 종료
            if(left == 0) break;

            // 가중치가 가장 작은 정점 하나를 뽑는다
            Vertex cur = pq.poll();

            // 아직 방문하지 않은 정점인 경우에만 인접 정점을 탐색한다
            if(visited[cur.n]) continue;
            visited[cur.n] = true;
            sum += cur.w;
            left--;

            // 인접 정점 탐색
            for(Vertex v : graph[cur.n]){
                // 아직 방문하지 않은 정점만 큐에 넣는다
                if(!visited[v.n]){
                    pq.offer(v);
                }
            }
        }

        // 결과 출력
        System.out.println(sum);
    }

    static class Vertex {
        int n; // 연결된 정점
        int w; // 간선 가중치

        public Vertex(int n, int w){
            this.n = n;
            this.w = w;
        }
    }
}
