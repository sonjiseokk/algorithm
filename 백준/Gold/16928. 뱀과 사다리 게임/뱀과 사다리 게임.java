import java.io.*;
import java.util.*;

public class Main {
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 보드 초기화
        int[] board = new int[101];
        for (int i = 1; i < 101; i++) {
            board[i] = i;
        }

        // 사다리
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = end;
        }

        // 뱀
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = end;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));

        boolean[] visited = new boolean[101];
        visited[1] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.num == 100) {
                result = now.depth;
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int next = now.num + i;

                if (next <= 100 && !visited[next]) {
                    visited[next] = true;
                    queue.add(new Node(board[next], now.depth + 1));
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    private static class Node {
        private int num;
        private int depth;

        public Node(final int num, final int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}