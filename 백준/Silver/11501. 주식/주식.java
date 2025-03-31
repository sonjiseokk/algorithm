import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // T 입력
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            // N 입력
            int n = Integer.parseInt(br.readLine());
            
            // 자연수 입력
            long[] nums = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++){
                nums[i] = Long.parseLong(st.nextToken());
            }
            
            
            // ------------------
            
            long res = 0;
            long maxPrice = 0;
            for (int i = n-1; i >= 0; i--){
                if (nums[i] > maxPrice){
                    maxPrice = nums[i];
                }
                res += (maxPrice - nums[i]);    
            }

            System.out.println(res);
            
        }
    }
    
    
}
