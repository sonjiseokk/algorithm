import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] board;
    static int [][] dist;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (true){
            n = Integer.parseInt(br.readLine());
            
            // 종료 구문
            if (n == 0) break;
            
            // 보드 입력
            board = new int[n][n];
            dist = new int[n][n];
            for (int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j ++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            
            dij();
            
            sb.append("Problem " + idx +": " + dist[n-1][n-1] +"\n");
            idx++;
        }
        System.out.println(sb.toString());
    }
    
    static void dij() {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0,0,board[0][0]));
        dist[0][0] = board[0][0];
        
        while (!queue.isEmpty()){
            Node now = queue.poll();
            
            // 종료구문
            if (now.x == n-1 && now.y == n-1) {
                break;
            }
            
            if (now.cost > dist[now.y][now.x]) continue;
            
            for (int d = 0; d < 4; d ++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                
                int newCost = now.cost + board[ny][nx];
                if (newCost >= dist[ny][nx]) continue;
                
                dist[ny][nx] = newCost;
                queue.add(new Node(nx,ny,newCost));
                
            }
        }
    }
    
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;
        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node){
            return Integer.compare(this.cost, node.cost);
        }
    }
}
