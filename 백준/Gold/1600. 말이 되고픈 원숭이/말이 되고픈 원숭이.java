import java.io.*;
import java.util.*;

public class Main {
    static int k, n, m;
    static int[][] board;
    // 좌우상하 왼쪽위부터 오른쪽 아래로
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    static int[] hdx = {-1, 1, -2, 2, -2, 2, -1,1};
    static int[] hdy = { -2, -2, -1,-1,1,1,2,2};
    static boolean isTrue = false;
    static int res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        logic();
        
        System.out.println(isTrue ? res : "-1");
    }
    
    static void logic(){
        // 최단 거리 저장할 배열
        boolean[][][] visited = new boolean[n][m][k+1];
        visited[0][0][0] = true;
        
        // 큐 생성
        Queue<Node> queue = new LinkedList<>();
        
        // 말의 최초 위치를 큐에 추가
        queue.add(new Node(0,0,0,0));
        
        while (!queue.isEmpty()){
            Node now = queue.poll();
            
            // 종료구문
            if (now.x == m-1 && now.y == n - 1) {
                res = now.depth;
                isTrue = true;
                return;
            }
            // 원숭이 이동
            for (int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                
                // 벽에 충돌한 경우
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                
                // 최단거리가 아닌 경우
                if (visited[ny][nx][now.telpo]) continue;
                
                // 장애물인 경우
                if (board[ny][nx] == 1) continue;
                
                queue.add(new Node(nx,ny,now.depth+1,now.telpo));
                visited[ny][nx][now.telpo] = true;
            }
            
            if (now.telpo < k){
                for (int d = 0; d < 8; d++){
                    int nx = now.x + hdx[d];
                    int ny = now.y + hdy[d];
                
                    // 벽에 충돌한 경우
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                
                    // 최단거리가 아닌 경우
                    // 텔포 쓴 경우니까 + 1
                    if (visited[ny][nx][now.telpo + 1]) continue;
                
                    // 장애물인 경우
                    if (board[ny][nx] == 1) continue;
                
                    queue.add(new Node(nx,ny,now.depth+1,now.telpo + 1));
                    visited[ny][nx][now.telpo + 1] = true;
                }
            }

        }

    }
    
    static class Node{
        int x;
        int y;
        int depth;
        int telpo;
        public Node(int x, int y, int depth, int telpo){
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.telpo = telpo;
        }
    }
}
