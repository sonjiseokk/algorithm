import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 1 인덱스를 사용할거임
        int[] memoryUsages = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            memoryUsages[i] = Integer.parseInt(st.nextToken());
        }

        // 여기도 1 인덱스를 사용할거임
        // max cost가 조건상 천만이긴 하나 최적화를 위해 그냥 계산해줌
        int maxCost = 0;
        st = new StringTokenizer(br.readLine());
        int[] costs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            maxCost += costs[i];
        }

        // 가로는 코스트 (0 ~ 전체코스트만큼)
        // 세로는 앱1, 앱2 등
        int[][] dp = new int[n + 1][maxCost + 1];

        // 1부터 시작하는 이유는 1 인덱스를 사용할 것이기 때문
        // 점화식이 [i][cost] = [i-1][cost] 로 활용되기 때문에 1 인덱스를  사용해주는 것이 간편
        for (int app = 1; app <= n; app++) {
            for (int cost = 0; cost <= maxCost; cost++) {
                // 현재 코스트가 선택된 앱의 코스트 보다 크거나 같을때 (즉 앱을 선택할 수 있을때)
                if (cost >= costs[app]) {
                    // 선택하지 않은 경우와 (선택할 수 있을 때 + 현재 사용량) 중 큰 값 저장
                    dp[app][cost] = Math.max(
                            dp[app - 1][cost],
                            dp[app - 1][cost - costs[app]] + memoryUsages[app]
                    );
                } else{
                    dp[app][cost] = dp[app - 1][cost];
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= maxCost; i++) {
            // n이 선택된 이유는 문제 조건상 "모든 앱을 고려해야함"
            // 따라서 n까지 진행되어봐야 알 수 있다.

            // m보다 크거나 같을때는 이것으로 설명됨
            // dp 연산을 통해 모든 앱을 고려했을 때
            // 특정 cost에서 가장 큰 사용량을 가진다 == 몇 개를 비활성화 했을 때 가장 큰 사용량을 가진다
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