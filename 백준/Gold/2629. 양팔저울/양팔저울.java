import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int sum = Arrays.stream(weights).sum();
        boolean[] dp = new boolean[40001];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int now = weights[i];

            // 상태가 업데이트 되면서 반복문을 이상하게 돌게 되므로 임시 배열
            boolean[] temp = new boolean[40001];
            for (int j = sum; j >= 0; j--) {
                if (dp[j]) {
                    if (j + now <= sum) {
                        temp[j + now] = true;
                    }
                    temp[Math.abs(j - now)] = true;
                }
            }

            for (int j = 0; j <= sum; j++) {
                if (temp[j]) {
                    dp[j] = true;
                }
            }
        }


        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int test = Integer.parseInt(st.nextToken());
            if (dp[test]) {
                bw.write("Y ");
            } else {
                bw.write("N ");
            }
        }

        bw.flush();
        bw.close();
    }
}