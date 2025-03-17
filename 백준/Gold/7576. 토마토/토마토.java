import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static List<Node> starts = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result, isWall;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        int[][] board = new int[n][m];
        for (int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1){
                    starts.add(new Node(j,i,0));
                }
                if (board[i][j] == -1) isWall++;
            }
        }
        
        visited = new boolean[n][m];
        bfs(board, n, m);
        
        int cnt = 0;
        for (int i = 0; i< n; i++){
            for (int j = 0; j < m; j++){
                if (visited[i][j]){
                    cnt ++;
                }
            }
        }
        if (cnt == n * m - isWall) System.out.println(result);
        else System.out.println(-1);
    }
    
    static void bfs(int[][] board, int n, int m){
        Queue<Node> queue = new LinkedList<>();
        for (Node s : starts){
            queue.add(s);
            visited[s.y][s.x] = true;
        }
        
        while (!queue.isEmpty()){
            Node now = queue.poll();
            
            for (int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                
                if (visited[ny][nx]) continue;
                
                if (board[ny][nx] != 0) continue;
                
                visited[ny][nx] = true;
                queue.add(new Node(nx,ny,now.depth + 1));
                result = now.depth+1;
            }
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
