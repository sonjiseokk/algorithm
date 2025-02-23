import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        
        int max = 0;
        int[] lans = new int[k];
        for (int i = 0; i <k; i++){
            lans[i] = Integer.parseInt(br.readLine());
            max= Math.max(max,lans[i]);
        }
        
        long start = 1;
        long end = max;

        while (start <= end){
            // mid값 계산
            long mid = (long) (end + start) / 2;
            
            // 모든 랜선들을 mid 값으로 나누어봄
            int count = 0;
            for (int i = 0; i < k; i ++){
                count += (lans[i] / mid);
            }
            
            // 만약 나눈 값이 n보다 작으면 더 작은 크기로 짤라야함
            if (count < n){
                end = mid - 1;
            } else {
                // n보다 크거나 같다면 좀 더 크게 잘라서 최대 랜선의 크기를 구함
                start = mid+1;
            }
        }
        
        // while을 빠져나온 순간부터
        // end가 최대 랜선의 크기일것임
        System.out.println(end);
    }
}
