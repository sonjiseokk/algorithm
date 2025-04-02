import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            
            // 상근이네 집
            Node[] nodes = new Node[n+2];
            nodes[0] = new Node(sx,sy);
            
            // 편의점 등록
            for (int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                
                nodes[i+1] = new Node(x,y); 
            }
            
            // 락페스티벌 등록
            st = new StringTokenizer(br.readLine());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());
            nodes[n+1] = new Node(rx,ry);
            
            // -------------------
            
            // 상근이네 집 방문 체크
            boolean[] visited = new boolean[n+2];
            visited[0] = true;
            
            // 인덱스 기준 큐에 추가
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            
            boolean isEnable = false;
            while (!queue.isEmpty()){
                int nowIdx = queue.poll();
                Node now = nodes[nowIdx];
                
                // 기저 조건
                if (now.x == rx && now.y == ry){
                    isEnable = true;
                    break;
                }
                
                // 모든 편의점 탐색
                for (int i = 0; i < n+2; i++){
                    if (!visited[i]){
                        Node nxt = nodes[i];
                        int distance = Math.abs(now.x - nxt.x) + Math.abs(now.y - nxt.y);
                        if (distance <= 1000){
                            queue.add(i);
                            visited[i] = true;
                        }
                    }
                }
            }
            
            System.out.println(isEnable ? "happy" : "sad");
        }
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
