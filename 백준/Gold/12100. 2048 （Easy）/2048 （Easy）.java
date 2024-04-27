import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n, res;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        // 값을 입력받자
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, map);

        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth, final int[][] map) {
        if (depth == 5) {
            res = Math.max(res, getMax(map));
            return;
        }
        // 최대 5번이동 시킬 수 있다.

        // 4방향으로 이동시키기
        for (int d = 0; d < 4; d++) {
            // 원래 배열을 카피해오자
            int[][] newMap = copyMap(map);
            logic(d, newMap);
            dfs(depth + 1, newMap);
        }

    }

    private static void logic(final int d, final int[][] map) {
        boolean[][] merged = new boolean[n][n];
        // 오른쪽
        if (d == 0) {
            for (int depth = 0; depth < n; depth++) {
                // 비교 대상을 설정해준다
                // 오른쪽 끝에서 두번째부터 시작하자
                for (int now = n - 2; now >= 0; now--) {
                    // 0이면 이동시킬 필요가 없다
                    if (map[depth][now] == 0) {
                        continue;
                    }
                    // 내 오른쪽을 확인해줄 변수
                    int temp = now + 1;
                    // 오른쪽이 0이고 벽에 부딫히기 전까지 이동
                    while (temp < n && map[depth][temp] == 0) {
                        temp++;
                    }
                    // 이동할만큼 이동한 곳이 합칠수있는지 체크
                    if (temp < n && map[depth][temp] == map[depth][now] && !merged[depth][temp]) {
                        // 합칠 수 있는 경우
                        map[depth][temp] *= 2;
                        map[depth][now] = 0;
                        merged[depth][temp] = true; // 합쳐졌음을 표시
                    } else {
                        // 합칠 수 없으면 가장 가까운 위치에 이동
                        map[depth][temp - 1] = map[depth][now];
                        if (now != temp - 1) map[depth][now] = 0;
                    }
                }
            }
        } else if (d == 1) { // 왼쪽
            for (int depth = 0; depth < n; depth++) {
                // 비교 대상을 설정해준다
                for (int now = 1; now < n; now++) {
                    // 0이면 이동시킬 필요가 없다
                    if (map[depth][now] == 0) {
                        continue;
                    }
                    // 내 왼쪽을 확인해줄 변수
                    int temp = now - 1;
                    // 왼쪽이 0이고 벽에 부딫히기 전까지 이동
                    while (temp >= 0 && map[depth][temp] == 0) {
                        temp--;
                    }
                    // 이동할만큼 이동한 곳이 합칠수있는지 체크
                    if (temp >= 0 && map[depth][temp] == map[depth][now] && !merged[depth][temp]) {
                        // 합칠 수 있는 경우
                        map[depth][temp] *= 2;
                        map[depth][now] = 0;
                        merged[depth][temp] = true; // 합쳐졌음을 표시
                    } else {
                        // 합칠 수 없으면 가장 가까운 위치에 이동
                        map[depth][temp + 1] = map[depth][now];
                        if (now != temp + 1) map[depth][now] = 0;
                    }
                }
            }
        } else if (d == 2) { // 상
            for (int depth = 0; depth < n; depth++) {
                // 비교 대상을 설정해준다
                for (int now = 1; now < n; now++) {
                    // 0이면 이동시킬 필요가 없다
                    if (map[now][depth] == 0) {
                        continue;
                    }
                    // 내 위쪽을 확인해줄 변수
                    int temp = now - 1;
                    // 위쪽이 0이고 벽에 부딫히기 전까지 이동
                    while (temp >= 0 && map[temp][depth] == 0) {
                        temp--;
                    }
                    // 이동할만큼 이동한 곳이 합칠수있는지 체크
                    if (temp >= 0 && map[temp][depth] == map[now][depth] && !merged[temp][depth]) {
                        // 합칠 수 있는 경우
                        map[temp][depth] *= 2;
                        map[now][depth] = 0;
                        merged[temp][depth] = true; // 합쳐졌음을 표시
                    } else {
                        // 합칠 수 없으면 가장 가까운 위치에 이동
                        map[temp + 1][depth] = map[now][depth];
                        if (now != temp + 1) map[now][depth] = 0;
                    }
                }
            }

        } else if (d == 3) { // 하
            for (int depth = 0; depth < n; depth++) {
                // 비교 대상을 설정해준다
                for (int now = n - 2; now >= 0; now--) {
                    // 0이면 이동시킬 필요가 없다
                    if (map[now][depth] == 0) {
                        continue;
                    }
                    // 내 아래쪽을 확인해줄 변수
                    int temp = now + 1;
                    // 위쪽이 0이고 벽에 부딫히기 전까지 이동
                    while (temp < n && map[temp][depth] == 0) {
                        // 이동할 수 있으면 계속 이동시킴
                        temp++;
                    }
                    // 이동할만큼 이동한 곳이 합칠수있는지 체크
                    if (temp < n && map[temp][depth] == map[now][depth] && !merged[temp][depth]) {
                        // 합칠 수 있는 경우
                        map[temp][depth] *= 2;
                        map[now][depth] = 0;
                        merged[temp][depth] = true; // 합쳐졌음을 표시
                    } else {
                        // 합칠 수 없으면 가장 가까운 위치에 이동
                        map[temp - 1][depth] = map[now][depth];
                        if (now != temp - 1) map[now][depth] = 0;
                    }
                }
            }

        }
    }

    private static int getMax(final int[][] newMap) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, newMap[i][j]);
            }
        }
        return max;
    }

    private static int[][] copyMap(final int[][] map) {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            newMap[i] = Arrays.copyOf(map[i], n);
        }
        return newMap;
    }
}