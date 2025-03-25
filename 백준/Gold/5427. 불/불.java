import java.io.*;
import java.util.*;

public class Main {
    static int n,m, res;
    static char[][] board;
    static Queue<Node> peopleQueue;
    static Queue<Node> fireQueue;
    
    static boolean isPossible;
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            isPossible = false;
            res = 0;
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            
            board = new char[n][m];

            // 큐 두가지
            peopleQueue = new LinkedList<>();
            fireQueue = new LinkedList<>();
            
            for (int i = 0; i < n; i++){
                String line = br.readLine();
                for (int ii = 0; ii < m; ii++){
                    board[i][ii] = line.charAt(ii);
                    if (board[i][ii] == '*'){
                        fireQueue.add(new Node(ii, i,0));
                    }
                    if (board[i][ii] == '@') {
                        peopleQueue.add(new Node(ii,i,0));
                    }
                }
            }
            
            //---------------------
            
            while (true){
                fire();
                people();
                
                if (isPossible){
                    System.out.println(res);
                    break;
                } else if (peopleQueue.isEmpty()){
                    // 사람 큐에 아무도 없다 -> 탈출이 불가능하다
                    System.out.println("IMPOSSIBLE");
                    break;
                }
            }
            
        }
    }
    
    static void fire(){
        List<Node> prepareNodes = new ArrayList<>();
        while(!fireQueue.isEmpty()){
            Node now = fireQueue.poll();
            
            for (int d = 0; d < 4; d ++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                
                // 경계값 넘는 경우 패스
                if (nx < 0 || ny < 0 || nx >=m || ny >=n) continue;
                // 벽인경우나 이미 불인경우 패스
                if (board[ny][nx] == '*' || board[ny][nx] == '#') continue;
                
                board[ny][nx] = '*';
                prepareNodes.add(new Node(nx,ny,now.depth + 1));
            }
        }
        
        for (Node nxt : prepareNodes){
            fireQueue.add(nxt);
        }
    }
    
    static void people(){
        List<Node> prepareNodes = new ArrayList<>();
        while(!peopleQueue.isEmpty()){
            Node now = peopleQueue.poll();
            
            for (int d = 0; d < 4; d ++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                
                // 경계값 넘는 경우 패스
                if (nx < 0 || ny < 0 || nx >=m || ny >=n) {
                    res = now.depth + 1;
                    isPossible = true;
                    return;
                }
                // 이미 갔거나 불이거나 벽인경우
                if (board[ny][nx] == '@' || board[ny][nx] == '#' || board[ny][nx] == '*') continue;
                
                board[ny][nx] = '@';
                prepareNodes.add(new Node(nx,ny,now.depth + 1));
            }
        }
        
        for (Node nxt : prepareNodes){
            peopleQueue.add(nxt);
        }
    }
    static class Node {
        int x;
        int y;
        int depth;
        public Node(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
