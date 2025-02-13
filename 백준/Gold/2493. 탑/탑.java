import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] towers = new int[n];
        
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i< n ; i++){
            towers[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] result = new int[n];
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i< n ; i++){
            // 아무것도 없으면 추가
            if (stack.isEmpty()){
                stack.add(new Node(towers[i],i));
                continue;
            }
            
            // 작은수가 들어옴
            if(stack.peek().num > towers[i]){
                result[i] = stack.peek().index + 1;
                stack.add(new Node(towers[i], i));
                continue;
            }
            
            // 큰수가 들어옴
            while (!stack.isEmpty() && stack.peek().num < towers[i]){
                stack.pop();   
            }
            

            if (stack.size() < 1){
                result[i] = 0;
            } else{
                result[i] = stack.peek().index+1;
            }
            stack.add(new Node(towers[i],i));
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< n; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.print(sb.toString());
    }
    static class Node {
        int num;
        int index;
        
        public Node(int num, int index){
            this.num = num;
            this.index= index;
        }
    }
}
