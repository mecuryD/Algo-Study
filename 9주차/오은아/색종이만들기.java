import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int whiteAns, blueAns;
    public static void main(String[] args)throws Exception {

        // n/2로 계속해서 나뉘어지는 거니까 dfs로 가자

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= null;
        int n= Integer.parseInt(br.readLine());

        map= new int[n][n];

        for(int i=0; i<n; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
                map[i][j]= Integer.parseInt(st.nextToken());
        }

        dfs(0,0,n);

        System.out.print(whiteAns+"\n"+blueAns);


    }

    private static void dfs(int i, int j,int n) {

        boolean blue_flag= false;
        boolean white_flag= false;

        for(int x= i; x<i+n; x++){
            for(int y= j; y<j+n; y++){
                if(map[x][y]==1) blue_flag= true;
                else white_flag= true;
            }
        }

        if(blue_flag && !white_flag){

            blueAns++;
            return;
        }

        if(!blue_flag && white_flag){

            whiteAns++;
            return;
        }


        dfs(i, j,n/2);
        dfs(i+n/2, j, n/2);
        dfs(i, j+n/2, n/2);
        dfs(i+n/2, j+n/2, n/2);


    }


}
