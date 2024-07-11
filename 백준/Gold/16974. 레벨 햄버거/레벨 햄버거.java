import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static long[] paties;
    private static long[] hambugersLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        paties = new long[n + 1];
        hambugersLength = new long[n + 1];

        paties[0] = 1;
        hambugersLength[0] = 1;

        for (int i = 1; i <= n; i++) {
            paties[i] = (2 * paties[i - 1])  + 1;
            hambugersLength[i] = (hambugersLength[i - 1] * 2) + 3;
        }

        long result = logic(n, x);
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }

    private static long logic(int level, long x) {
        // 만약 레벨이 0이면 패티만 있는 상태임
        // x가 1이면 패티를 먹은거고 0이면 안먹은거
        if (level == 0) {
            return x == 0 ? 0 : 1;
        }

        // 1은 무조건 B임
        if (x == 1) {
            return 0;
        }
        // 중간보다 작은 경우
        // 중간은 이전 레벨의 + 2 즉, + 1은 중간 이전
        if (x <= hambugersLength[level - 1] + 1) {
            // x-1을 해주는 이유는 첫번째가 B이기 때문에 제외시켜 주기 위함
            return logic(level - 1, x - 1);
        } else if (x == hambugersLength[level - 1] + 2) {
            // 중간인 경우 패티 한장이 추가되었다
            return paties[level - 1] + 1;
        } else{
            return paties[level -1] + 1 + logic(level -1 , x - (hambugersLength[level - 1] + 2));
        }
    }
}