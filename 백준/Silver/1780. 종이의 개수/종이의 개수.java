
import java.io.*;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1780_종이의개수_손지석
 * </pre>
 *
 * 메모리 : 16140KB
 * 시간 : 168ms
 * 코드 길이 : 1489B
 *
 * @author 손지석
 * @since jdk17
 *
 * [입력]
 * 9
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 0 1 -1 0 1 -1 0 1 -1
 * 0 -1 1 0 1 -1 0 1 -1
 * 0 1 -1 1 0 -1 0 1 -1
 *
 * [출력]
 * 10
 * 12
 * 11
 *
 * [과정]
 *
 */
public class Main {
    static int minus;
    static int zero;
    static int one;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // 입력받은 크기 만큼만 배열 생성
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                // 값 할당
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기값 재귀 (0,0) 에서 n만큼 돌린다는 것은 결국 전체 배열을 돌린다는 뜻
        recurcive(map,0, 0, n);

        bw.write(String.valueOf(minus));
        bw.write("\n");
        bw.write(String.valueOf(zero));
        bw.write("\n");
        bw.write(String.valueOf(one));

        bw.flush();
        bw.close();
    }

    private static void recurcive(int[][] map, int x, int y, int n) {
        // 주어진 배열을 돌아서 1인 값을 측정
        int oneTemp = 0;
        int zeroTemp = 0;
        int minusTemp = 0;
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (map[i][j] == 1) {
                    oneTemp += 1;
                } else if (map[i][j] == -1) {
                    minusTemp += 1;
                } else{
                    zeroTemp += 1;
                }
            }
        }

        // 전체가 0이거나 1이여야 유효하므로 그걸 카운팅
        if (oneTemp == n * n) {
            one += 1;
        } else if (zeroTemp == n*n) {
            zero += 1;
        } else if (minusTemp == n * n) {
            minus += 1;
        } else {
            int newSize = n / 3;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    recurcive(map, x + i * newSize, y + j * newSize, newSize);
                }
        }
        }
    }

}
