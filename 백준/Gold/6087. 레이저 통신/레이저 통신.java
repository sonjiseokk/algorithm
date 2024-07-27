import java.io.*;
import java.util.*;

public class Main {
    private static Node start;
    private static Node end;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        boolean isFirst = true;

        for (int i = 0; i < n; i++) {
            char[] oneLine = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = oneLine[j];
                if (map[i][j] == 'C') {
                    if (isFirst) {
                        isFirst = false;
                        start = new Node(j, i, 0, -1);
                    } else {
                        end = new Node(j, i, 0, -1);
                    }
                }
            }
        }

        int[][][] distance = initDistance(n, m);
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.depth));
        queue.add(start);
        distance[start.y][start.x][0] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == end.x && now.y == end.y) {
                distance[end.y][end.x][now.dir] = now.depth;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                // 벽체크
                if (map[ny][nx] == '*') {
                    continue;
                }

                int newDepth = now.depth + (now.dir == i || now.dir == -1 ? 0 : 1);

                // 이미 최적의 경로였던 경우에는 패스
                if (distance[ny][nx][i] > newDepth) {
                    distance[ny][nx][i] = newDepth;
                    queue.add(new Node(nx, ny, newDepth, i));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            result = Math.min(result, distance[end.y][end.x][i]);
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    private static int[][][] initDistance(final int n, final int m) {
        int[][][] distance = new int[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }
        return distance;
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