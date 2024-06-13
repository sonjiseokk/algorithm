import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int VISITED = 2;
    private static int n, m;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    logic(i, j);
                    res += 1;
                }
            }
        }

        bw.write(String.valueOf(res));

        bw.flush();
        bw.close();
    }

    private static void logic(int y, int x) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        map[y][x] = VISITED;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    int[] mediate = mediate(nx, ny);
                    nx = mediate[0];
                    ny = mediate[1];
                }

                if (map[ny][nx] == VISITED || map[ny][nx] == 1) {
                    continue;
                }

                map[ny][nx] = VISITED;
                queue.add(new Node(nx, ny));
            }
        }
    }

    private static int[] mediate(int nx, int ny) {
        if (nx < 0) {
            nx = m - 1;
        } else if (nx >= m) {
            nx = 0;
        } else if (ny < 0) {
            ny = n - 1;
        } else if (ny >= n) {
            ny = 0;
        }

        return new int[]{nx, ny};
    }

    private static class Node {
        private int x;
        private int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}