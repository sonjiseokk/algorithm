import java.io.*;
import java.util.*;
public class Main {
    private static int blue, white;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[][] map = new int[n][n];
        for (int i = 0; i <n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j =0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        recursive(map, 0,0,n);
        
        System.out.println(white);
        System.out.println(blue);
    }
    
    private static void recursive(int[][] map, int x, int y, int n){
        int blueCnt = 0;
        for (int i = y; i < y + n; i++){
            for (int j = x; j < x + n; j++){
                if(map[i][j] == 1) blueCnt++;
            }
        }
        
        if (blueCnt == n * n){
            blue++;
        } else if (blueCnt == 0){
            white++;
        } else{
            recursive(map, x, y, n / 2);
            recursive(map, x + (n /2), y, n/2);
            recursive(map, x, y + n/2, n/2);
            recursive(map, x+(n/2), y+(n/2), n/2);
        }
    }
}
