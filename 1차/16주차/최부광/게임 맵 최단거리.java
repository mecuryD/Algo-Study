import java.util.*;

class Solution {
    
    static int N;
    static int M;
    static int result;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{1, -1, 0, 0};
    
    public int solution(int[][] maps) {
        result = 99999;
        N = maps.length;
        M = maps[0].length;
        
        // DFS
        // boolean[][] visited = new boolean[N][M];
        // visited[0][0] = true;
        // dfs(0, 0, 1, maps, visited);
        
        // BFS
        bfs(maps);
        
        if(result == 99999) return -1;
        return result;
    }
    
    // DFS
    public void dfs(int r, int c, int count, int[][] maps, boolean[][] visited){
        // 상대 진영에 도착하면 칸의 개수 업데이트
        if(r == (N-1) && c == (M-1)){
            result = (count < result) ? count : result;
            return;
        }
        
        // 칸 이동
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
            if(visited[nr][nc] || maps[nr][nc]==0) continue;
            
            visited[nr][nc] = true;
            dfs(nr, nc, count+1, maps, visited);
            visited[nr][nc] = false;
        }
    }
    
    // BFS
    public void bfs(int[][] maps){        
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            
            for(int i=0; i<4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                
                if(nr<0 || nc<0|| nr>=N || nc>=M) continue;
                if(visited[nr][nc] || maps[nr][nc]==0) continue;
                
                if(nr==(N-1) && nc == (M-1)){
                    result = cur[2] + 1;
                    return;
                }
                
                queue.offer(new int[]{nr, nc, cur[2] + 1});
                visited[nr][nc] = true;
            }
        }
    } 
}
