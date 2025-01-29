import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static int[] results, nums;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true){
            String line = br.readLine();
            if (line.equals("0")) break;
            
            sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(line);
            
            int n = Integer.parseInt(st.nextToken());
            nums = new int[n];
            results = new int[6];
            visited = new boolean[n];
            
            for (int i = 0; i<n; i++){
                nums[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(nums);
            
            rec(0, n, 0);
            sb.append("\n");
            System.out.print(sb.toString());
        }
    }
    
    static void rec(int depth, int n, int start){
        if (depth == 6){
            for (int result : results){
                sb.append(result).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        int prev = 0;
        for (int i = start; i < n; i++){
            if (!visited[i] && prev != nums[i]){
                visited[i] = true;
                results[depth] = nums[i];
                prev = nums[i];
                rec(depth+1, n, i+1);
                visited[i] = false;
            }
        }
    }
}
