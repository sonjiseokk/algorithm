import java.io.*;
import java.util.*;

public class Main {
    private static List<Mountain> mountains = new ArrayList<>();
    private static int n, m;
    private static int[][] map;
    private static int year;

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
                if (map[i][j] != 0) {
                    mountains.add(new Mountain(j, i, map[i][j]));
                }
            }
        }

        while (true) {
            bfs();
            melt();

            year++;
            if (floodFill()) {
                break;
            }
            if (allMeltCheck()) {
                year = 0;
                break;
            }
        }

        bw.write(String.valueOf(year));

        bw.flush();
        bw.close();
    }

    private static boolean allMeltCheck() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) cnt++;
            }
        }
        return cnt == (n * m);
    }

    private static boolean floodFill() {
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt >= 2;
    }

    private static void dfs(int y, int x, boolean[][] visited) {
        visited[y][x] = true;
        // 좌우 상하
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 벽을 넘어가는 경우 continue
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;
            }

            if (map[ny][nx] == 0 || visited[ny][nx]) {
                continue;
            }
            dfs(ny, nx, visited);
        }
    }

    private static void melt() {
        for (Mountain mountain : mountains) {
            map[mountain.y][mountain.x] = mountain.value;
        }
    }

    private static void bfs() {
        // 좌우 상하
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Mountain> queue = new LinkedList<>();

        for (Mountain mountain : mountains) {
            queue.add(mountain);
        }

        while (!queue.isEmpty()) {
            Mountain now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 벽을 넘어가는 경우 continue
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                // 최소 0은 유지되어야함
                if (now.value == 0) {
                    continue;
                }

                if (map[ny][nx] == 0) {
                    now.value -= 1;
                }
            }
        }
    }

    private static class Mountain {
        private int x;
        private int y;
        private int value;

        public Mountain(final int x, final int y, final int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
