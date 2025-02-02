import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        
        String line = br.readLine();
        
        int[] minCounts = new int[4];
        int[] counts = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++){
            minCounts[i] = Integer.parseInt(st.nextToken());
        }
        
        int result = 0;
        for (int i = 0; i < s; i++){
            counts[findIndex(line.charAt(i))]++;
            
            if (i < p-1) {
                continue;
            }
            
            if (isSecure(counts, minCounts)) result++;
            counts[findIndex(line.charAt(i-(p-1)))]--;
        }
        
        System.out.println(result);
    }
    
    static int findIndex(char x){
        if (x == 'A') return 0;
        else if (x == 'C') return 1;
        else if (x == 'G') return 2;
        else return 3;
    }
    
    static boolean isSecure(int[] counts, int[] minCounts){
        for (int i = 0; i < 4; i++){
            if (counts[i] < minCounts[i]) return false;
        }
        return true;
    }
}
