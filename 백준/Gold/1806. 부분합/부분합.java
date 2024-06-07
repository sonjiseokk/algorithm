import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int result = Integer.MAX_VALUE;

        int startIndex = 0;
        int endIndex = 0;
        int currentSum = 0;
        while (endIndex < n) {
            currentSum += nums[endIndex];
            while (currentSum >= s) {
                result = Math.min(result, endIndex - startIndex + 1);
                currentSum -= nums[startIndex];
                startIndex++;
            }
            endIndex++;
        }

        if (result == Integer.MAX_VALUE) {
            bw.write("0");
        } else {
            bw.write(String.valueOf(result));
        }
        bw.flush();
        bw.close();
    }
}