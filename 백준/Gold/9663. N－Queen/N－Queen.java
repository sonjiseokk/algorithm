import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int[] col;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        board = new int[n][n];
        col = new int[n];
        
        int result = dfs(0);
        System.out.println(result);
    }
    
    /**
    col[?] = x;
    : ?번째 행에서 x열에 퀸이 있다.
    */
    static int dfs(int depth){
        // 종료 함수
        if (depth == n) return 1;
        
        
        int count = 0;
        for (int x = 0; x<n; x++){
            boolean isValid = true;
        
            for (int i = 0; i < depth; i++){
                // 세로 검사
                if (col[i] == x) {
                    isValid = false;
                    break;
                }
                // 대각선 검사
                if (Math.abs(depth - i) == Math.abs(x - col[i])) {
                    isValid = false;
                    break;
                }
            }

            
            if (isValid) {
                // 해당 열에서 x 행에 퀸을 둠
                col[depth] = x;
            
                // 백트래킹 돌며 depth가 n이된 모든 케이스 카운트
                count += dfs(depth + 1);
            }
        }
        return count;
        
    }
}
