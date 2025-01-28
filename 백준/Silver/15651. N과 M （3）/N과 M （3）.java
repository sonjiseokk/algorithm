import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        nums = new int[m];

        rec(n, m, 0);

        bw.flush();
        bw.close();
    }

    static void rec(int n, int m, int depth) throws IOException {
        if (depth == m) {
            for (int num : nums) {
                bw.write(num + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i <= n; i++) {
            nums[depth] = i;
            rec(n, m, depth + 1);
        }
    }
}