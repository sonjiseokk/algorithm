import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++){
            int input = Integer.parseInt(br.readLine());
            
            // 0인 경우
            if (input == 0) {
                if (queue.isEmpty()) sb.append("0");
                else {
                    sb.append(queue.poll().num);
                }
                sb.append("\n");
            } else {
                queue.offer(new Node(input));
            }
        }
        
        System.out.println(sb.toString());    
    }
    
    static class Node implements Comparable<Node> {
        private int num;
        
        public Node(int num){
            this.num = num;
        }
        
        @Override
        public int compareTo(Node x){
            int a = (int) Math.abs(this.num);
            int b = (int) Math.abs(x.num);
            
             if (a == b) {
                // 절대값 같으면 작은 수 우선
                return Integer.compare(this.num, x.num);
            }
            return Integer.compare(a, b);
        }
    }
}
