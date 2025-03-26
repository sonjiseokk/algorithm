import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t<T; t++){
            int n = Integer.parseInt(br.readLine());
            
            long[] dp = new long[n+1];
            dp[0] = 1;
            if (n>=1) dp[1] = 0;
            if (n >= 2) dp[2] = 1;
            for (int i = 3; i <= n; i++){
                // a,b가 자리를 바꿈 -> 나머지 n-2에 대한 dp
                // a,b 가 자리를 바꾸지 않음 -> 나머지 n-1에대한 dp
                dp[i] = (i - 1) * (dp[i-2] + dp[i-1]);
            }
            
            System.out.println(dp[n]);
        }
    }
}
