import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = st.countTokens() - 1;
        int[] moves = new int[n];
        for (int i = 0; i < n; i++) {
            moves[i] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[n + 1][5][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        dp[0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            int move = moves[i];
            // 왼쪽 무빙의 모든것들 (5개인 이유는 1,2,3,4라서)
            for (int l = 0; l < 5; l++) {
                // 오른쪽 무빙의 모든것들
                for (int r = 0; r < 5; r++) {
                    if (dp[i][l][r] == Integer.MAX_VALUE) continue;

                    // 왼발 이동
                    dp[i + 1][move][r] = Math.min(dp[i + 1][move][r], dp[i][l][r] + moveCost(l, move));

                    // 오른발 이동
                    dp[i + 1][l][move] = Math.min(dp[i + 1][l][move], dp[i][l][r] + moveCost(r, move));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int l = 0; l < 5; l++) {
            for (int r = 0; r < 5; r++) {
                result = Math.min(result, dp[n][l][r]);
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    private static int moveCost(int from, int to) {
        if (from == to) return 1; // same position
        if (from == 0) return 2;  // from the center
        if (Math.abs(from - to) == 2) return 4; // opposite positions
        return 3; // adjacent positions
    }
}