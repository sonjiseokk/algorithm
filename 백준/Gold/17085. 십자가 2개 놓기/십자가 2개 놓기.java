import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,m,res;
    static char[][] board;
    static List<Node> crossList = new ArrayList<>();
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
        
        //--------------
        
        // n x m을 탐색하며 십자가 가능한지 체크
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                check(j,i);
            }
        }
        
        int length = crossList.size();
        
        // 첫번째 노드 뽑아
        for (int i = 0; i < length; i++){
            Node now = crossList.get(i);
            
            boolean[][] visited = new boolean[n][m];
            // 방문 체크 해놔
            // 이미 사이즈만큼 돌았기에 예외 없음
            mark(visited, now);
                
            // 두번째 노드 뽑아
            for (int j = i+1; j < length; j++){
                Node other = crossList.get(j);
                // 얘도 사이즈만큼 방문 체크해
                boolean isDuplicated = false;
                for (int os = 0; os < other.size; os++){
                    for (int d = 0; d < 4; d++){
                        int nx = other.x + (dx[d] * os);
                        int ny = other.y + (dy[d] * os);
                        
                        // 다만 이미 방문이였던 경우는 불가능하므로 패스
                        if (visited[ny][nx]) {
                            isDuplicated = true;
                            break;
                        }
                    }
                    
                    // 덧붙여지는 케이스니까 반복문 끝내
                    if (isDuplicated) break;
                }
                    
                // 다음 노드 탐색해
                if (isDuplicated) continue;
                else {
                    int a = 4 * (now.size - 1) + 1;
                    int b = 4 * (other.size - 1) + 1;

                    res = Math.max(a * b,res);
                }
            }
            

        }

        System.out.println(res);
    }
    
    static void check(int x, int y){
        int size = 0;
        while (true){
            boolean isEnabled = true;
            for (int d = 0; d < 4; d++){
                int nx = x + (dx[d] * size);
                int ny = y + (dy[d] * size);
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || board[ny][nx] == '.'){
                    isEnabled = false;
                    break;
                }
            }
            
            // 4방향 돌고나서 가능하다면 사이즈 증가 + 큐에 추가
            if (isEnabled){
                // 넓이가 1인게 1로 저장되기 위해 먼저 사이즈부터 증가시킴
                size++;
                crossList.add(new Node(x,y,size));
            } else {
                // 무한루프 종료
                break;
            }
        }
    }
    static void mark(boolean[][] visited, Node now){
        for (int s = 0; s < now.size; s++){
            for (int d = 0; d < 4; d++){
                int nx = now.x + (dx[d] * s);
                int ny = now.y + (dy[d] * s);
                    
                visited[ny][nx] = true;
            }
        }
    }
    
    static class Node {
        int x;
        int y;
        int size;
        public Node(int x, int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;   
        }
    }
}
