import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long m, k;
    static long[] arr;
    static long anss;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());
        arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        anss = m; // 초기 자본을 최대 수익으로 설정
        ans(0, m, 0);
        System.out.println(anss);
    }

    static void ans(int da, long now, long mmm) {
        anss = Math.max(anss, now);
        now -= mmm; // 현재 돈에서 대출금(mmm)을 뺀다.
        if (da == n - 1) return; // 마지막 날에 도달했다면 리턴

        // 아무것도 안하는 경우
        ans(da + 1, now, 0);

        long p = now * k; // 대출 가능한 금액
        if (now + p < arr[da]) return; // 대출 후에도 주식을 살 수 없다면 리턴
        now += p; // 대출금을 현재 돈에 추가

        for (int i = da + 1; i < n; i++) {
            // i일에 매수 후 매도하는 경우, 수익 계산 및 재귀 호출
            ans(i, (arr[i] - arr[da]) * (now / arr[da]) + now, p);
        }
    }
}