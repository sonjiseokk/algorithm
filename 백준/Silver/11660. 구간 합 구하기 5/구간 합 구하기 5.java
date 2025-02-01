import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] nums = new int[n][n];
        int[][] psums = new int[n+1][n+1];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                nums[i][j] = Integer.parseInt(st.nextToken());
                psums[i+1][j+1] = psums[i][j+1] + (psums[i+1][j] - psums[i][j]) + nums[i][j];
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            
            int result = psums[endY][endX] - psums[endY][startX-1] - psums[startY-1][endX] + psums[startY-1][startX-1];
            sb.append(result).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}
