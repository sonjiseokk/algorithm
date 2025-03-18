import java.io.*;
import java.util.*;

public class Main {
    static int [][][] dp;
    static final int GARO = 0;
    static final int SERO = 1;
    static final int DAEGAK = 2;
    static int[][] board;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i< n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());   
            }
        }
        
        dp = new int[n][n][3];
        
        // 첫줄 초기화
        dp[0][1][GARO] = 1;
        for (int i = 2; i < n; i++){
            if (board[0][i] == 1) break;
            dp[0][i][GARO] = 1;
        }
        
        // 두번째 줄부터
        for (int i = 1; i < n; i++){
            for (int j = 1; j < n; j ++) {
                // 대각 이동
                if (board[i][j] == 0 && board[i-1][j] == 0 && board[i][j-1] == 0) {
                    // 대각으로 오는 경우
                    dp[i][j][DAEGAK] = dp[i-1][j-1][DAEGAK] + dp[i-1][j-1][SERO] + dp[i-1][j-1][GARO];
                }
                
                // 가로 세로 이동
                if (board[i][j] == 0){
                    dp[i][j][SERO] = dp[i-1][j][SERO] + dp[i-1][j][DAEGAK];
                    dp[i][j][GARO] = dp[i][j-1][GARO] + dp[i][j-1][DAEGAK];
                }
            }
        }
        
        System.out.println(dp[n-1][n-1][GARO] + dp[n-1][n-1][SERO] + dp[n-1][n-1][DAEGAK]);
    }
}
