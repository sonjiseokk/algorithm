import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        
        Node[] nodes = new Node[n+1];
        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(time, score);
        }
        
        // ------------------
        
        int[][] dp = new int[n+1][t+1];
        
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= t; j++){
                // 선택한 노드
                Node now = nodes[i];
                // 아직 노드의 시간보다 작은 경우
                if (now.time > j){
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 노드의 시간인 경우
                    // 해당 노드를 선택하는 것과 선택하지 않은 것중 더 큰것을 선택
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - now.time] + now.score);
                }
            }
        }
        
        // 마지막줄에서 제일 큰 애 선택
        int res = 0;
        for (int i = 0; i <= t; i++){
            res = Math.max(res, dp[n][i]);
        }
        
        System.out.println(res);
    }
    
    static class Node {
        int time;
        int score;
        public Node(int time, int score){
            this.time = time;
            this.score = score;
        }
    }
}
