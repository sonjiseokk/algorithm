import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while ((line = br.readLine()) != null) {
            int x = Integer.parseInt(line.trim());
            int n = Integer.parseInt(br.readLine().trim());

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(br.readLine().trim());
            }

            Arrays.sort(nums);
            int l1 = 0;
            int l2 = 0;
            int start = 0, end = n - 1;

            while (start < end) {
                long sum = (long) nums[start] + nums[end];
                if (sum == x * 10000000L) {
                    l1 = nums[start];
                    l2 = nums[end];
                    break;
                } else if (sum < x * 10000000L) {
                    start++;
                } else {
                    end--;
                }
            }

            if (l1 == 0 && l2 == 0) {
                System.out.println("danger");
            } else {
                System.out.println("yes " + l1 + " " + l2);
            }
        }
    }
}
