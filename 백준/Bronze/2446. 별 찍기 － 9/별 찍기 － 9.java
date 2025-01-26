import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());


        for (int i = 1; i < n+1; i++) {
            for (int k = 1; k < i; k++) {
                bw.write(" ");
            }
            for (int j = 2*n; j > 2*(i)-1; j--) {
                bw.write("*");
            }

            bw.write("\n");
        }
        for (int i = 2; i < n+1; i++) {
            for (int j = 0; j < n - i; j++) {
                bw.write(" ");
            }
            for (int k = 1; k < 2*i; k++) {
                bw.write("*");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();

    }
}
