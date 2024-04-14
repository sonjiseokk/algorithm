

import java.io.*;
import java.util.*;

public class Main {
    private static int n, m, k;
    private static int[][] map;
    private static int[][] nourishments;
    private static ArrayDeque<Tree> trees;
    private static List<Tree> deadList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        nourishments = new int[n+1][n+1];
        trees = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], 5);
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                nourishments[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Tree> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            temp.add(new Tree(x, y, age));
        }

        temp.sort(Comparator.comparing(Tree::getAge));

        trees.addAll(temp);

        while (k > 0) {
            spring();
            summer();
            deadList = new ArrayList<>();

            autumn();
            winter();
            k -= 1;
        }

        bw.write(String.valueOf(trees.size()));
        bw.flush();
        bw.close();
    }

    private static void spring() {
        ArrayDeque<Tree> queue = new ArrayDeque<>();
        deadList = new ArrayList<>();
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;
            if (map[y][x] < age) {
                deadList.add(tree);
            } else {
                map[y][x] -= age;
                queue.add(new Tree(x, y, age + 1));
            }
        }
        trees = queue;
    }

    private static void summer() {
        for (Tree t : deadList) {
            map[t.y][t.x] += t.age / 2;
        }
    }

    private static void autumn() {
        ArrayDeque<Tree> temp = new ArrayDeque<>();

        // 상하좌우 좌상우상좌하우하
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

        while (!trees.isEmpty()) {
            Tree tree = trees.pollFirst();    // 가장 작은 것들부터 빼내야 정렬된 채로 가질 수 있음
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            // 주위에 나무 심기
            if (age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 1 || nx > n || ny < 1 || ny > n) {
                        continue;
                    }

                    temp.addFirst(new Tree(nx, ny, 1));
                }
            }

            temp.addLast(tree);
        }

        trees = temp;
    }

    private static void winter() {
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                map[y][x] += nourishments[y][x];
            }
        }
    }

    private static class Tree {
        int x;
        int y;
        int age;

        public int getAge() {
            return age;
        }

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
