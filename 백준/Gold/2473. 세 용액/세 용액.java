import java.io.*;
import java.util.*;

public class Main {
    private static long[] nums;
    private static long diff = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        nums = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(nums);

        long[] result = logic(n);
        Arrays.sort(result);

        for (int i = 0; i < 3; i++) {
            bw.write(result[i] +" ");
        }
        bw.flush();
        bw.close();
    }

    private static long[] logic(int n) {
        long[] result = new long[3];
        for (int i = 0; i < n - 2; i++) {
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                long logicDiff = nums[start] + nums[end] + nums[i];
                long absDiff = Math.abs(logicDiff);

                if (absDiff < diff) {
                    result[0] = nums[start];
                    result[1] = nums[i];
                    result[2] = nums[end];
                    diff = absDiff;
                }

                if (logicDiff > 0) {
                    end--;
                } else if (logicDiff < 0) {
                    start++;
                } else{
                    return result;
                }
            }
        }
        return result;
    }

}