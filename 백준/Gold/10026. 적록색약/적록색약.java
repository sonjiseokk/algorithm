import java.io.*;

public class Main {
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int normalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(new Node(i, j, map[i][j]));
                    normalCnt++;
                }
            }
        }

        visited = new boolean[n][n];
        int notCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs2(new Node(i, j, map[i][j]));
                    notCnt++;
                }
            }
        }

        bw.write(String.valueOf(normalCnt));
        bw.write(" ");
        bw.write(String.valueOf(notCnt));
        bw.flush();
        bw.close();
    }

    private static void dfs(final Node node) {
        visited[node.y][node.x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }
            if (node.color != map[ny][nx]) {
                continue;
            }
            dfs(new Node(ny, nx, node.color));
        }
    }

    private static void dfs2(final Node node) {
        visited[node.y][node.x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }
            if (node.color != map[ny][nx]) {
                if ((node.color == 'R') && map[ny][nx] == 'G') {
                    dfs2(new Node(ny, nx, node.color));
                } else if (node.color == 'G' && map[ny][nx] == 'R') {
                    dfs2(new Node(ny, nx, node.color));
                } else{
                    continue;
                }
            }
            dfs2(new Node(ny, nx, node.color));
        }
    }

    private static class Node {
        private int y;
        private int x;
        private char color;

        public Node(final int y, final int x, final char color) {
            this.y = y;
            this.x = x;
            this.color = color;
        }
    }
}
