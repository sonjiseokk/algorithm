import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        Map<Integer, String> indexPockets = new HashMap<>();
        Map<String, Integer> namePockets = new HashMap<>();
        
        for (int i = 0; i<n; i++){
            String name = br.readLine();
            indexPockets.put(i+1, name);
            namePockets.put(name,i+1);
        }
        
        for (int i = 0; i<m; i++){
            String question = br.readLine();
            try {
                int index = Integer.parseInt(question);
                sb.append(indexPockets.get(index)).append("\n");
            } catch (Exception e) {
                sb.append(namePockets.get(question)).append("\n");
            }
        }
        
        System.out.print(sb.toString());
    }
}
