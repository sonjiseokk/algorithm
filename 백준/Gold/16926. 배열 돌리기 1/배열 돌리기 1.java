
import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n,m,r;
    private static int tn,tm,tb;
    private static int[][] map;
    private static int[][] newMap;
    private static List<LinkedList<Integer>> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열의 row 크기
        n = Integer.parseInt(st.nextToken());
        // 배열의 column 크기
        m = Integer.parseInt(st.nextToken());
        // 회전 횟수
        r = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        newMap = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tn = n;
        tm = m;
        tb = 1;
        for (int i = 1; i <= Math.min(n/2, m/2); i++) {
            ArrayDeque<Integer> list = new ArrayDeque<>();
            dfs(i, i,i,i,list);
            for (int j = 0; j < r; j++) {
                list.addFirst(list.pollLast());
            }
            place(i,i,i,i,list);
            tn -= 1;
            tm -= 1;
            tb += 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                bw.write(newMap[i][j] + " ");
            }
            bw.write("\n");
        }



        bw.flush();
        bw.close();
    }

    private static void dfs(int x, int y, int targetX, int targetY, ArrayDeque<Integer> list) {
        if (!list.isEmpty() && x == targetX && y == targetY) {
            return;
        }
        if (x == tb && y < tn) {
            list.add(map[y][x]);
            dfs(x, y + 1, targetX, targetY,list);
        } else if (y == tn && x < tm) {
            list.add(map[y][x]);
            dfs(x + 1, y, targetX, targetY, list);
        } else if (x == tm && y > tb) {
            list.add(map[y][x]);
            dfs(x, y - 1, targetX, targetY, list);
        } else if (y == tb && x > tb-1) {
            list.add(map[y][x]);
            dfs(x - 1, y, targetX, targetY, list);
        }
    }
    private static void place(int x, int y, int targetX, int targetY, ArrayDeque<Integer> list) {
        if (list.isEmpty() && x == targetX && y == targetY) {
            return;
        }
        if (x == tb && y < tn) {
            newMap[y][x] = list.poll();
            place(x, y + 1, targetX, targetY,list);
        } else if (y == tn && x < tm) {
            newMap[y][x] = list.poll();
            place(x + 1, y, targetX, targetY, list);
        } else if (x == tm && y > tb) {
            newMap[y][x] = list.poll();
            place(x, y - 1, targetX, targetY, list);
        } else if (y == tb && x > tb-1) {
            newMap[y][x] = list.poll();
            place(x - 1, y, targetX, targetY, list);
        }
    }
}
