import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int length = 0;
        for (int i = 0; i < n; i++) {
            int index = Arrays.binarySearch(dp, 0, length, nums[i]);
            if (index < 0) {
                index = -index - 1;
            }
            dp[index] = nums[i];
            if (index == length) {
                length++;
            }
        }

        bw.write(String.valueOf(length));

        bw.flush();
        bw.close();
    }
}