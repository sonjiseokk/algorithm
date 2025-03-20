import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] as = new int[m];
        int[] bs = new int[m];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i< m; i++){
            as[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i< m; i++){
            bs[i] = Integer.parseInt(st.nextToken());
        }
        
        // -------------------
        // 우선순위 큐에 노드 추가
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i< m; i++){
            queue.add(new Node(as[i], bs[i]));
        }
        
        int res = 0;
        int remainHour = 24 * n;
        while (remainHour > 0 && !queue.isEmpty()){
            Node now = queue.poll();
            
            // 만약 100점이라면 값에 추가하고 스킵
            if (now.score >= 100){
                res += now.score;
                continue;
            }
            
            int expectScore = now.score + now.increase;
            if (expectScore >= 100) now.score = 100;
            else now.score = expectScore;
            
            remainHour--;
            queue.add(now);
            
        }
        
        // 100점 아닌 것들 추가로 더해줌
        while(!queue.isEmpty()){
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
        
        public int effectiveIncrease(){
            // 만약 100점까지 남은 값이 상승폭보다 작다면
            // 우선순위 다운
            return Math.min(this.increase, 100 - score);
        }
        
        @Override
        public int compareTo(Node other){
            // 내림차순
            return Integer.compare(other.effectiveIncrease(), this.effectiveIncrease());
        }
    }

    
}
