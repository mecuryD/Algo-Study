package B.병사관리;

class UserSolution
{

    public class Node{//병사 한명=노드 하나
        int id;
        int ver;
        Node next;

        public Node(){}

        public Node(int id, Node next){
            this.id = id;
            this.next = next;
        }

        public Node(int id, int ver, Node next) {
            this.id = id;
            this.ver = ver;
            this.next = next;
        }
    }

    //팀 클래스-> 각 점수대별 head, tail필요
    public class Team{
        Node[] head = new Node[6];//각 점수대별 head
        Node[] tail = new Node[6];
    }

    public Node[] node = new Node[200055];
    public int[] version = new int[100055];//몇번 수정됐는지?/해고여부(-1)
    public int[] teamNum = new int[100055];//병사 ID별 팀 저장용도
    public Team[] team = new Team[6];//1~5번 팀 생성

    public int cnt=0;

    //init에서 만들어놓은 노드에 값을 넣음.
    public Node getNewNode(int id, Node next){
        Node ret = node[cnt++];
        ret.id = id;
        ret.ver = ++version[id];
        ret.next = next;
        return ret;
    }

    public void init()
    {
        cnt = 0;
        //병사 배열 초기화
        for(int i=0; i<200055; ++i){
            node[i] = new Node();
        }

        //팀 배열 초기화
        for(int i=1; i<6; ++i){
            team[i] = new Team();
            for(int j=1; j<6; ++j){
                team[i].tail[j] = team[i].head[j] = getNewNode(0,null);
            }
        }

        for(int i=0; i<=100000; ++i){
            version[i] = 0;
            teamNum[i] = 0;
        }

    }


    //고유번호가 mID, 소속팀이 mTeam, 평판 점수가 mScore인 병사를 고용
    public void hire(int mID, int mTeam, int mScore)
    {
        Node newSoldier = getNewNode(mID, null);// ver=next=null
        teamNum[mID] = mTeam;//팀설정
        team[mTeam].tail[mScore].next = newSoldier;//다음을 새노드로 바꾸고
        team[mTeam].tail[mScore] = newSoldier;//tail도 업데이트

    }

    public void fire(int mID)
    {
        version[mID] = -1;
    }

    public void updateSoldier(int mID, int mScore)
    {
        hire(mID, teamNum[mID], mScore);
    }

    public void updateTeam(int mTeam, int mChangeScore)
    {
        int tmp;
        if(mChangeScore<0){//점수를 빼는 경우
            for(int i=1; i<6; ++i){//1점부터 시작해야 점수가 중복해서 빠지지 않음
                tmp = i+mChangeScore;
                if(tmp<1) tmp=1;
                if(tmp == i) continue;//원래점수랑 똑같으면 굳이 바꿀 필요x


                if (team[mTeam].head[i].next == null) continue;//해당 점수대에 사람이 없으면 다음 점수로 이동
                //원래 점수랑 달라졌으면 바뀐 점수쪽으로 이어 붙여주기
                //mTeam번 팀의 tmp 점수대의 tail에 i번 점수대의 head 붙이기
                team[mTeam].tail[tmp].next = team[mTeam].head[i].next;
                team[mTeam].tail[tmp] = team[mTeam].tail[i];//tmp점수대의 tail도 i번 점수대의 tail로 변경
                team[mTeam].head[i].next = null;
                team[mTeam].tail[i] = team[mTeam].head[i];
            }
        }

        if(mChangeScore>0){//점수를 더하는 경우
            for(int i=5; i>0; i--){//5점부터 시작해야 점수가 중복해서 더해지지 않음
                tmp = i+mChangeScore;
                if(tmp>5) tmp=5;
                if(tmp == i) continue;//원래점수랑 똑같으면 굳이 바꿀 필요x

                if (team[mTeam].head[i].next == null) continue;
                //원래 점수랑 달라졌으면 바뀐 점수쪽으로 이어 붙여주기
                //mTeam번 팀의 tmp 점수대의 tail에 i번 점수대의 head 붙이기
                team[mTeam].tail[tmp].next = team[mTeam].head[i].next;
                team[mTeam].tail[tmp] = team[mTeam].tail[i];//tmp점수대의 tail도 i번 점수대의 tail로 변경
                team[mTeam].head[i].next = null;
                team[mTeam].tail[i] = team[mTeam].head[i];
            }
        }


    }

    public int bestSoldier(int mTeam)
    {
        int ans;
        for(int i=5; i>0; i--){
            Node node = team[mTeam].head[i].next;//첫 노드? 저장
            if(node == null) continue;// 해당 점수대에 노드가 없으면 넘어가기
            ans = 0;
            while(node != null){
                if(node.ver == version[node.id]){
                    if(ans < node.id) ans = node.id;

                }
                node = node.next;
            }
            if(ans != 0) return ans;

        }
        return 0;
    }
}