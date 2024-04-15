import java.util.*;

// [프로그래머스] 네트워크
// 그래프가 연결되면 하나의 네트워크로 본다
// LEVEL3지만 DFS/BFS 알고리즘을 이용하면 무난하게 해결되는 문제
class Solution {
    
    static int count = 0;
    
    public int solution(int n, int[][] computers) {
        // DFS
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i, n, visited, computers);
                count++;
            }
        }
        // BFS
        // bfs(n, computers);
        
        return count;
    }
    
    public void dfs(int c, int n, boolean[] visited, int[][] computers){
        visited[c] = true;
        
        for(int i=0; i<n; i++){
            if(!visited[i] && computers[c][i]==1){
                dfs(i, n, visited, computers);
            }
        }
    }

    public int bfs(int n, int[][] computers){
        int network = 0;
        boolean[] visited = new boolean[n];

        // 컴퓨터 노드 각각을 순회하면서 네트워크 개수를 체크한다
        for(int i=0; i<n; i++){

            if(!visited[i]){
                Queue<Integer> queue = new ArrayDeque<Integer>();
                queue.offer(i);
                visited[i] = true;
                while(!queue.isEmpty()){
                    // 컴퓨터 한 대를 꺼낸다
                    int cur = queue.poll();
                    
                    // 아직 방문하지 않은 인접 노드를 확인하고 큐에 입력한다
                    for(int j=0; j<n; j++){
                        if(!visited[j] && computers[cur][j]==1){
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }

                network++;
            }
        }
        // 값 리턴
        return network;
    }
}
