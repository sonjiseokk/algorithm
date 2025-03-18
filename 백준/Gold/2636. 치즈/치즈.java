import java.io.*;
import java.util.*;

public class Main {
    static List<Node> airList = new ArrayList<>();
    static boolean[][] visited;
    static int[][] board;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <m; j++){
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                // 모서리만 추가
                if (i == 0 || j == 0 || i == n-1 || j == m - 1) airList.add(new Node(j,i));
            }
        }
        
        visited = new boolean[n][m];
        // 다 없어질때까지
        int time = 0;
        int prev = 0;
        while (true){
            // 먼저 1을 체크
            int cnt = checkOne(n,m);
            // 만약 체크한게 0이다 -> 이전에 다 끝났다 그래서 prev 안바꾸고 넘어간다
            if (cnt == 0) break;
            else prev = cnt;
            
            // 치즈를 녹여
            bfs(n,m);
            time ++;
            visited = new boolean[n][m];
        }
        
        System.out.println(time);
        System.out.println(prev);
    }
    
    static void bfs(int n, int m){
        Queue<Node> queue = new LinkedList<>();
        for (Node air : airList){
            visited[air.y][air.x] = true;
            queue.add(air);
        }
        
        while (!queue.isEmpty()){
            Node now = queue.poll();
            
            for (int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[ny][nx]) continue;
                
                // 치즈인 경우 2로 변환
                visited[ny][nx] = true;
                // 이미 방문했기에 변한 치즈가 큐에 추가되지 않음
                if (board[ny][nx] == 1) {
                    board[ny][nx] = 0;
                }
                // 공기인 경우 큐에 추가
                else if (board[ny][nx] == 0){
                    queue.add(new Node(nx,ny));
                }
            }
        }
    }
    
    static int checkOne(int n, int m){
        int cnt = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (board[i][j] != 0){
                    cnt ++;
                }
            }
        }   
        
        return cnt;
    }
    static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
}

// 1. 0 인거 추가
// 2. 1을 2로 바꿈
// 3. 전체 돌면서 1 2 카운트 -> 한시간전 칸의 개수
// 4. 전체 돌때 리스트에 추가