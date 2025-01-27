import java.io.*;
import java.util.*;

public class Main {
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        
        rec(n, x, y);
        System.out.println(result);
    }
    
    private static void rec(int n, int x, int y){
        if (n == 0) return;
        int half = (int) Math.pow(2, n - 1);
        int stage = 0;
        
        if (y >= half){
            stage += 2;
            y -= half;
        }
        if (x >= half){
            stage += 1;
            x -= half;
        }
        
        result += stage * (half * half);
        
        rec(n - 1, x , y);
        
    }
}
