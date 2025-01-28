import java.io.*;
import java.util.*;

public class Main {
    static int[] nums,results;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        nums = new int[n];
        visited = new boolean[n];
        results = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i ++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(nums);
        
        rec(n,m,0);
        
        System.out.print(sb.toString());
    }
    
    static void rec(int n, int m, int depth){
        if (depth == m){
            for (int num : results){
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = 0; i< n; i++){
            if (!visited[i]){
                visited[i] = true;
                results[depth] = nums[i];
                rec(n,m,depth+1);
                visited[i] = false;
            }

        }
    }
} 