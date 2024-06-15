import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<List<Integer>> neighbors = new ArrayList<>();
    private static int n, r, q;
    private static int[] subCounts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // 인접리스트 초기화
        for (int i = 0; i < n + 1; i++) {
            neighbors.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 인접 리스트에 인접 정보 추가
            neighbors.get(u).add(v);
            neighbors.get(v).add(u);
        }

        // 서브 트리 카운트해줄 배열 초기화
        subCounts = new int[n + 1];

        // 배열만 구성하면 되므로 반환값은 필요없다.
        dfs(r, -1);


        for (int i = 0; i < q; i++) {
            int queryNumber = Integer.parseInt(br.readLine());
            bw.write(subCounts[queryNumber] + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int dfs(final int node, final int parent) {
        // 나 자신을 포함
        subCounts[node] = 1;

        // 내 자식의 서브 트리 수를 찾는다
        List<Integer> nearNodes = neighbors.get(node);
        for (Integer nearNode : nearNodes) {
            // 부모로 가지 않게 막음
            if (nearNode != parent) {
                subCounts[node] += dfs(nearNode, node);
            }
        }

        return subCounts[node];
    }
}