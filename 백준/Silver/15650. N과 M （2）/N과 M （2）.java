import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] nums;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        nums = new int[m];        
        rec(0, 1,n,m);
    }
    
    static void rec(int depth, int start, int n, int m){
        if (depth == m) {
            for (int num : nums){
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }
        
        for (int i = start; i <= n; i++){
            if (!visited[i]){
                visited[i] = true;
                nums[depth] = i;
                rec(depth+1, i, n, m);
                visited[i] = false;  
            }

            
        }
    }
}
