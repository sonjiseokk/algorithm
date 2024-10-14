import java.io.*;
import java.util.*;

class Solution {
    private static int answer;
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        

        return bfs(maps, n, m);
    }
    
    private int bfs(int[][] maps, int n, int m){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        boolean[][] visited = new boolean[n][m];
        Queue<Node> queue = new LinkedList<>();
        // 시작점 추가
        visited[0][0] = true;
        queue.add(new Node(0,0,1));
        
        while(!queue.isEmpty()){
            Node now = queue.poll();
            
            for(int i =0; i< 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= m || ny >= n){
                    continue;
                }
                
                if (visited[ny][nx]){
                    continue;
                }
                
                // 벽인 경우
                if(maps[ny][nx] == 0){
                    continue;
                }
                if(nx == m - 1 && ny ==n - 1 ){
                    return now.value + 1;
                }
                
                visited[ny][nx] = true;
                queue.add(new Node(nx,ny,now.value + 1));
            }
        }
        
        return -1;
    }
}

class Node{
    int x,y,value;
    
    public Node(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}