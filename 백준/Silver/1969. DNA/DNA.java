import java.io.*;
import java.util.*;

public class Main {
    static String[] dic = {"A", "C", "G", "T"};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        String[] dnas = new String[n];
        for (int i = 0; i < n; i++){
            dnas[i] = br.readLine();
        }
        
        // ------------------
        
        // 제일 많이 선택된 뉴클레오타이드 선택
        // 만약 같으면 사전순으로 작은거 선택
        
        StringBuilder sb = new StringBuilder();
        int res = 0;
        for (int i = 0; i < m; i++){
            // A T C G
            int[] select = new int[4];
            for (int j = 0; j < n; j++){
                String compare = dnas[j];
                if (compare.charAt(i) == 'A') select[0]++;
                else if (compare.charAt(i) == 'C') select[1]++;
                else if (compare.charAt(i) == 'G') select[2]++;
                else select[3]++;
            }
            
            // 더 많이 선택된 뉴클레오타이드 선택
            int cnt = 0;
            int idx = 0;
            for (int p = 0; p < 4; p++){
                if (cnt < select[p]){
                    idx = p;
                    cnt = select[p];
                }
            }
            
            // 선택된거 말고 다른애 인 경우 거리 늘어남
            for (int p = 0; p < 4; p++){
                if (p != idx){
                    res += select[p];
                }
            }
            
            sb.append(dic[idx]);
        }
        

        System.out.println(sb.toString());
                System.out.println(res);
    }
}
