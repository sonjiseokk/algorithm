import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[n];
        int[] psum = new int[n+1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
            psum[i+1] = psum[i] + nums[i];
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            sb.append(psum[end] - psum[start - 1]).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}
