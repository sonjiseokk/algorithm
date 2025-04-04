import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        // 0 : 실제 방 번호
        // 1 : 끝나는 시간
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            rooms.add(new Room(start, end));
        }
        
        // 끝나는 시간 기준으로 정랼
        Collections.sort(rooms);
        
        int cnt = 1;
        int minEndTime = rooms.get(0).end;
        
        for (int i = 1; i < n; i++){
            Room now = rooms.get(i);

            // 이전 회의가 안끝난 경우
            if (minEndTime > now.start){
                continue;
            } else{
                cnt ++;
                minEndTime = now.end;
            }
        }
        
        System.out.println(cnt);
    }
    static class Room implements Comparable<Room> {
        int start;
        int end;
        public Room(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Room o){
            // 오름차순
            if (this.end == o.end){
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }
}
