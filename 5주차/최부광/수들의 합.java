import java.util.Scanner;

// [백준1789] 수들의 합
public class Main {
    static long S;
    static int res = 0;

    public static void main(String[] args) {
        // 값 입력
        Scanner sc = new Scanner(System.in);
        S = sc.nextLong();

        // 연산 수행
        Long sum = 0L;
        for(int i=1; i<100000; i++){
            sum += i;
            res++;
            if(S < sum) break;
        }

        // 결과 출력
        System.out.println(res-1);
    }
}
