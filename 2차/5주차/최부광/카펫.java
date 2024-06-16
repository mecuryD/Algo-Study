// [프로그래머스] 카펫
// 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 같은 색으로 칠해져 있는 격자 모양 카펫
// 갈색 격자, 노란 격자의 수가 주어질 때 카펫의 [가로, 세로] 크기를 배열에 담아 리턴
// 가로 길이는 세로 길이와 같거나 세로 길이보다 길다

class Solution {
    public int[] solution(int brown, int yellow) {
        // 가로 테두리, 세로 테두리 길이 초기화
        int outline = (brown + 2) / 2;
        int width = (int) Math.ceil((outline + 1) / 2.0);
        int ehgith = outline - width + 1;

        // 가로, 세로 길이를 바꿔가며 노란 격자의 수가 일치하는지 확인한다
        while((width-2) * (height-2) != yellow){
          width++;
          height--;
        }
        
        return new int[]{width, height};
    }
}
