import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args)throws Exception {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int n= Integer.parseInt(st.nextToken());
        int m= Integer.parseInt(st.nextToken());
        int b= Integer.parseInt(st.nextToken());

        int[][] map= new int[n][m];

        int minH= 256;

        for(int i=0; i<n; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(minH > map[i][j]) minH= map[i][j];
            }
        }

        int h= minH;
        int minTime= Integer.MAX_VALUE;

        while(true) {

            int tb =b; //99
            int totalTime= 0;
            int hdiff; //0

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){

                    hdiff= h- map[i][j]; //0-1

                    if(hdiff < 0){ //최소높이보다 블록이 더 크다면
                        totalTime += hdiff* -2; //큰만큼 제거한다
                        tb += -hdiff; //인벤토리에 넣는다
                    }else{ //그렇지 않다면
                        totalTime+= hdiff; //작은만큼 블록을 더 놓는다
                        tb -= hdiff; //인벤토리에서 블록을 꺼냈다
                    }

                }
            }

            if(tb <0) break; //인벤토리가 0보다 작다면 끝

            if(minTime >= totalTime){//이번 h에서 걸린 시간이 더 적다면
                //==이 붙는 이유는 답이 여러 개면 땅 높이가 더 높을 것을 선택해야하기 떄문
                minTime= totalTime; //minTime을 갱신
                minH= h; //최소h를 갱신
            }

            h++; //다음 높이를 1 높인다.

        }
        System.out.print(minTime+" "+minH);
    }
}