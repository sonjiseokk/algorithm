import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] activeAppMemories = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            activeAppMemories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] costs = new int[n + 1];
        int maxCost = 0;
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            maxCost += costs[i];
        }

        // dp 로직
        int[][] dp = new int[n + 1][maxCost + 1];
        for (int app = 1; app <= n; app++) {
            for (int cost = 0; cost <= maxCost; cost++) {
                if (cost >= costs[app]) {
                    dp[app][cost] = Math.max(
                            dp[app - 1][cost],
                            dp[app - 1][cost - costs[app]] + activeAppMemories[app]
                    );
                } else{
                    dp[app][cost] = dp[app - 1][cost];
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= maxCost; i++) {
            if (dp[n][i] >= m) {
                result = i;
                break;
            }
        }

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}