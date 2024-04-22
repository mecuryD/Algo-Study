import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] graph = new int[N][N];
        int[] dist = new int[N];
        boolean[] V = new boolean[N];
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        
        // 간선 그래프 -> 가중치 행렬 변환
        for(int i = 0; i < road.length; i++){
            int first = road[i][0] - 1;
            int second = road[i][1] - 1;
            int weight = road[i][2];
            // 이전에 있는 값이 더 작으면 큰 값은 무시
            if(graph[first][second] > 0 && graph[first][second] < weight)
                continue;
            graph[first][second] = weight;
            graph[second][first] = weight;
        }
        for(int i = 1; i < N; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        
        // 초기 위치 집어넣고 다익스트라 돌리기
        PQ.add(new int[] {0, 0});
        dist[0] = 0;
        while(!PQ.isEmpty()){
            // 현재 위치 빼기
            int[] cur = PQ.poll();
            V[cur[0]] = true;
            
            // 최소 거리 갱신 : 연결돼있고 최소거리인 곳
            for(int i = 0; i < N; i++){
                int curDist = cur[1] + graph[cur[0]][i];
                if(graph[cur[0]][i] > 0 && curDist < dist[i]){
                    dist[i] = curDist;
                    PQ.add(new int[] {i, curDist});
                }
            }
        }
        
        for(int i = 0; i < N; i++){
            if(dist[i] <= K){
                answer++;
            }
        }
        
        return answer;
    }
}
