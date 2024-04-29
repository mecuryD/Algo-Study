import java.io.*;
import java.util.*;

class Node {
    int idx;
    Node P;
    Node N;
    Node(int idx) {
        this.idx = idx;
    }
}
class UserSolution
{

    static final int MAX = 400210;
    static final int start = 100;
    static final int end = MAX - 105;

    static Node[] node = new Node[MAX];
    static int cnt = 0;
    static TreeMap<Integer, Node> nodeMap[] = new TreeMap[101];



    public static void init() {
        for (int i = 0; i < MAX; i++) {
            node[i] = new Node(i);
        }
        for (int i = 1; i <= 100; i++) {
            nodeMap[i] = new TreeMap<>();
            
            nodeMap[i].put(0, node[i]);

            nodeMap[i].put(1000000000, node[end + i - 1]);
            attach(node[i], node[end + i - 1]);
        }
        cnt = 101;
    }

    public static void attach(Node N1, Node N2) {
        N1.N = N2;
        N2.P = N1;
    }

    public static void add(int mX, int mY) {
        Node Left = node[cnt++];
        Node Right = node[cnt++];

        Node pLeft = nodeMap[mX].floorEntry(mY).getValue();
        Node pRight = nodeMap[mX + 1].floorEntry(mY).getValue();

        Node nLeft = pLeft.N;
        Node nRight = pRight.N;

        attach(pLeft, Right);
        attach(Right, nRight);

        attach(pRight, Left);
        attach(Left, nLeft);

        nodeMap[mX].put(mY, Left);
        nodeMap[mX + 1].put(mY, Right);
    }

    public static void remove(int mX, int mY) {
        Node Left = nodeMap[mX].get(mY);
        Node Right = nodeMap[mX + 1].get(mY);

        Node pLeft = Right.P;
        Node pRight = Left.P;

        Node nLeft = Left.N;
        Node nRight = Right.N;

        attach(pLeft, nLeft);
        attach(pRight, nRight);

        nodeMap[mX].remove(mY);
        nodeMap[mX + 1].remove(mY);
    }

    public static int numberOfCross(int mID) {
        int ret = -1;
        Node now = node[mID];
        while (now.idx < node[end].idx) {
            ret++;
            now = now.N;
        }
        return ret;
    }

    public static int participant(int mX, int mY) {
        Node now = nodeMap[mX].floorEntry(mY).getValue();
 
        while (now.idx > node[start].idx)
            now = now.P;
        return now.idx;
    }
}
