import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 계단의 개수를 입력받는다
        int n = Integer.parseInt(br.readLine());
        
        // 각 계단의 점수를 입력받는다
        int[] arr = new int[n+1];
        for(int i =1; i< n+1; i++){
            arr[i] = Integer.parseInt(br.readLine());    
        }
        
        // 엣지 케이스 
        // 계단의 개수가 1개인 경우
        
        // 계단의 개수가 2개인 경우
        
        // 첫번째 칸과 두번째 칸의 최대값은 정해져 있음
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = arr[1];
        if (n > 1){
            dp[2] = arr[1] + arr[2];
        }
        
        // 1칸씩 이동이 가능하다
        // 1칸 씩 하되 3칸 연속은 불가능하다
        for(int i = 3; i<n+1; i++){
            // 두칸 넘어서 오는 경우
            int first = dp[i-2] + arr[i];
            // 한칸씩 넘어오는 경우 (세칸 넘어서 오는 경우를 방지) 
            int second = arr[i] + arr[i-1] + dp[i-3];
            dp[i] = Math.max(first, second);
        }
        
        System.out.println(dp[n]);
        
    }
}
