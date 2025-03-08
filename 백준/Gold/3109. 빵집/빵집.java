import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1,1,1};
    static int[] dy = {-1,0,1};
    static int r,c;
    static boolean[][] visited;
    static char[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        map = new char[r][c];
        for (int i = 0; i < r; i++){
            String line = br.readLine();
            for (int j = 0; j< c; j++){
                map[i][j] = line.charAt(j);
            }
        }
        
        visited = new boolean[r][c];
        
        int res = 0;
        for (int i = 0; i<r; i++){
            if (dfs(i,0)){
                res++;
            }
        }
        
        System.out.println(res);
    }
    
    private static boolean dfs(int y, int x){
        // 만약 방문했거나 벽인 경우
        if (visited[y][x] || map[y][x] == 'x') return false;
        
        // 끝에 도착한 경우
        if (x == c - 1){
            return true;
        }

        // 방문 체크
        visited[y][x] = true;
                
        // 3방향 이동
        boolean result = false;
        for (int i = 0; i<3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= c || ny >= r || map[ny][nx] == 'x') continue;
            
            if (dfs(ny,nx)) return true;
        }
        
        // 끝에 도착하지 못한 경우
        return result;
    }
}
