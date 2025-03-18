import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        // 관계 넣을 리스트 추가
        for (int i = 0; i<n; i++){
            graph.add(new ArrayList<>());
        }
        
        // 관계 설정
        for (int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        // 방문 배열 초기화
        visited = new boolean[n];
        
        // n은 매우 많음. 모두가 연결 안되어있을 수도 있음
        // 따라서 n-1까지 전부 시도
        boolean endFlag = false;
        for (int i = 0; i < n; i++){
            if (dfs(n, i,0)){
                endFlag = true;
                break;
            }
        }
        
        if (endFlag) System.out.println(1);
        else System.out.println(0);
    }
    
    static boolean dfs(int n, int node, int depth){
        // 5명이 알고있는 관계라면 
        if (depth == 4) {
            return true;
        }
        
        visited[node] = true;
        for (int nxt : graph.get(node)){
            if (!visited[nxt]){
                if (dfs(n, nxt, depth + 1)) return true;
            }
            
        }
        visited[node] = false;
        return false;
    }

}
