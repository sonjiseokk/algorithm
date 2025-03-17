import java.io.*;
import java.util.*;

public class Main {
    // 상하좌우 좌상우상좌하우하
    static int[] dx = {0,0,-1,1,-1,1,-1,1};
    static int[] dy = {-1,1,0,0,-1,-1,1,1};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            
            int[][] board = new int[n][m];
            for (int i = 0; i <n; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j<m; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            visited = new boolean[n][m];
            int cnt = 0;
            for (int i = 0; i < n; i ++){
                for (int j = 0; j<m; j++){
                    if (!visited[i][j] && board[i][j] == 1){
                        cnt ++;
                        dfs(board, n, m, i, j);
                    }
                }
            }
            
            System.out.println(cnt);
        }

    }
    
    static void dfs(int[][] board, int n, int m, int y, int x){
        visited[y][x] = true;
        board[y][x] = 0;
        
        // 4방향 탐색
        for (int d = 0; d < 8; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            
            if (visited[ny][nx] || board[ny][nx] != 1) continue;
            visited[ny][nx] = true;
            board[ny][nx] = 0;
            dfs(board, n, m, ny, nx);
        }
    }
}
