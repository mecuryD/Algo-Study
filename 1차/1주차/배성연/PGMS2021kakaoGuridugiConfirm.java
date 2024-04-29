package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class PGMS2021kakaoGuridugiConfirm {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[][] places = new String[5][5];
		StringTokenizer stk;
		for(int i = 0; i < 5; i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0; j < 5; j++) {
				places[i][j] = stk.nextToken();		
			}
		}
		
		int[] answer = {1, 1, 1, 1, 1};
		
//POOOP OXXOX OPXPX OOXOX POXXP
//POOPX OXPXP PXXXO OXXXO OOOPP
//PXOPX OXOXP OXPOX OXXOP PXPOX
//OOOXX XOOOX OOOXX OXOOX OOOOO
//PXPXP XPXPX PXPXP XPXPX PXPXP
		
//OOPOO OPOOO OOOOO OOOOO OOOOO
		
//POOOO XPOOO OOOOO OOOOO OOOOO
//OOOOO OOOOO OOOOO OOOOO OOOOO
//PXOPX OXOXP OXPOX OXXOP PXPOX
//OOOXX XOOOX OOOXX OXOOX OOOOO
//PXPXP XPXPX PXPXP XPXPX PXPXP
		
		int[][] map = new int[5][5];
        //대기실 5개 돌기
        for(int waitRoom = 0; waitRoom < 5; waitRoom++){
            //5x5 map에 각 places 집어넣기 + P 위치배열 만들기
		    LinkedList<int[]> P = new LinkedList<int[]>();   //P위치배열
            for(int i = 0; i < 5; i++){
                String tempS = places[waitRoom][i];
                for(int j = 0; j < 5; j++){
                    char tempC = tempS.charAt(j);
                    switch(tempC){
                        case 'P': map[i][j] = 1;    //인간이면 1
                            P.add(new int[] {i, j});   //P위치배열 추가
                            break;
                        case 'O': map[i][j] = 0;    //비었으면 0
                            break;
                        case 'X': map[i][j] = 2;    //파티션이면 2
                    }
                }
            }
            //확인용
            // for(int i=0; i<5; i++){
            //     for(int j = 0; j<5; j++){
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            
            //P배열 돌면서 각 위치별로 map의 3조건 탐방
            //맨해튼 거리가 2 이하인 자리 : 1칸4방, 1칸대각선, 2칸4방
            int sizeOfP = P.size();
            int[] dx1 = {-1, 1, 0, 0};
            int[] dy1 = {0, 0, -1, 1};
            int[] dx2 = {-1, 1, -1, 1}; //0상좌, 1하좌, 2상우, 3하우
            int[] dy2 = {-1, -1, 1, 1}; //13, 23, 14, 24
            int[] dx3 = {-2, 2, 0, 0};
            int[] dy3 = {0, 0, -2, 2};
            confirm : for(int p = 0; p < sizeOfP; p++){
                int[] cur = P.poll();
                //1칸4방:무조건x
                for(int i = 0; i < 4; i++){
                    int nx = cur[0] + dx1[i];
                    int ny = cur[1] + dy1[i];
                    if((nx < 0)||(ny < 0)||(nx > 4)||(ny > 4)) continue;
                    if(map[nx][ny] == 1){
                        answer[waitRoom] = 0;
                        break confirm;
                    }
                }
                //1칸대각선:사이2개 다 파티션아니면x
                //0상좌, 1하좌, 2상우, 3하우
                //02, 12, 03, 13
                for(int i = 0; i < 4; i++){
                    int nx = cur[0] + dx2[i];
                    int ny = cur[1] + dy2[i];
                    if((nx < 0)||(ny < 0)||(nx > 4)||(ny > 4)) continue;
                    int flag = 0;
                    if(map[nx][ny] == 1){
                        switch(i){
                            case 0:
                                if(map[cur[0]+dx1[0]][cur[1]+dy1[0]] != 2
                                  || map[cur[0]+dx1[2]][cur[1]+dy1[2]] != 2){
                                    flag = 1;
                                }
                                break;
                            case 1:
                                if(map[cur[0]+dx1[1]][cur[1]+dy1[1]] != 2
                                  || map[cur[0]+dx1[2]][cur[1]+dy1[2]] != 2){
                                    flag = 1;
                                }
                                break;
                            case 2:
                                if(map[cur[0]+dx1[0]][cur[1]+dy1[0]] != 2
                                  || map[cur[0]+dx1[3]][cur[1]+dy1[3]] != 2){
                                    flag = 1;
                                }
                                break;
                            case 3:
                                if(map[cur[0]+dx1[1]][cur[1]+dy1[1]] != 2
                                  || map[cur[0]+dx1[3]][cur[1]+dy1[3]] != 2){
                                    flag = 1;
                                }
                                break;
                        }
                        if(flag == 1){
                            answer[waitRoom] = 0;
                            break confirm;
                        }
                    }
                }
                //2칸4방:사이1개 파티션아니면x
                for(int i = 0; i < 4; i++){
                    int nx = cur[0] + dx3[i];
                    int ny = cur[1] + dy3[i];
                    if((nx < 0)||(ny < 0)||(nx > 4)||(ny > 4)) continue;
                    if(map[nx][ny] == 1){
                        if(map[cur[0]+dx1[i]][cur[1]+dy1[i]] != 2){
                            answer[waitRoom] = 0;
                            break confirm;
                        }
                    }
                }
            }
        }
		System.out.println(Arrays.toString(answer));
	}
	
}
