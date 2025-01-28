import java.io.*;
import java.util.*;

public class Main {
    static int[] nums, results;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        results = new int[m];
        nums = new int[n];
        visited = new boolean[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i< n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(nums);
        
        rec(0, n, m, 0);
        
        System.out.print(sb.toString());
    }
    
    static void rec(int depth, int n, int m, int start){
        if (depth == m){
            for (int result : results){
                sb.append(result).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = start; i < n; i++){
            if (!visited[i] || i == start){
                visited[i] = true;
                results[depth] = nums[i];
                rec(depth + 1, n, m, i);
                visited[i] = false;
            }
        }
        
    }
}
