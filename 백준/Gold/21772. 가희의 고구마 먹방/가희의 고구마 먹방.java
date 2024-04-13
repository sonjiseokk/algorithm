import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, T;
    private static char[][] map;
    private static Node start;
    private static boolean[][] visited;
    private static int res;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'G') {
                    start = new Node(j, i, 0, 0);
                    map[i][j] = '.';
                }
            }
        }

        // 방문 처리
        visited = new boolean[n][m];

        dfs(start);
        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }

    private static void dfs(Node now) {
        if (now.depth == T) {
            res = Math.max(res, now.collect);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (map[ny][nx] == '#') continue;

            // 백트래킹
            int collect = now.collect;
            if (map[ny][nx] == 'S') {
                collect += 1;
                map[ny][nx] = '.';
                dfs(new Node(nx, ny, now.depth + 1, collect));
                map[ny][nx] = 'S';
            }else{
                dfs(new Node(nx, ny, now.depth + 1, collect));
            }
        }

        dfs(new Node(now.x, now.y,now.depth+1, now.collect));

    }

    private static class Node {
        private int x;
        private int y;
        private int depth;
        private int collect;

        public Node(final int x, final int y, final int depth, final int collect) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.collect = collect;
        }
    }
}