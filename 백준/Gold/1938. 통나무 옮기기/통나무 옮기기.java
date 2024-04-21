import java.io.*;
import java.util.*;

public class Main {
    private static int GARO = 0;
    private static int SERO = 1;
    private static int n;
    private static int res = Integer.MAX_VALUE;
    private static char[][] map;
    private static List<Node> woods = new ArrayList<>();
    private static List<Node> endPoints = new ArrayList<>();
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'B') {
                    woods.add(new Node(j, i, 0, -1));
                }
                if (map[i][j] == 'E') {
                    endPoints.add(new Node(j, i, 0, -1));
                }
            }
        }

        // 초기 나무의 방향과 끝나는 위치의 방향 확인
        woods.get(1).dir = selectDir(woods);
        endPoints.get(1).dir = selectDir(endPoints);

        bfs();

        if (res == Integer.MAX_VALUE) {
            bw.write("0");
        } else {
            bw.write(String.valueOf(res));
        }

        bw.flush();
        bw.close();
    }

    private static void bfs() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        visited = new boolean[n][n][2];

        // 시작 위치 방문처리
        Node start = woods.get(1);
        Node target = endPoints.get(1);
        visited[start.y][start.x][start.dir] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == target.x && now.y == target.y && now.dir == target.dir) {
                res = Math.min(res, now.depth);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isOut(nx, ny)) {
                    continue;
                }

                if (visited[ny][nx][now.dir]) {
                    continue;
                }

                if (isPlacedTree(nx, ny, now.dir)) {
                    continue;
                }

                visited[ny][nx][now.dir] = true;
                queue.add(new Node(nx, ny, now.depth + 1, now.dir));
            }

            // 회전하는 경우
            if (isAvailableTurn(now.x, now.y, now.dir)) {
                if (now.dir == GARO) {
                    queue.add(new Node(now.x, now.y, now.depth + 1, SERO));
                } else {
                    queue.add(new Node(now.x, now.y, now.depth + 1, GARO));
                }
            }
        }
    }

    private static boolean isAvailableTurn(final int x, final int y, int dir) {
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isOut(nx, ny)) {
                return false;
            }
            if (map[ny][nx] == '1') {
                return false;
            }
        }
        // 방향 수정
        if (dir == GARO) dir = SERO;
        else dir = GARO;

        if (isPlacedTree(x, y, dir)) {
            return false;
        }
        if (visited[y][x][dir]) {
            return false;
        }
        visited[y][x][dir] = true;
        return true;
    }

    private static boolean isPlacedTree(final int nx, final int ny, final int dir) {
        for (int i = -1; i < 2; i++) {
            if (dir == SERO) {
                int next = ny + i;
                if (isOut(nx, next)) {
                    return true;
                }
                if (map[next][nx] == '1') {
                    return true;
                }
            } else {
                int next = nx + i;
                if (isOut(next,ny)) {
                    return true;
                }
                if (map[ny][next] == '1') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOut(final int nx, final int ny) {
        return nx < 0 || ny < 0 || nx >= n || ny >= n;
    }

    private static int selectDir(final List<Node> list) {
        if (list.get(1).x + 1 == list.get(2).x) {
            return GARO;
        } else {
            return SERO;
        }
    }

    private static class Node {
        private int x;
        private int y;
        private int depth;
        private int dir;

        public Node(final int x, final int y, final int depth, final int dir) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.dir = dir;
        }
    }
}