

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_17829_222풀링_손지석
 * </pre>
 *
 * 메모리 : 97860 KB
 * 시간 : 684 ms
 * 코드 길이 : 1485 B
 *
 * @author 손지석
 *
 * [문제]
 * 분할 재귀를 활용하여 2x2 배열에서의 두번째로 큰 값만을 계속해서 추출해나가는 문제
 *
 * [입력]
 8
-1 2 14 7 4 -5 8 9
10 6 23 2 -1 -1 7 11
9 3 5 -2 4 4 6 6
7 15 0 8 21 20 6 6
19 8 12 -8 4 5 2 9
1 2 3 4 5 6 7 8
9 10 11 12 13 14 15 16
17 18 19 20 21 22 23 24
 *
 * [출력]
 * 17
 *
 * [과정]
 * 1. 기존에 분할 재귀 문제에서 아이디어를 얻음
 * 2. 먼저 n / 2 배열로 4등분을 하다가 길이가 2가 되었을 때에는 두번째로 큰 수를 반환하는 식으로 생각
 * 3. 분리해서 계산하니 정답을 받았음
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 배열의 크기 할당
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        // 입력 및 할당
        for (int i = 0; i < n; i++) {
            String oneLine = br.readLine();
            StringTokenizer st = new StringTokenizer(oneLine);
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int res = recursive(map, 0, 0, n);
        bw.write(String.valueOf(res));

        bw.flush();
        bw.close();
    }

    private static int recursive(int[][] map, int x, int y, int n) {
        // 2 x 2 배열의 값을 저장할 배열 초기화
        int[] temp = new int[4];
        int inx = 0;

        // 만약 크기가 2가 아니라면 분할해야함
        if (n != 2) {
            int newSize = n / 2;
            // 분할하여 temp에 값 할당
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    temp[inx++] = recursive(map, x + j * newSize, y + i * newSize, newSize);
                }
            }
        }
        // 만약 크기가 2라면 그대로 temp에 할당해서 2번째로 큰 값을 뽑아내야함
        else{
            for (int i = y; i < y + 2; i++) {
                for (int j = x; j < x + 2; j++) {
                    temp[inx++] = map[i][j];
                }
            }
        }

        // 2번째로 큰 값을 할당받기위해 sort
        Arrays.sort(temp);
        // 2번째로 큰 값 return
        return temp[2];
    }
}
