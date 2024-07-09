import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] visited;
    private static int[] nums;
    private static int cnt;
    private static boolean[] finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            nums = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            cnt = n;
            // DFS를 돌리면서 사이클을 찾으면 방문 처리가 확정되는 시스템
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            bw.write(String.valueOf(cnt));
            bw.write("\n");
        }


        bw.flush();
        bw.close();

    }

    private static void dfs(final int start) {
        visited[start] = true;

        int next = nums[start];
        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            for (int temp = next; temp != start; temp = nums[temp]) {
                cnt--;
            }
            cnt--;
        }
        finished[start] = true;
    }
}