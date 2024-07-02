import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    private static int n;
    private static int[][] costs;
    private static int[][][] dp;
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        costs = new int[n][3];
        dp = new int[3][n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            initDp();

            dp[firstColor][0][firstColor] = costs[0][firstColor];
            for (int next = 1; next < n; next++) {
                dp[firstColor][next][RED] =
                        Math.min(dp[firstColor][next - 1][BLUE], dp[firstColor][next - 1][GREEN]) + costs[next][RED];
                dp[firstColor][next][GREEN] =
                        Math.min(dp[firstColor][next - 1][RED], dp[firstColor][next - 1][BLUE]) + costs[next][GREEN];
                dp[firstColor][next][BLUE] =
                        Math.min(dp[firstColor][next - 1][GREEN], dp[firstColor][next - 1][RED]) + costs[next][BLUE];
            }

            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (firstColor != lastColor) {
                    result = Math.min(result, dp[firstColor][n - 1][lastColor]);
                }
            }
        }

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }

    private static void initDp() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = 1001;
                }
            }
        }
    }
}