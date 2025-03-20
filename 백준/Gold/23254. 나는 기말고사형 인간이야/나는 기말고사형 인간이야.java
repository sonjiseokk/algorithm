import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] as = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++){
            as[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] bs = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++){
            bs[i] = Integer.parseInt(st.nextToken());
        }
        
        //----------------
        
        // 우선순위 큐에 추가
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < m; i++){
            queue.add(new Node(as[i], bs[i]));
        }
        
        int remainHour = 24 * n;
        int res = 0;
        while (remainHour > 0 && !queue.isEmpty()){
            // 큐에서 뽑은다음
            Node now = queue.poll();
            
            // 근데 이미 100점이면 패스해
            if (now.score == 100) {
                res += now.score;
                continue;
            }
            
            // 1시간 공부했으니 점수 올려
            int possibleIncrease = Math.min(now.increase, 100 - now.score);
            now.score += possibleIncrease;
            
            // 남은 시간 줄여
            remainHour--;
            
            // 큐에 다시 넣어
            queue.add(now);
        }

        while (!queue.isEmpty()){
            res += queue.poll().score;
        }
        
        System.out.println(res);
        
    }
    
    static class Node implements Comparable<Node>{
        int score;
        int increase;
        public Node(int score, int increase){
            this.score = score;
            this.increase = increase;
        }
        
        public int effectiveGain() {
            return Math.min(increase, 100 - score);
        }
        @Override
        public int compareTo(Node node){
            return Integer.compare(node.effectiveGain(), this.effectiveGain());
        }
    }
    
}
