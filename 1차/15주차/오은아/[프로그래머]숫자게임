import java.util.*;

class Solution {
    
    public int solution(int[] A, int[] B) {
        
        int[] a= new int[A.length];
        int[] b= new int[B.length];
        
        //A,B를 정렬함
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0; i<A.length; i++)
            a[A.length-i-1]= A[i];
        
        for(int i=0; i<B.length; i++)
            b[B.length-i-1]= B[i];
        
        //B에서 제일 큰값과 A에서 제일 큰 값 비교
        //B가 이기면 다음으로 비교
        //B가 같거나 지면 진 걸로치고 A에서 다음으로 큰거랑 비교
        int cnt= 0;
        int ans= 0;
        for(int i=0; i<a.length; i++){// 7 5 3 1
            
            if(a[i] < b[cnt]){// 8 6 2 2
                ans++;
                cnt++;
                continue;
            }
            
            continue;
            
        }
        
        return ans;
    }
}
