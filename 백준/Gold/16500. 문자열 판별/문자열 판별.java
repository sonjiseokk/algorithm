import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        
        int n = Integer.parseInt(br.readLine());
        Set<String> words = new HashSet<>();
        for (int i = 0; i < n; i++){
            words.add(br.readLine());
        }
        
        // ---------------------
        int size = s.length();
        int[] dp = new int[size+1];
        
        // 빈 문자열은 만들 수 있음
        dp[size] = 1;
        
        // 거꾸로 탐색
        for (int i = size - 1; i >= 0; i--){
            // i -> 끝에서부터 처음으로 이동
            // j -> i에서부터 끝까지 이동하면서 단어 찾음
            for (int j = i + 1; j <= size; j++){
                String word = s.substring(i,j);
                
                // 만약 짤라내서 만든 워드가 있는 워드인 경우
                if (words.contains(word) && dp[j] == 1){
                    dp[i] = 1;
                    break;
                }
            }
        }
        
        System.out.println(dp[0]);
        
    }
}
