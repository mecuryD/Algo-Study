import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        StringTokenizer stk = new StringTokenizer(today, ".");
        int year = Integer.parseInt(stk.nextToken());
        int month = Integer.parseInt(stk.nextToken());
        int day = Integer.parseInt(stk.nextToken());
        int dateToday = day + month*28 + year*28*12;
        List<Integer> ansList = new LinkedList<Integer>();
        
        int size = privacies.length;
        int termSize = terms.length;
        
        for(int i = 0; i < size; i++){
            // 일자와 종류 구분
            StringTokenizer stkTemp1 = new StringTokenizer(privacies[i]);
            String date = stkTemp1.nextToken();
            char type = stkTemp1.nextToken().charAt(0);
            // 일자 년월일로 구분
            StringTokenizer stkTemp2 = new StringTokenizer(date, ".");
            int yearTemp = Integer.parseInt(stkTemp2.nextToken());
            int monthTemp = Integer.parseInt(stkTemp2.nextToken());
            int dayTemp = Integer.parseInt(stkTemp2.nextToken());
            int dateTemp = dayTemp + monthTemp*28 + yearTemp*28*12;
            
            // term 돌아다니며 일치하는 약관 유효기간 더하기
            for(int j = 0; j < termSize; j++){
                if(terms[j].charAt(0) == type){
                    // 약관 동일하면 더해서 파기 기준일 계산
                    StringTokenizer stkTemp3 = new StringTokenizer(terms[j]);
                    String temp = stkTemp3.nextToken();
                    dateTemp += Integer.parseInt(stkTemp3.nextToken())*28;
                    
                    // 파기여부 계산 : 파기 기준일 이상이면 추가
                    if(dateTemp <= dateToday){
                        ansList.add(i+1);
                    }
                }
            }
            
        }
        
        answer = new int[ansList.size()];
        for (int i = 0 ; i < ansList.size() ; i++){
            answer[i] = ansList.get(i).intValue();
        }
        
        
        return answer;
    }
}
