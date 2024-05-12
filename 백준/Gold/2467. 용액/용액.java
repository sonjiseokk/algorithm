import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static long[] nums;
    private static long X;
    private static long Y;
    private static long diff = Long.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        nums = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        logic(n);

        bw.write(String.format("%d %d",X,Y));

        bw.flush();
        bw.close();
    }

    private static void logic(final int n) {
        int start = 0;
        int end = n - 1;

        while (start < end) {
            // 음수 양수일땐 더해
            long nowDiff = nums[end] + nums[start];
            long absDiff = Math.abs(nowDiff);


            if (absDiff < diff) {
                X = nums[start];
                Y = nums[end];
                diff = absDiff;
            }

            if (nowDiff > 0) {
                end -= 1;
            } else if (nowDiff < 0) {
                start += 1;
            } else {
                return;
            }
        }

    }
}