import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        List<Node> list = new ArrayList<>();
        for (int i = 0; i <n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int min = 0;
            int max = 0;
            if (a < b) {
                max = b;
                min = a;
            } else {
                max = a;
                min = b;
            }
            list.add(new Node(min,max));
        }
        
        // 내림차순 정렬
        Collections.sort(list);
        
        // DP 배열 생성
        int[] dp = new int[n];
        
        // 거꾸로 탐색
        int res = 0;
        for (int i = 0; i < n; i++){
            Node now = list.get(i);
            
            dp[i] = 1;
            for (int j = 0; j<i; j++){
                Node nxt = list.get(j);
                
                // now보다 nxt가 커서 들어갈 수 있는 색종이인 경우에ㅁ
                if (now.max <= nxt.max && now.min <= nxt.min){
                    // cnt 갱신
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            res = Math.max(dp[i], res);
        }
        
        System.out.println(res);
        
        
    }
    static class Node implements Comparable<Node>{
        int min;
        int max;
        public Node(int min, int max){
            this.min = min;
            this.max = max;
        }
        
        @Override
        public int compareTo(Node o){
            if (this.max != o.max) return o.max - this.max;
            else {
                return o.min - this.min;
            }
        }
    }
}
