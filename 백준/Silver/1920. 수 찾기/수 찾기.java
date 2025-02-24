

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] findNums = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            findNums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        for (int findNum : findNums) {
            if (Arrays.binarySearch(nums, findNum) >= 0) {
                bw.write("1\n");
                continue;
            }
            bw.write("0\n");
        }

        bw.flush();
        bw.close();
    }
}
