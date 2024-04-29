import java.util.*;
// [프로그래머스] 합승 택시 요금
// 플로이드 워셜로 풀어도 되지만 다익스트라를 여러 번 돌리면 훨씬 빠르다고 한다
// 플로이드 워셜 알고리즘을 어정쩡하게 알고 있어서 삽질을 했던 것 같다 ㅠㅠ
class Solution {
    
    static int MAX = 99999999;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 그래프 초기화
        int[][] graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            Arrays.fill(graph[i], MAX);
            graph[i][i] = 0;
        }
        
        // 그래프 간선 연결
        for(int[] edge : fares){
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        
        // 플로이드 워셜 알고리즘
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        
        // 최소 비용 탐색
        int cost = graph[s][a] + graph[s][b]; // 합승하지 않는 경우

        for(int i=1; i<=n; i++){ // 합승 하고 지점 i에서 분기하는 경우
            cost = Math.min(cost, graph[s][i] + graph[i][a] + graph[i][b]);       
        }
        
        return cost;
    }
}
