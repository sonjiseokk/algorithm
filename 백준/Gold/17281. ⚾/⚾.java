// tip: each public class is put in its own file

import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] scores;
    private static int res;

    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        scores = new int[n + 1][10];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[] visited = new boolean[10];
        int[] lineUp = new int[10];

        lineUp[4] = 1;
        visited[4] = true;
        dfs(2, visited, lineUp);

        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth, boolean[] visited, int[] lineUp) {
        if (depth == 10) {
            play(lineUp);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (!visited[i]) {
                lineUp[i] = depth;
                visited[i] = true;
                dfs(depth + 1, visited, lineUp);
                lineUp[i] = 0;
                visited[i] = false;
            }
        }

    }

    private static void play(int[] lineUp) {
        int start = 1;
        int score = 0;
        for (int ening = 1; ening <= n; ening++) {
            int outCount = 0;
            int baseState = 0;
            int now = start;
            while (outCount < 3) {
                int action = scores[ening][lineUp[now]];

                if (action == 0) outCount++;
                else {
                    baseState = updateState(baseState, action);
                    score += Integer.bitCount(baseState >> 4);
                    baseState &= 0b1111;
                }

                now = (now % 9) + 1;
                if (outCount >= 3) {
                    start = now;
                }
            }
        }

        res = Math.max(res, score);
    }

    private static int updateState(int baseState, int action) {
        baseState |= 1;
        return baseState << action;

    }
}