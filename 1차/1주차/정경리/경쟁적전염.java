package LinkedList;

import java.util.*;
import java.io.*;

public class 경쟁적전염 {

    static void printMap(int[][] map, int N){
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N, K;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
//        System.out.println(N);
//        System.out.println(K);
        //NxN 배열
        int[][] map = new int[N][N];
        //우선 순위가 낮은 숫자로 정렬되는 pq
        //pq에 배열을 넣을 경우, 반드시 Comparator를 써서 정렬 조건을 정해줘야 함.
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int []>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {//오름차순 정렬
//        		
        		//배열의 마지막 원소(바이러스 번호)가 같다면, 두번째 원소를 기준으로 오름차순 정렬한다.
        		if (o1[2]==o2[2]) {
            		//배열의 두번째 원소도 같다면, 첫번째 원소를 기준으로 오름차순 정렬한다.
            		if(o1[1]==o2[1]) {
            			return o1[0]-o2[0];//첫번째 원소 기준
            		}
        			return o1[1]-o2[1];//두번째 원소 기준
        		}
				return o1[2]-o2[2];    		
        	}     	
        });
        int t=0;

	    for(int i=0; i<N; ++i){
	        st = new StringTokenizer(bf.readLine());
	        for(int j=0; j<N; ++j){
	            t = Integer.parseInt(st.nextToken());
	            map[i][j] = t;
	            if(t!=0){//0이 아니면 우선순위 큐에 추가하기
	                pq.offer(new int[] {i,j,t});//y,x,v 순서로 저장
	            }
	        }
	    }
//        printMap(map, N);
        
//	    for(int[] tt:pq) {
//	    	for(int i=0; i<3; ++i) {
//	    		System.out.println(tt[i]+" ");
//	    	}
//	    System.out.println();
//	    }

        st = new StringTokenizer(bf.readLine());
        int S, X, Y;
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

//        System.out.println(S+" "+X+" "+Y);
        

        
        int tx, ty, xx, yy, vv;
        //다음 pq가 될 임시 큐=>pq2
        PriorityQueue<int[]> pq2 = new PriorityQueue<>(new Comparator<int []>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {//오름차순 정렬
//        		
        		//배열의 마지막 원소(바이러스 번호)가 같다면, 두번째 원소를 기준으로 오름차순 정렬한다.
        		if (o1[2]==o2[2]) {
            		//배열의 두번째 원소도 같다면, 첫번째 원소를 기준으로 오름차순 정렬한다.
            		if(o1[1]==o2[1]) {
            			return o1[0]-o2[0];//첫번째 원소 기준
            		}
        			return o1[1]-o2[1];//두번째 원소 기준
        		}
				return o1[2]-o2[2];    		
        	}
        });	
        for (int s=0; s<S; ++s) {//S초의 시간동안 바이러스 번식        
//        	System.out.println(s);
        	while(!pq.isEmpty()) {//pq에 있는거 다 나와야 한 회차 번식한 것.
//        		System.out.println("!!");
        		//일단 하나 뽑아서 상하좌우로 번식시키기
                int[] virus = pq.poll();
                ty = virus[0];
                tx = virus[1];
                vv = virus[2];
        		
        		for(int i = 0; i < 4; ++i) {
                	yy = ty+dy[i];
                	xx = tx+dx[i];
                	//맵의 범위를 넘어가지 않을 때 & 빈 공간일 때만
                    if(yy>-1 && yy<N && xx>-1 && xx<N && map[yy][xx]==0) {
                    	//해당 바이러스의 번호로 번식시키기
                    	map[yy][xx] = vv;
//                    	System.out.println(yy+" "+xx);
                    	//번식 후의 위치는 임시 큐에 넣어야 함.
                    	pq2.offer(new int[] {yy,xx,vv});
                    }
                }
        	}
        	//1초의 번식이 끝나면(큐 안에 들어있는 바이러스가 1초동안 번식하면,
        	//번식으로 늘어난 애들이 다음으로 번식할 차례이므로 pq2에 임시로 넣어두었다 pq로 전달)
        	
        	for(int[] tt:pq2) {
        		pq.offer(tt);
        	}
//            System.out.println("check");
//    	    for(int[] tt:pq) {
//	    	for(int i=0; i<3; ++i) {
//	    		System.out.print(tt[i]+" ");
//	    	}
//	    System.out.println();
//	    }
            pq2.clear();//옮겼으니까 pq2 초기화
            
        }
        //바이러스가 존재하지 않으면 0출력
        if(map[Y-1][X-1]==0) {System.out.println(0);}
        //존재하면 바이러스의 종류 출력
        else{System.out.println(map[X-1][Y-1]);}
        System.out.println((Y-1)+" "+(X-1));
      printMap(map, N);

    }
}