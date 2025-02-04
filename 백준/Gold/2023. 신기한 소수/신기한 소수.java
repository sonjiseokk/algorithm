import java.io.*;
import java.util.*;

public class Main {
    static int[] sosus = new int[]{2,3,5,7};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        for (int sosu : sosus){
            backtrack(0, n, sosu);    
        }
        
        
        System.out.print(sb.toString());
    }
    
    static void backtrack(int depth, int n, int result){
        if (!isPrime(result)){
            return;
        }
        
        if (depth == n-1){
            sb.append(result).append("\n");
            return;
        }
        
        for (int i = 1; i < 10; i+=2){
            int temp = result * 10 + i;
            backtrack(depth + 1, n, temp); 
        }
    }
    
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
