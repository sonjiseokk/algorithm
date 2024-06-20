import java.io.*;
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
            int index = binarySearch(0, length - 1, nums[i], dp);

            dp[index] = nums[i];
            if (length == index) {
                length++;
            }
        }

        bw.write(String.valueOf(length));
        bw.flush();
        bw.close();
    }

    private static int binarySearch(int left, int right, int target, final int[] dp) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (dp[mid] < target) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return left;
    }
}