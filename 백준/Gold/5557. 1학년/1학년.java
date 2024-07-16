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

        long[][] dp = new long[n][21];
        dp[0][nums[0]] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 21; j++) {
                if (nums[i] + j < 21) {
                    dp[i][nums[i] + j] += dp[i - 1][j];
                }
                if (j - nums[i] >= 0) {
                    dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        }

        bw.write(String.valueOf(dp[n - 2][nums[n - 1]]));


        bw.flush();
        bw.close();
    }
}