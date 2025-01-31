import java.io.*;

public class Main {
    static int n, full, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        full = (1 << n) - 1; // 모든 비트가 1인 마스크

        dfs(0, 0, 0, 0);
        System.out.println(count);
    }

    static void dfs(int row, int cols, int diag1, int diag2) {
        if (row == n) {
            count++;
            return;
        }

        int available = full & ~(cols | diag1 | diag2); // 가능한 위치 계산
        while (available > 0) {
            int pos = available & -available; // 가장 오른쪽 1 추출
            available ^= pos; // 사용한 위치 제거
            dfs(row + 1, cols | pos, (diag1 | pos) << 1, (diag2 | pos) >> 1);
        }
    }
}
