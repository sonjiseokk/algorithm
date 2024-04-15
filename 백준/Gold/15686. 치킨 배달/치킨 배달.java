
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_15686_치킨배달_손지석
 * 메모리 : 18756 KB
 * 실행 시간 : 232 ms
 * 코드 길이 : 2517 B
 * <p>
 * [문제]
 * 치킨집과 집 사이의 최소 거리들을 합한 최소값을 구하는 문제
 * <p>
 * [접근]
 * 1. 치킨집을 선택해야한다는 개념이
 */
public class Main {
    private static int n, m;
    private static int[][] map;
    private static List<Node> chickens = new ArrayList<>();
    private static List<Node> houses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new Node(i, j));
                }
                if (map[i][j] == 1) {
                    houses.add(new Node(i, j));
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 1 << chickens.size(); i++) {
            if (Integer.bitCount(i) == m) {
                List<Node> temp = new ArrayList<>();
                for (int j = 0; j < chickens.size(); j++) {
                    if ((i & (1 << j)) != 0) {
                        temp.add(chickens.get(j));
                    }
                }

                res = Math.min(res, calculateDistance(temp));
            }
        }

        bw.write(String.valueOf(res));

        bw.flush();
        bw.close();
    }

    private static int calculateDistance(final List<Node> temp) {
        int sum = 0;

        for (Node house : houses) {
            int min = Integer.MAX_VALUE;
            for (Node chicken : temp) {
                int distance = Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y);
                min = Math.min(min, distance);
            }
            sum += min;
        }

        return sum;
    }

    private static class Node {
        private int y;
        private int x;

        public Node(final int y, final int x) {
            this.y = y;
            this.x = x;
        }
    }
}
