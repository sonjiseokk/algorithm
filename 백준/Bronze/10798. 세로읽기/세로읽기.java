import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[][] map = new char[5][15];
        for(int i =0; i< 5; i++){
            String line = br.readLine();
            for(int j = 0; j < line.length(); j++){
                map[i][j] = line.charAt(j);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < 15; j++){
            for(int i = 0; i < 5; i++){
                if (map[i][j] != 0) sb.append(map[i][j]);
            }
        }
        
        System.out.println(sb.toString());
    }
}
