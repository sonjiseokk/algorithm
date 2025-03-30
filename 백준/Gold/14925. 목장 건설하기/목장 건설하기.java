import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[n][m];
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0){
                    dp[i][j] = 1;
                } else if (board[i][j] == 1 || board[i][j] == 2){
                    dp[i][j] = 0;
                }
            }
        }
        
        //----------------------
        
        int res = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (i > 0 && j > 0 && board[i][j] == 0){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                res = Math.max(dp[i][j], res);            
            }
        }
        
        System.out.println(res);
    }
}
