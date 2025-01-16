import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // 코드를 작성해주세요
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nowH = Integer.parseInt(st.nextToken());
        int nowM = Integer.parseInt(st.nextToken());
        
        int plusM = Integer.parseInt(br.readLine());
        
        // 시간오바 연산
        int calH = (nowM + plusM) / 60;
        int calM = (nowM + plusM) % 60;
        
        // 현재 시간이랑 더함
        calH += nowH;
        
        // 자정 이후인 경우
        if (calH >= 24) calH -= 24;
        
        System.out.printf("%d %d", calH, calM);
        
    }
}
