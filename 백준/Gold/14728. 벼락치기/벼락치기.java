import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] questions = new int[n][2];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            questions[i][0] = Integer.parseInt(st.nextToken());
            questions[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // ------------------
        
        int[][] dp = new int[n+1][t+1];
        
        for (int i = 1; i <= n; i++){
            int k = questions[i-1][0];
            int s = questions[i-1][1];
            for (int j = 0; j <= t; j++){
                // 배낭에 넣을 수 있는 크기인 경우
                if (j >= k) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-k] + s);
                } else{
                    // 배낭에 넣을 수 없는 경우
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i <= t; i++){
            res = Math.max(dp[n][i], res);
        }
        System.out.println(res);
    }
}
