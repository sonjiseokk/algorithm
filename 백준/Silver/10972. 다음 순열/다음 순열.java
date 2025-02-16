import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[] nums = new int[n];
        StringTokenizer st=  new StringTokenizer(br.readLine());
        for (int i = 0; i< n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        // 1. 로직
        boolean isEnd = np(nums, n);
        
        StringBuilder sb = new StringBuilder();
        if (isEnd) sb.append("-1");
        else{ 
            for (int i = 0; i < n; i++){
                sb.append(nums[i]).append(" ");
            }
        }

        System.out.println(sb.toString());
    }
    
    static boolean np(int[] nums, int n){
        // 1. 지역 최고점 찾기
        int i = n - 1;
        while (i > 0 && nums[i-1] >= nums[i]) --i;
        
        // 2. 다음 순열이 없는 경우 처리
        if (i == 0) return true;
        
        // 3. 교환 위치 찾기
        int j = n - 1;
        while (nums[i-1] >= nums[j]) --j;
        
        // 4. 교환 로직 수행
        swap(nums, i-1, j);
        
        // 5. i부터 맨 뒤까지 리버스 수행
        int k = n-1;
        while (k > i) swap(nums, i++, k--);
        
        return false;
    }
    
    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
