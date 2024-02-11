import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N; // 노드의 개수
	static int D; // 지울 노드
	
	static int root;  // 루트 노드
	static int leaf;  // 리프 노드의 개수
	static int[] parents; // 부모 노드 배열
	static ArrayList<Integer>[] tree; // 트리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		
		// 트리 초기화
		tree = new ArrayList[N];
		for(int i=0; i<N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		// 트리 입력
		parents = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			int p = Integer.parseInt(stk.nextToken());
			parents[i] = p;
			
			if(p == -1) root = i; // 루트 노드인 경우
			else tree[p].add(i); // 루트 노드가 아닌 경우
		}
		
		// 노드를 제거하고 트리 탐색
		D = Integer.parseInt(br.readLine());
        
		// 제거하는 노드가 루트 노드가 아닌 경우
		if(D != root) {
			// 노드와 연결된 부모 관계를 끊는다
			int p = parents[D];
			int pLen = tree[p].size();
			
			for(int i=0; i<pLen; i++) {
				if(tree[p].get(i)==D) {
					tree[p].remove(i);
					break;
				}
			}
			
			// 연결된 자식 관계를 끊는다
			tree[D].clear();
			
			// 루트 노드부터 탐색 시작
			searchTree();
		}
		// 결과 출력
		System.out.println(leaf);
	}
	
	// 리프 노드 탐색 메소드
	static void searchTree(){
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			// 노드 하나를 꺼낸다
			int node = queue.poll();
			
			// 리프 노드인지 확인한다
			if(tree[node].size()==0) {
				// 리프 노드이고, D노드가 아니라면 카운트 1 증가
				if(node != D) leaf++;
				continue;
			}
			
			// 리프 노드가 아니라면 자식 탐색
			for(int child : tree[node]) {
				queue.offer(child);
			}
		}
	}
}
