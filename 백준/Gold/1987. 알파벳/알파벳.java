import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited = new boolean[26];
    static int result;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        char[][] board = new char[n][m];
        
        for (int i = 0; i <n; i++){
            String line = br.readLine();
            for (int j = 0; j<m; j++){
                board[i][j] = line.charAt(j);
            }
        }
        
        dfs(board,n,m,0,0,1);
        
        System.out.println(result);
    }
    
    static void dfs(char[][] board, int n , int m, int x, int y, int depth){
        // 방문 체크
        visited[board[y][x] - 'A'] = true;
        result = Math.max(result, depth);
        for (int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (visited[board[ny][nx] - 'A']) continue;
            
            dfs(board, n,m,nx,ny,depth+1); 
        }
        
        visited[board[y][x] - 'A'] = false;
    }
}
