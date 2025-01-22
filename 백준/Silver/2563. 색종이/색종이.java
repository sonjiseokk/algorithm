import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        // 색종이 원래 크기
        boolean[][] map = new boolean[101][101];
        
        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            makeLec(map, x,y);
        }
        
        int cnt = 0;
        for (int i = 0; i<101; i++){
            for(int j =0; j <101; j++){
                if(map[i][j]) cnt ++;
            }
        }
        
        System.out.println(cnt);
    }
    
    private static void makeLec(boolean[][] map, int x, int y){
        for(int i =0; i<10; i++){
            for(int j =0; j<10; j++){
                map[y+i][x+j] = true;
            }
        }
    }
}
