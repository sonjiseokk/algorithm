import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[][] ingres = new int[n][2];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ingres[i][0] = Integer.parseInt(st.nextToken());
            ingres[i][1] = Integer.parseInt(st.nextToken());
        }
        
        // 비트마스킹
        int result = Integer.MAX_VALUE;
        for (int mask = 1; mask < (1 << n); mask++){
            int sin = 1;
            int tuen = 0;
            
            for (int i = 0; i<n; i++){
                if ((mask & (1 << i)) != 0){
                    sin *= ingres[i][0];
                    tuen += ingres[i][1];
                }
            }
            
            result = Math.min(result, Math.abs(sin - tuen));
        }
        
        System.out.print(result);
    }
}
