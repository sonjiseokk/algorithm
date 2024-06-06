import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int a = map[0][0];
        int b = map[0][1];
        int c = map[0][2];

        int[] maxDp = {a, b, c};
        int[] minDp = {a, b, c};
        for (int i = 1; i < n; i++) {
            a = map[i][0];
            b = map[i][1];
            c = map[i][2];
            int na = Math.max(maxDp[0], maxDp[1]) + a;
            int nb = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2])) + b;
            int nc = Math.max(maxDp[1], maxDp[2]) + c;

            int minna = Math.min(minDp[0], minDp[1]) + a;
            int minnb = Math.min(minDp[0], Math.min(minDp[1], minDp[2])) + b;
            int minnc = Math.min(minDp[1], minDp[2]) + c;

            maxDp = new int[]{na, nb, nc};
            minDp = new int[]{minna, minnb, minnc};
        }

        bw.write(String.valueOf(Arrays.stream(maxDp).max().getAsInt()));
        bw.write(" ");
        bw.write(String.valueOf(Arrays.stream(minDp).min().getAsInt()));


        bw.flush();
        bw.close();
    }

}