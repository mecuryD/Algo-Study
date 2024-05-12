import java.util.*;

class Solution {
    
    static int n;
    static String[] ans;
    static boolean found= false;

    public String[] solution(String[][] tickets) {

        //1. ICN에서 출발, 알파벳 순서가 빠른 순으로 and 모든 항공권을 다 쓴 경로 찾기
        //2. 항공권 모두 3글자
        //3. !!모든 도시를 방문할 수 있는 범위에서, 알파벳 순서가 빠른 순!!
        
        //알고리즘 찾기: 구현/BFS/DFS/브루트포스/그래프/위상정렬/MST/다이나믹/투포인터/이분탐색
        //DFS인데 어떻게 알파벳 순으로 정렬해야하지
        //전부 써야하는거니까 vis를 써야한다
        //시작점하나에 도착지가 여러개 있을 수 있다
        //Map을 버리자
        
        n= tickets.length;
        
        ans= new String[n+1];
        
        String[] list= new String[n+1];
        
        boolean vis[]= new boolean[n];
        
        Arrays.sort(tickets, (t1, t2) -> {
            if(t1[0].equals(t2[0])) {
                return t1[1].compareTo(t2[1]); // 출발지가 같으면 도착지를 비교해서 정렬
                } else {
                return t1[0].compareTo(t2[0]); // 출발지가 다르면 출발지를 비교해서 정렬
            }
        });
        list[0] = "ICN";

        dfs(0, "ICN", list, vis, tickets);
        
        return ans;
        
    }
    
    static void dfs(int L, String cur, String[] list, boolean[] vis, String[][] tickets){//JFK
        
         if(found) return; // 이미 정답을 찾았다면 더 이상 탐색하지 않음
        
        
        if(L == n){
            for(int i=0; i<=n; i++)
                ans[i]= list[i];
            
            found = true; // 정답을 찾았다고 표시
           
            return;
        }
        
        for(int i=0; i<n; i++){
            if(!vis[i] && tickets[i][0].equals(cur)){
                
                vis[i]= true;
                list[L+1]= tickets[i][1];
                dfs(L+1, tickets[i][1], list, vis, tickets);
                vis[i]= false;
                
            }
        }
        
        
    }
}
