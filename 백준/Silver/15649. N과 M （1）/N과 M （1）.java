
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static LinkedList<Integer> list = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 개수와 이을 쌍 개수 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dfs();

        bw.flush();
        bw.close();
    }
    private static void dfs() throws IOException {
        // 만약 백트래킹 호출전에 add 되어서 m과 같은 크기가 되었다면 출력
        if (list.size() == m) {
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i) + " ");
            }
            bw.write("\n");
            return;
        }
        // 1부터 돌아가며 백트래킹.
        // 이렇게 반복문을 돌면
        // 1 - 2,3,4 (1은 contain)
        // 2 - 1,3,4 (2는 contain)
        // 이런식으로 백트래킹을 수행할 수 있음
        // 출력된 이후 (size == m) 마지막 원소를 제거해줌
        for (int i = 1; i <= n; i++) {
            if (!list.contains(i)) {
                list.add(i);
                dfs();
                list.pollLast();
            }
        }
    }
}
