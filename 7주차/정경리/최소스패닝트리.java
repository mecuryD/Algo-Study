import java.io.*;
import java.util.*;

public class 최소스패닝트리 {

    static class edge implements Comparable<edge>{
        int v1;//정점 번호
        int v2;//정점 번호
        int cost;//비용

        public edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
        @Override
        public int compareTo(edge o) {// 오름차순 정렬
            if (this.cost < o.cost) return -1;
            else return 1;
        }
    }

    // 1. 임의의 정점을 하나 신장트리에 넣는다.
    // 2. 이어진 간선 중 값이 가장 작은 것을 신장트리에 넣는다.
    // 3. 출발점과 도착점이 모두 트리 안에 있다면 넣지 않는다.
    // 4. 간선을 v-1개 뽑을 때까지 2,3 반복

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int V,E;
        List<edge> edgeList = new ArrayList<>();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int A,B,C;
        boolean[] visited = new boolean[V+1];
        //우선순위 큐 -> 간선 크기로 정렬하기
        PriorityQueue<edge> pq = new PriorityQueue<>();
        for(int i=0; i<E; ++i){
            st = new StringTokenizer(bf.readLine());
            //A,B,C->A번 정점과 B번 정점이 가중치 C인 간선으로 연결됨.
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            pq.offer(new edge(A,B,C));

        }

        edge tmp;
        int V1, V2;
        while(edgeList.size() < V-1){
            tmp = pq.poll();
            if(tmp==null)break;
            V1 = tmp.v1;
            V2 = tmp.v2;
            if(visited[V1] && visited[V2]) continue;//연결된 간선 모두가 방문된 적 있다면 continue
            edgeList.add(tmp);
            visited[V1] = true;
            visited[V2] = true;
        }

        int sum=0;
        for(edge e: edgeList){
            sum+=e.cost;
        }
        System.out.println(sum);
    }
}
