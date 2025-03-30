import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        // -----------------
        long[][] dp = new long[n+1][k+1];
        
        // 1개인데 n이 되는 경우의 수는 항상 1
        for (int i = 0; i <= n; i++){
            dp[i][1] = 1;
        }
        
        // 0이 되는 경우의수는 항상 1개 (0만 사용)
        for (int i = 0; i <= k; i++){
            dp[0][i] = 1;
        }
        
        for (int i = 1; i <= n; i++){
            for (int j = 2; j <=k; j++){
                // n에서 1을 안더한 경우의 수
                // 0을 안넣은 경우의 수
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }       
        
        System.out.println(dp[n][k]);


        
    }
}
