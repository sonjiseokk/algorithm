import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static char[][] board;
    static int resDir;
    static int resDepth;
    // 상하좌우
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static boolean isVoyager;
    static boolean[][][] visited;
    static int bestVoyagerDir = -1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new char[n][m];
        for (int i = 0; i < n; i++){
            String line = br.readLine();
            for (int j = 0; j < m; j++){
                board[i][j] = line.charAt(j);
            }
        }
        
        st = new StringTokenizer(br.readLine());
        // 0 인덱스 조정
        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;
        
        // dfs 돌면서 뎁스 측정
        // 만약 뎁스가 갱신될수있으면 갱신
        // 같을때는 알파벳 교체
        // 방문 배열은 방향마다 계속 초기화
        for (int d = 0; d < 4; d++){
            visited = new boolean[n][m][4];
            dfs(startX, startY, d, 1);
        }
        
        char[] dirChar = {'U', 'R', 'D', 'L'};
        System.out.println(dirChar[isVoyager ? bestVoyagerDir : resDir]);
        System.out.println(isVoyager ? "Voyager" : resDepth);

    }
    
    static void dfs(int x, int y, int dir, int depth){
        // 시작 지점 백업
        int sX = x;
        int sY = y;
        int sDir = dir;
        
        // dfs -> while로 수정
        // 이유는 재귀 한계
        while (true){
            x = x + dx[dir];
            y = y + dy[dir];
            // 만약 벽이거나 블랙홀인 경우 -> 중단이 된다는 거니까 갱신하고 종료
            if (x < 0 || y < 0 || x >= m || y >= n || board[y][x] == 'C') {
                if (depth > resDepth) {
                    resDepth = depth;
                    resDir = sDir;
                } else if (depth == resDepth) {
                    resDir = Math.min(resDir, sDir);
                }
                return;
            }

        
            // 만약 처음으로 돌아왔는데 방향도 똑같다면 -> 무한반복임
            if (visited[y][x][dir]) {
                
                isVoyager = true;
                if (bestVoyagerDir == -1 || sDir < bestVoyagerDir) {
                    bestVoyagerDir = sDir;
                }
                return;
            }
            
            visited[y][x][dir] = true;
            
            // 방향 회전
            if (board[y][x] == '\\') {
                if (dir == 0) dir = 3;
                else if (dir == 2) dir = 1;
                else if (dir == 1) dir = 2;
                else dir = 0;
            } else if (board[y][x] == '/') {
                if (dir == 0) dir = 1;
                else if(dir == 1) dir = 0;
                else if (dir == 2) dir = 3;
                else dir = 2;
            }
            
            depth++;
        }
    }
    // 0 1 2 3 : 상하좌우
}
