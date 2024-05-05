import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] map;


    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        map= new int[9][9];

        for(int i=0; i<9; i++){
            String str= br.readLine();
            for(int j=0; j<9; j++){

                map[i][j] = str.charAt(j) - '0';

            }
        }

        dfs(0);

    }

    static void dfs(int idx){
        if(idx == 81) {

            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(map[i][j]);

                }
                System.out.println();
            }

            System.exit(0);
        }

        int x = idx/9;
        int y = idx%9;

        if(map[x][y] != 0) {
            dfs(idx + 1);
            return;
        }


        for(int i=1; i<10; i++){
            map[x][y] = i;

            if(chk(x,y))
                dfs(idx+1);

            map[x][y] = 0;

        }
    }

    static boolean chk(int x, int y) {

        boolean[] visited= new boolean[10];

        //넓이 고려
        for(int i=0; i<9; i++){
            if(map[x][i] == 0) continue;

            if(visited[map[x][i]]){
                return false;
            }
            visited[map[x][i]] = true;
        }

        for(int i=0; i<10; i++){
            visited[i] = false;
        }

        //높이고려
        for(int i=0; i<9; i++){
            if(map[i][y] == 0) continue;

            if(visited[map[i][y]]){
                return false;
            }
            visited[map[i][y]] = true;
        }

        for(int i=0; i<10; i++){
            visited[i] = false;
        }

        //3*3 고려 0,1,2-> 0  3,4,5->3 6,7,8->6
        for(int i=0; i<9; i++){
            if(map[i/3+x/3*3][i%3+y/3*3] == 0) continue;

            if(visited[map[i/3+x/3*3][i%3+y/3*3]]){
                return false;
            }
            visited[map[i/3+x/3*3][i%3+y/3*3]] = true;
        }


        return true;
    }

}
