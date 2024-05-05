package BOJ;

import java.io.*;
import java.util.*;

public class 트리_제출용 {
    static List<Integer>[] graph;
    static int root;
    static boolean[] isDeleted;
    static int[] parent;
    //삭제 시, 자식만 삭제할 게 아니라, 부모에게서 자신을 삭제하는 작업도 필요
    static void remove(int node) {
//        System.out.println("삭제할 노드는: "+node);
////        System.out.println(len);
//        System.out.println("부모에서 이 노드를 없애주자");
//        System.out.println("부모 노드는" + parent[node]);

        isDeleted[node] = true;//삭제했다고 표시하기
        //부모노드에서도 삭제시키기(-1로 바꾸는 방법도 있지만,, 그렇게하면 부모노드 삭제시 for문 돌때 -1이면 continue하는 내용 넣어줘야함. + 그게 몇번째 노드인지 위치를 찾아서 -1로 바꿔야함.)
        graph[parent[node]].remove(Integer.valueOf(node));//그냥 node를 전달하면 node번째 원소가 삭제됨.

        // 리프노드면 리턴!
        int len = graph[node].size();
//        System.out.println("자식수: "+len);
        if (len == 0) return;

        int tmp;
        while(graph[node].size()!=0){//리스트가 빌때까지 0번원소부터 뽑아서 삭제하기!
            tmp = graph[node].get(0);
//            System.out.println("node의 자식: "+tmp);
//            System.out.println("자식 노드 삭제하러가자");
            remove(tmp);  //(해당 노드에 달린 자식을 모두 삭제하면
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(bf.readLine()); // 배열 인덱스로 쓰일거니까 1빼고 진행
        st = new StringTokenizer(bf.readLine());
        int removeNode = Integer.parseInt(bf.readLine());
        isDeleted = new boolean[N];//삭제된 노드와 리프노드를 구별하기 위한 배열
//        isDeleted[0] = true;//0번은 세면 안되니까 따로 처리해줌
        parent = new int[N];
        //부모 정보 저장할 배열
        for (int i=0; i<N; ++i){
            parent[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N];//리스트의 배열 선언

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }
        int tmp;
        for (int i = 0; i < N; ++i) {
            // 루트인 경우에는 따로 표시해두기(root)
            if (parent[i] == -1) root = i;
                //i번 노드의 부모 노드에 i를 추가하는 작업
            else graph[parent[i]].add(i);
        }


        // 루트를 삭제하는 경우는 빠르게 리턴
        if (removeNode == root) {
            System.out.println(0);
            return;
        }


//        System.out.println("삭제 전: ");
//        for (int i = 0; i < N; ++i) {
//            System.out.print("노드 " + i + ": ");
//            for (int j = 0; j < graph[i].size(); ++j) {
//                System.out.print(graph[i].get(j) + " ");
//            }
//            System.out.println();
//        }

        remove(removeNode);


//        System.out.println("삭제 후: ");
//        for (int i = 0; i < N; ++i) {
//            System.out.print("노드 " + i + ": ");
//            for (int j = 0; j < graph[i].size(); ++j) {
//                System.out.print(graph[i].get(j) + " ");
//            }
//            System.out.println();
//        }

        int cnt=0;

        cnt=0;
        for(int i=0; i<N; ++i){
            if(isDeleted[i]) continue;//삭제된 노드는 세지 않는다.

            if(graph[i].size()==0) ++cnt;
        }
        System.out.println(cnt);
    }
}