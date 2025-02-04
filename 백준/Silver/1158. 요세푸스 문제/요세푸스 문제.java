

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                q.addLast(q.poll());
            }
            result.add(q.poll());
        }
        bw.write("<");
        for (int i = 0; i < result.size(); i++) {
            if (result.size() - 1 == i) {
                bw.write(result.get(i) +">");
                break;
            }
            bw.write(result.get(i) +", ");
        };

        bw.flush();
        bw.close();
    }
}
