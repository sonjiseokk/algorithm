import java.io.*;
import java.util.*;

public class Main {
    private static int n, k;
    private static int res = Integer.MAX_VALUE;
    private static List<Integer> resValues;
    private static boolean[] visited = new boolean[100_001];
    private static int[] prev = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(prev, -1);
        visited[n] = true;
        bfs();

        bw.write(String.valueOf(res));
        bw.write("\n");

        List<Integer> list = new ArrayList<>();
        int temp = k;
        while (prev[temp] != -1) {
            if (temp < 0) {
                break;
            }
            int prevValue = prev[temp];
            list.add(prevValue);
            temp = prevValue;
        }
        Collections.reverse(list);
        for (Integer i : list) {
            bw.write(i + " ");
        }
        bw.write(String.valueOf(k));

        bw.flush();
        bw.close();
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 0));
        int maxValue = Math.min(k * 2, 100_001);

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            visited[now.value] = true;
            if (now.value == k) {
                res = now.depth;
            }
            int minus = now.value - 1;
            int multiple = now.value * 2;
            int plus = now.value + 1;

            if (minus >= 0) {
                if (!visited[minus]) {
                    Node node = new Node(minus, now.depth + 1);
                    visited[minus] = true;
                    prev[minus] = now.value;
                    queue.add(node);
                }
            }
            if (maxValue >= multiple) {
                if (!visited[multiple]) {
                    Node node = new Node(multiple, now.depth + 1);
                    visited[multiple] = true;
                    prev[multiple] = now.value;
                    queue.add(node);
                }
            }
            if (plus < 100_001 && plus <= maxValue) {
                if (!visited[plus]) {
                    Node node = new Node(plus, now.depth + 1);
                    visited[plus] = true;
                    prev[plus] = now.value;
                    queue.add(node);
                }
            }
        }
    }

    private static class Node {
        private int value;
        private int depth;

        public Node(final int value, final int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
}
