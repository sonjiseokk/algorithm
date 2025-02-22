import java.io.*;
import java.util.*;

public class Main {
    static List<Room> gameSet;
    static int n, attack;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        attack = Integer.parseInt(st.nextToken());
        
        // 게임 추가
        gameSet = new ArrayList<>();
        for (int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            gameSet.add(new Room(type, a, h));
        }
        
        find (1, Long.MAX_VALUE);
        
        
    }
    
    public static void find(long min, long max){        
        // min이 더 크거나 같다는 건 찾았다는 거
        // 출력
        if (min >= max){
            System.out.println(min);
            return;
        }
 
        long nowAttack = attack;
        // 중간 찾아
        long mid = (min + max) / 2;
               
        // 중간값이 방 다 돌 수 있는지 확인해
        if (run(mid, nowAttack)){
            // 돌 수 있으면 범위 줄여
            find(min, mid);
        } else{
            // 못돌면 범위 늘려
            find(mid +1, max);
        }
    }
    
    public static boolean run(long hp, long nowAttack){
        long maxHp = hp;
        for (Room room : gameSet){
            // 몬스터를 만난 경우
            if (room.type == 1){
                // 턴 계산
                long turn = (long) Math.ceil((double) room.h / nowAttack);
                hp -= (turn - 1) * room.a;
                // 용사 0이면 죽음
                if (hp <= 0) return false;
            } else {
                // 포션을 만난 경우 
                // Hp 회복
                hp = Math.min(maxHp, hp + room.h);
                
                // 공격력 증가
                nowAttack += room.a;
            }
        }
        
        return true;
    }
    
    static class Room {
        int type;
        int a;
        int h;
        
        public Room(int type, int a, int h){
            this.type = type;
            this.a = a;
            this.h = h;
        }
    }
}
