
import java.io.*;

public class Main {
    static int n;
    static int[][] chess;
    static int res;
    static int[] savedX = new int[16];
    static int[] savedY = new int[16];
    private static int dfs(int y, int x){
        // 가지치기 (백트래킹)
        // y == depth
        // 줄 단위로 보겠다!
        for (int i = 0; i < y; i++) {
            // 가로세로 체크
            if (x == savedX[i]) return 0;
            if (y == savedY[i]) return 0;

            if (Math.abs(x - savedX[i]) == Math.abs(y - savedY[i])) return 0;
        }
        // 종료 조건 (0인덱스 사용하므로 n-1이 배열의 끝)
        if (y == n-1) {
            return 1;
        }

        // 말의 현재 위치 기억
        // 이전 말이 어떤 y에 있는지, 어떤 x에 있는지
        // 즉, 이전말들을 전부 저장해놓고, 그 위치에는 절대 퀸이 올수 없으므로 이거로 가로세로 체크를 한다
        savedY[y] = y;
        savedX[y] = x;


        int r = 0;
        // 가로줄 전부 dfs
        for (int i = 0; i < n; i++) {
            r += dfs(y + 1, i);
        }
        return r;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        chess = new int[n][n];

        for (int i = 0; i < n; i++) {
            res += dfs(0, i);
        }
        bw.write(String.valueOf(res));

        bw.flush();
        bw.close();
    }
    
}
