import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean isEnd = pp(nums,n);
        
        if (isEnd) sb.append("-1");
        else{
            for (int i = 0; i<n; i++){
                sb.append(nums[i]).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
    
    
    static boolean pp(int[] nums, int n){
        // 1. 지역 최고점을 찾자
        int i = n-1;
        while (i > 0 && nums[i-1] <= nums[i]) --i;
        
        // 2. 첫번째면 끝내자
        if (i == 0) return true;
        
        // 3. i부터 끝까지 돌면서 i-1보다 큰 자식 중 가장 큰 자식을 찾자
        int j = n-1;
        while(nums[i-1] <= nums[j]) --j;
        
        // 4. i-1, j 체인지
        swap(nums, i-1,j);
        
        // 5. i부터 끝까지 리버스
        int k = n-1;
        while(k > i) swap(nums,i++,k--);
        
        return false;
    }
    
    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
