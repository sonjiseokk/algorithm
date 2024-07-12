
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static final int RIGHT = 0;
	private static final int DOWN = 1;
	private static final int DIAGONAL = 2;

	private static long[][] map;
	private static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		map = new long[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[1][0] = 1;
		map[1][1] = 1;

		dp = new long[n+1][n+1][3];

		dp[1][2][RIGHT] = 1;
		for (int i = 3; i <= n; i++) {
		    if (map[1][i] == 1) break;
		    dp[1][i][RIGHT] = dp[1][i-1][RIGHT];
		}

		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= n; j++) {
				if (map[i][j] == 0 && map[i-1][j] == 0 && map[i][j-1] == 0) {
					dp[i][j][DIAGONAL] = (dp[i-1][j-1][RIGHT] + dp[i-1][j-1][DOWN] + dp[i-1][j-1][DIAGONAL]);
				}

				if (map[i][j] == 0) {
					dp[i][j][RIGHT] = dp[i][j-1][DIAGONAL] + dp[i][j-1][RIGHT];
					dp[i][j][DOWN] = dp[i-1][j][DIAGONAL] + dp[i-1][j][DOWN];
				}

			}
		}

		long res = 0;
		for (int i = 0; i < 3; i++) {
			res += dp[n][n][i];
		}
		bw.write(String.valueOf(res));

		bw.flush();
		bw.close();
	}

}

