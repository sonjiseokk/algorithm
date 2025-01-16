import java.io.*;
import java.util.*;

public class Main {
    // 좌우상하 좌상우상좌하우하
    private static int[] dx = {-1,1,0,0, -1,1,-1,1};
    private static int[] dy = {0,0,-1,1, -1,-1,1,1};
    public static void main(String[] args) throws Exception{
        // 코드를 작성해주세요
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;
            
            int[][] map = new int[n][m];
            for (int i = 0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<m; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int result = 0;
            for (int i = 0; i<n; i++) {
                for (int j=0; j<m; j++){
                    result += logic(map,n,m,i,j);
                }
            }

            System.out.println(result);
        }
    }
    
    private static int logic(int[][] map, int n, int m, int sy, int sx){
        if (map[sy][sx] == 0) return 0;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx,sy));
        map[sy][sx] = 0;
        
        while (!queue.isEmpty()){
            Node now = queue.poll();
            
            for (int i=0; i<8; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || ny <0 || nx >= m || ny >= n) continue;
                if (map[ny][nx] == 0) continue;
                
                map[ny][nx] = 0;
                queue.add(new Node(nx,ny));
            }
        }
        
        return 1;
        
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
