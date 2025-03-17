import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        // 관계 추가용 리스트 추가
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        // 관계 추가
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            graph.get(parent).add(new Node(child, 0));
            graph.get(child).add(new Node(parent, 0));
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start,0));
        
        int result = -1;
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        while(!queue.isEmpty()){
            Node now= queue.poll();
            
            if (now.num == end){
                result = now.depth;
                break;
            }
            for (Node nxt : graph.get(now.num)){
                if (!visited[nxt.num]){
                    visited[nxt.num] = true;
                    queue.add(new Node(nxt.num, now.depth + 1));
                }
            }
        }
        
        System.out.println(result);
    }
    
    private static class Node {
        int num;
        int depth;
        public Node(int num, int depth){
            this.num = num;
            this.depth = depth;
        }
    }
}
