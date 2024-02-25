import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Counsel> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<Counsel>();
        for(int i=1; i<=N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int T = Integer.parseInt(stk.nextToken());
            int P = Integer.parseInt(stk.nextToken());
            list.add(new Counsel(i, i+T-1, P));
        }

        Collections.sort(list, new Comparator<Counsel>() {
            @Override
            public int compare(Counsel o1, Counsel o2) {
                return (o1.e - o2.e);
            }
        });

        int idx = 0;
        int[] max = new int[N+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<i; j++){
                if(max[i] < max[j]){
                    max[i] = max[j];
                }
            }
            
            if(list.get(idx).e != i) {
                continue;
            }

            while(idx < list.size() && list.get(idx).e == i){
                Counsel cur = list.get(idx);
                int money = max[cur.s-1] + cur.p;
                if(max[i] < money) {
                    max[i] = money;
                }

                idx++;
            }
        }
        int result = -1;
        for(int m : max){
            result = (result < m) ? m : result;
        }

        System.out.println(result);
    }

    static class Counsel {
        int s; 
        int e; 
        int p; 

        public Counsel(int s, int e, int p){
            this.s = s;
            this.e = e;
            this.p = p;
        }
    }
}
