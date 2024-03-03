import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] p; //부모의 인덱스 저장

    public static void main(String[] args)throws Exception {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n= Integer.parseInt(br.readLine());
        int m= Integer.parseInt(br.readLine());

        p= new int[n];

        for(int i=0; i<n; i++) p[i]= i; //모든 원소는 자기만 원소로 갖는 단위
        //서로소 집합이 됨(자신이 곧 대표자인 루트임)

        int[][] graph= new int[n][n];

        for(int i=0; i<n; i++){
            st= new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++) {

                graph[i][j]= Integer.parseInt(st.nextToken());

                if(graph[i][j] == 1) union(i,j);

            }
        }

        st= new StringTokenizer(br.readLine());
        String ans= "YES";

        int val= find(Integer.parseInt(st.nextToken())-1); //0

        for(int i=1; i<m; i++){//모든 경로지가 같은 루트를 공유하는지 파악함
            if(val != find(Integer.parseInt(st.nextToken())-1)){
                ans= "NO";
                break;
            }
        }

        System.out.print(ans);
    }

    static int find(int a){

        if(p[a] == a) return a; //나의 부모가 나라면
        return find(p[a]); //나의 부모가 아직 있다면
        
    }

    static void union(int a, int b) {//0,1 1,0 1,2

        a= find(a); //a의 루트 0 0 0
        b= find(b); //b의 루트 1 0 2

        if(a==b) return; //같은 루트를 공유함

        p[b]= a; //b를 a가 속한 집합 아래로 넣어줌

    }


}
