import java.util.*;

class Solution {
        
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{1, -1, 0, 0};
    
    public int[] solution(int m, int n, int[][] picture) {
        // 변수 초기화
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        // 그림 전체 영역을 탐색하면서 BFS
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // 만약 이미 탐색된 영역이라면 continue;
                if(picture[i][j]==0) visited[i][j]=true;
                if(visited[i][j]) continue;
                
                // 주변에 같은 영역이 존재하는지 BFS 탐색
                numberOfArea++;
                int num = picture[i][j];
                
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{i,j});
                visited[i][j] = true;
                int area = 1;
                
                while(!queue.isEmpty()){
                    int[] cur = queue.poll();
                    
                    for(int k=0; k<4; k++){
                        int nr = cur[0] + dr[k];
                        int nc = cur[1] + dc[k];
                        if(nr>=0 && nc>=0 && nr<m && nc<n){
                            if(!visited[nr][nc] && picture[nr][nc]==num){
                                queue.offer(new int[]{nr, nc});
                                visited[nr][nc] = true;
                                area++;
                            }
                        }
                    }
                }
                
                maxSizeOfOneArea = (maxSizeOfOneArea < area) ? area : maxSizeOfOneArea;
            }
        }

        
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }
}
