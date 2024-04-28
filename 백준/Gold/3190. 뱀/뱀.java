import java.io.*;
import java.util.*;

public class Main {
    private static final int APPLE = 1;
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static int n, res;
    private static int[][] map;
    private static Map<Integer, String> turns = new HashMap<>();
    private static int direction = RIGHT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        int appleCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < appleCount; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1] = APPLE;
        }

        int turnCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < turnCount; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            turns.put(time, direction);
        }

        Deque<Node> snake = new ArrayDeque<>();
        snake.addLast(new Node(0, 0));

        int now = 0;
        boolean collision = false;
        while (true) {
            String turn = turns.getOrDefault(now, "no");
            if (!turn.equals("no")) {
                if (turn.equals("D")) {
                    direction = (direction + 1) % 4;
                } else {
                    direction -= 1;
                    if (direction < 0) {
                        direction = 3;
                    }
                }
            }

            Node forward = forward(snake);
            if (forward == null) {
                res = now + 1;
                break;
            }
            // 이동한 뱀의 머리가 몸통에 부딪히는지 체크
            for (Node body : snake) {
                if (body.equals(forward)) {
                    res = now + 1;
                    collision = true;
                    break;
                }
            }
            if (collision) {
                break;
            }
            snake.addFirst(forward);

            if (map[forward.y][forward.x] != APPLE) {
                snake.pollLast();
            } else {
                map[forward.y][forward.x] = 0;
            }


            now++;
        }

        bw.write(String.valueOf(res));

        bw.flush();
        bw.close();
    }

    private static Node forward(final Deque<Node> snake) {
        // 우하좌상
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Node first = snake.peek();

        // 앞으로 고개를 내밀어
        int nx = first.x + dx[direction];
        int ny = first.y + dy[direction];

        // 벽을 넘어가는지 체크
        if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
            return null;
        }

        return new Node(nx, ny);
    }

    private static class Node {
        private int x;
        private int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}