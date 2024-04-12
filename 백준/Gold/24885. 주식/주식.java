import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static long seed, leverage;
    private static long[] values;
    private static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        seed = Long.parseLong(st.nextToken());
        leverage = Long.parseLong(st.nextToken());
        values = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }

        res = seed; // 초기 자본을 최대 수익으로 설정
        dfs(0, seed, 0);

        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth, long now, long debt) {
        res = Math.max(res, now);
        now -= debt; // 현재 돈에서 대출금을 뺀다.
        if (depth == n - 1) return; // 마지막 날에 도달했다면 리턴

        // 아무것도 안하는 경우
        dfs(depth + 1, now, 0);

        long p = now * leverage; // 대출 가능한 금액
        if (now + p < values[depth]) return; // 대출 후에도 주식을 살 수 없다면 리턴
        now += p; // 대출금을 현재 돈에 추가

        for (int i = depth + 1; i < n; i++) {
            // i일에 매수 후 매도하는 경우, 수익 계산 및 재귀 호출
            dfs(i, (values[i] - values[depth]) * (now / values[depth]) + now, p);
        }
    }
}