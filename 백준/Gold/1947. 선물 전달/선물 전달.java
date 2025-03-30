import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 0;
        if (n > 1) dp[2] = 1;
        
        for (int i = 3; i <= n; i++){
            dp[i] = (i-1) * (dp[i-1] + dp[i-2]) % 10_0000_0000;
        }
        
        System.out.println(dp[n]);
    }
}
