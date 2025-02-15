import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        
        int[][] board = new int[n][m];
        for (int i = 0; i< n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        rotate(board,n,m,r);

        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< n; i++){
            for (int j = 0 ; j < m; j++){
                sb.append(board[i][j]).append(" ");
            }
            if (i== n-1) continue;
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    
    static void rotate(int[][] board, int n, int m, int r){
        int maxLayer = Math.min(n,m) / 2;
        for (int layer = 0; layer < maxLayer; layer++){
            
            int cycleLength= 2 * ((n - 2*layer) + (m - 2*layer) - 2);
            int rotateCount = (int) r % cycleLength;

            
            for (int t = 0; t < rotateCount; t++){
                int x = layer;
                int y = layer;
                int value = board[y][x];
                for (int d = 0; d < 4; d++){
                    while (true){
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                    
                        if (nx < layer || ny < layer || nx >= m - layer || ny >= n - layer) break;
                        board[y][x] = board[ny][nx];
                        x = nx;
                        y = ny;
                    }
                }
            
                board[layer+1][layer] = value;
            }

        }
        
    }
}
