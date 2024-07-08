import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(w, p);
        }

        // 가방은 크기가 작은순으로 채워야함
        int[] bags = new int[k];
        boolean[] visited = new boolean[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nodes, Comparator.comparing(Node::getW));
        Arrays.sort(bags);

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparing(Node::getPrice).reversed());

        // 하나의 보석이 들어갈 가방을 선택하는 로직에서 시간 소요가 큼

        long result = 0;
        int index = 0;

        for (int i = 0; i < k; i++) {
            while (index < n && nodes[index].w <= bags[i]) {
                queue.add(nodes[index]);
                index++;
            }

            if (!queue.isEmpty()) {
                result += queue.poll().price;
            }
        }

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }

    private static class Node {
        private long w;
        private long price;

        public Node(final long w, final long price) {
            this.w = w;
            this.price = price;
        }

        public long getPrice() {
            return price;
        }

        public long getW() {
            return w;
        }
    }
}