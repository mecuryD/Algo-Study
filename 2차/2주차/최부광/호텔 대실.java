// [프로그래머스] 호텔 대실
class Solution {
    public int solution(String[][] book_time) {
        int[] reservations = new int[1440];
        
        for(String[] book : book_time) {
            int start = getIndex(book[0]) ; 
            int end = getIndex(book[1]) + 9;
            
            for(int i=start; i<=end; i++){
                reservations[i % 1440]++;
            }
        }
        
        int count = 0;
        for(int i=0; i<1440; i++){
            count = (count < reservations[i]) ? reservations[i] : count;
        }
        return count;
    }
    
    public int getIndex(String time){
        String[] strArr = time.split(":");
        return Integer.parseInt(strArr[0]) * 60 + Integer.parseInt(strArr[1]);
    }
}
