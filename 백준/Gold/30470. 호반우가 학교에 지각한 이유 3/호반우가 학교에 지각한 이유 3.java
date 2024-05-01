import java.io.*;
import java.util.*;

public class Main {
    private static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Stack<Tree> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (oper == 1) {
                // 스택에 나무가 있으면서 value가 같으면 갯수만 증가
                // 나무가 항상 큰 값으로 들어오긴 해서.. 이게 맞는지는 애매하네
                if (!stack.isEmpty() && stack.peek().value == m) {
                    stack.peek().repeat += 1;
                } else {
                    stack.add(new Tree(m, 1));
                }
                res += m;
            } else {
                // 비어있으면 패스
                if (stack.isEmpty()) {
                    continue;
                }
                // 마법 수행
                int newSize = Math.max(stack.peek().value - m, 0);
                int cnt = 0;
                Stack<Tree> temp = new Stack<>();
                // 스택의 최상단 길이가 마법 기준보다 큰 경우
                while (!stack.isEmpty() && stack.peek().value > newSize) {
                    Tree now = stack.pop();
                    res -= ((long) now.value * now.repeat);
                    if (newSize > 0) {
                        cnt += now.repeat;
                    }
                }

                res += ((long) newSize * cnt);
                stack.add(new Tree(newSize, cnt));
            }
        }

        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }

    private static class Tree {
        private int value;
        private int repeat;

        public Tree(final int value, final int repeat) {
            this.value = value;
            this.repeat = repeat;
        }
    }
}