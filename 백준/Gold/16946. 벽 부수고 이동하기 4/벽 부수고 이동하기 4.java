import java.io.*;
import java.util.*;

public class Main {
    private static int[][] map;
    private static int n, m;
    private static int[][] group;
    private static int[][] result;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        group = new int[n][m];
        result = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        logic();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(String.valueOf(result[i][j]));
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void logic() {
        int number = 1;
        Map<Integer, Integer> groupCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 벽이 아니면서 그룹도 없는 애들
                if (map[i][j] == 0 && group[i][j] == 0) {
                    int count = bfs(j, i, number);
                    groupCount.put(number, count);
                    number++;
                }
            }
        }

        // 이제 벽을 찾아다가 상하좌우 그룹 더해줘야함
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    boolean[] visited = new boolean[number + 1];
                    int sum = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                            continue;
                        }

                        // 벽이 아니면서 방문하지 않은 그룹일때
                        if (map[ny][nx] == 0 && !visited[group[ny][nx]]) {
                            sum += groupCount.get(group[ny][nx]);
                            visited[group[ny][nx]] = true;
                        }
                    }
                    result[i][j] = (sum + 1) % 10;
                }
            }
        }
    }

    private static int bfs(final int x, final int y, final int number) {
        // 일단 시작점 그룹 할당해주고
        group[y][x] = number;

        Node start = new Node(x, y);
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        // 그룹 크기 (나 포함)
        int count = 1;
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                if (map[ny][nx] == 0 && group[ny][nx] == 0) {
                    group[ny][nx] = number;
                    queue.add(new Node(nx, ny));
                    count++;
                }
            }
        }
        return count;
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