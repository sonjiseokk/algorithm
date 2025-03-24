import java.io.*;
import java.util.*;

public class Main {
    // URDL
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int n,m;
    static char[][] board;
    static boolean[][][] visited;
    static boolean isVoyager;
    static int bestVoyagerDir = -1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new char[n][m];
        for (int i = 0; i<n; i++){
            String line = br.readLine();
            for (int j = 0; j <m; j++){
                board[i][j] = line.charAt(j);
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;
        
        // --------------
        
        // 임시용 큰값
        int resDir = 5;
        int resCnt = 0;
        
        // 4방향으로 이동
        for (int d = 0; d < 4; d++){
            visited = new boolean[n][m][4];
            int cnt = run(startX, startY, d);
            
            // 더 많이 전파된다면 그거로 교체
            if (resCnt < cnt) {
                resDir = d;
                resCnt = cnt;
            } else if (resCnt == cnt){
                // 같다면 더 앞서는 방향으로 교체
                resDir = Math.min(resDir, d);
            }
        }
        
        char[] dirChar = {'U','R','D','L'};
        if (isVoyager){
            System.out.println(dirChar[bestVoyagerDir]);
            // 광야로 걸어가~
            System.out.println("Voyager");
        } else {

            System.out.println(dirChar[resDir]);
            System.out.println(resCnt);
        }
    }
    
    static int run(int x, int y, int d){
        int cnt = 0;
        // 벽 너머가거나 무한루프 돌때까지 반복
        while (true){        
            cnt ++;
            // 방향대로 증가
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            // 기저조건
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || board[ny][nx] == 'C') {
                break;
            }
            
            if (visited[ny][nx][d]) {
                isVoyager = true;
                if (bestVoyagerDir == -1 || bestVoyagerDir > d){
                    bestVoyagerDir = d;
                }
                break;
            }    
            
            visited[ny][nx][d] = true;
            
            // 만약 행성이라면 방향 전환
            if (board[ny][nx] == '\\'){
                d = 4 - d - 1;
            } else if (board[ny][nx] == '/'){
                if (d == 0) d = 1;
                else if(d == 1) d = 0;
                else if(d == 2) d = 3;
                else d = 2;
            }
            
            x = nx;
            y = ny;
        }
        
        return cnt;
    }
}
