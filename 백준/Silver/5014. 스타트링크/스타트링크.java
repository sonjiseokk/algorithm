import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        // -----------------------
        
        boolean[] visited = new boolean[f+1];
        
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(s,0));
        
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            Step now = queue.poll();
            
            // 기저조건
            if (now.floor == g){
                res = now.depth;
                break;
            }
            if (now.floor > f || now.floor <= 0) continue;
            if (visited[now.floor]){
                continue;
            }
            
            visited[now.floor] = true;
            queue.add(new Step(now.floor + u, now.depth + 1));
            queue.add(new Step(now.floor - d, now.depth + 1));
        }
        
        System.out.println(res == Integer.MAX_VALUE ? "use the stairs" : res);
    }
    
    static class Step {
        int floor;
        int depth;
        public Step(int floor, int depth){
            this.floor = floor;
            this.depth = depth;
        }
    }
}
