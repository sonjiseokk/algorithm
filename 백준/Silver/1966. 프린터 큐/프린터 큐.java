import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int tries = Integer.parseInt(br.readLine());

        for (int i=0; i<tries; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 총 개수와 찾을 인덱스
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 입력받는 프린터 큐 숫자들
            st = new StringTokenizer(br.readLine());

            // 큐에 인덱스와 같이 저장
            // 우선순위 큐는 이제 꺼낼 수를 정렬
            Queue<Node> q = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for(int j =0; j< n; j++){
                int priority = Integer.parseInt(st.nextToken());
                q.add(new Node(j,priority));
                pq.add(priority);
            }

            int printStack = 0;
            while (!q.isEmpty()) {
                // 일단 첫번째꺼 빼서 확인
                Node now = q.poll();

                // 나올만한 애야
                if(now.value == pq.peek()){
                    pq.poll();
                    printStack++;
                    if(now.index == m){
                        System.out.println(printStack);
                        break;
                    }
                } else{
                    q.add(now);
                }
            }
            
        }
    }

    private static class Node{
        private int index;
        private int value;

        Node(int index, int value){
            this.index=  index;
            this.value = value;
        }

    }
}