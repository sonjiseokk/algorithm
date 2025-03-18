import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited; 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        
        // 관계 저장할 리스트 추가
        for (int i = 0; i<= n; i++){
            graph.add(new ArrayList<>());
        }
        
        visited = new boolean[n+1];
        
        // 관계 입력
        for (int j = 0; j<m; j++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        // 관계 오름차순 정렬
        for (int i = 0; i<= n; i++){
            Collections.sort(graph.get(i));
        }
        
        System.out.print(v + " ");
        dfs(v);
        
        visited = new boolean[n+1];
        System.out.println();
        
        bfs(v);
    }
    
    static void dfs(int now){
        visited[now] = true;
        for (int nxt : graph.get(now)){
            if (!visited[nxt]){
                System.out.print(nxt + " ");
                visited[nxt] = true;
                dfs(nxt);
            }
        }
    }
    
    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){
            int now = queue.poll();
            
            System.out.print(now + " ");
            
            for (int nxt : graph.get(now)){
                if (!visited[nxt]){
                    visited[nxt] = true;
                    queue.add(nxt);
                }
            }
        }
    }
    
}
