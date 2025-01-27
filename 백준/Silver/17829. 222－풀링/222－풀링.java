import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[][] map = new int[n][n];
        for (int i = 0; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = rec(map, n, 0, 0);
        System.out.println(result);
    }
    
    private static int rec(int[][] map, int n, int x, int y){
        int[] temp = new int[4];
        int maxValue = 0;
        // 분할하는 경우
        if (n != 2){
            temp[0] = rec(map,n/2, x, y);
            temp[1] = rec(map, n/2, x + n/2,y);
            temp[2] = rec(map,n/2, x, y +n/2);
            temp[3] = rec(map,n/2, x + n/2, y + n/2);
        } else {
            int inx = 0;
            for (int i = y; i < y + 2; i++){
                for (int j = x; j < x + 2; j++){
                    temp[inx++] = map[i][j];
                }
            }
        }
        
        Arrays.sort(temp);
        
        return temp[2];
    }
}
