import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // a~z까지 배열 만들기

        int n = Integer.parseInt(br.readLine());
        
        int count = 0;
        for (int i = 0; i < n; i++){
            // 입력받고
            char[] line = br.readLine().toCharArray();
            int[] alphabets = new int[26];
        
            boolean isGroupWord = true;
            for (int j = 1; j < line.length; j ++){
                if (line[j-1] != line[j]) {
                    if (alphabets[line[j] - 'a'] == 1) {
                        isGroupWord = false;
                        break;
                    }
                }
                alphabets[line[j - 1] - 'a'] = 1;
            }
            
            if (isGroupWord) count++;

        }
        // 다음이 같은 거면 배열 수정 x
        
        System.out.println(count);
        
    }
}
