import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, r;
    private static int[][] map;
    // 동서남북
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static char[][] res;
    private static int resCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 도미노를 모두 세운 상태로
        res = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(res[i], 'S');
        }

        for (int round = 0; round < r; round++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = convertDir(st.nextToken());

            if (res[y][x] == 'S') {
                res[y][x] = 'F';
                bfs(new Node(x, y), d);
            }
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            res[sy - 1][sx - 1] = 'S';
        }

        bw.write(String.valueOf(resCount));
        bw.write("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(res[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(final Node start, final int d) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        int cnt = 1;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int maxH = map[now.y][now.x];

            for (int i = 1; i < maxH; i++) {
                int nx = now.x + (dx[d] * i);
                int ny = now.y + (dy[d] * i);

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                if (res[ny][nx] == 'F') continue;
                res[ny][nx] = 'F';
                cnt++;
                queue.add(new Node(nx, ny));
            }
        }
        resCount += cnt;
    }

    private static int convertDir(final String d) {
        switch (d) {
            case "E":
                return 0;
            case "W":
                return 1;
            case "S":
                return 2;
            case "N":
                return 3;
        }
        return 0;
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