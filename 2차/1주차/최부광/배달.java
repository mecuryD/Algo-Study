import java.util.*;

class Solution {
    
    static int MAX_VALUE = 999999;
    static int[] distance;
    static int[][] map;
    
    public int solution(int N, int[][] road, int K) {
        // 인접 행렬 구성
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], MAX_VALUE);
        }

        for (int[] r : road) {
            int a = r[0] - 1;
            int b = r[1] - 1;

            map[a][b] = (r[2] < map[a][b]) ? r[2] : map[a][b];
            map[b][a] = (r[2] < map[b][a]) ? r[2] : map[b][a];
        }

        // 다익스트라 알고리즘
        int[] distance = new int[N];
        Arrays.fill(distance, MAX_VALUE);

        boolean[] visited = new boolean[N];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (o1.w - o2.w);
            }
        });

        pq.offer(new Node(0, 0));
        distance[0] = 0;

        int count = 0;
        while (count < N) {
            // 우선순위 큐에서 노드 한 개를 뽑는다
            Node cur = pq.poll();

            if (visited[cur.n]) continue;
            visited[cur.n] = true;
            count++;

            // 방문하지 않은 인접 노드의 거리를 계산한다
            for (int i = 0; i < N; i++) {
                if (i != cur.n && !visited[i]
                    && (map[cur.n][i] + distance[cur.n]) < distance[i]) {
                    distance[i] = map[cur.n][i] + distance[cur.n];
                    pq.offer(new Node(i, distance[i]));
                }
            }
        }

        // 결과 탐색
        int result = 0;
        for (int d : distance) {
            if (d <= K) result++;
        }

        return result;
    }

    static class Node {

        int n;
        int w;

        public Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
