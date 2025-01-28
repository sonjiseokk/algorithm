import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        nums = new int[m];

        rec(1, 0, n, m);  

        
        System.out.print(sb.toString());
        
    }
    
    static void rec(int start, int depth, int n, int m){
        if(depth == m){
            for (int num : nums){
                sb.append(num +" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = start; i <= n; i ++){
            nums[depth] = i;
            rec(i, depth+1, n , m);
        }
    }
}
