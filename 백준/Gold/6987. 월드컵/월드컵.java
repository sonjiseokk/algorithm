import java.io.*;
import java.util.*;

public class Main {
    static boolean isPossible;
    static int[][] matchs;
    static int[][] results;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 매치 뽑아서 저장
        init();
        
        
        // 게임 세트 입력받음
        for (int i = 0; i < 4; i++){
            
            // 가지치기용 변수
            int totalGame = 0;
            int totalWin = 0;
            int totalDraw = 0;
            int totalLose = 0;
            boolean isNotFive = false;
        
            results = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++){
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());
                
                totalGame += win+draw +lose;
                totalWin += win;
                totalDraw += draw;
                totalLose += lose;
                
                // 경기 결과 배열에 저장
                results[j][0] = win;
                results[j][1] = draw;
                results[j][2] = lose;
                
                // 4. 국가당 승무패합이 5여야함
                if (win + draw + lose != 5) isNotFive = true; 
            }
            // 가지치기 검증
            // 1. 승무패 합쳐서 30이 넘으면 안됨
            // 2. 승합과 패합이 같아야함
            // 3. 무합이 짝수여야함
            // 4. 국가당 승무패합이 5여야함
            if (totalGame != 30 || totalWin != totalLose || totalDraw % 2 != 0 || isNotFive) {
                sb.append("0 ");
                continue;
            }
            
            isPossible = false;
            simulate(0);
            // 시뮬레이션
            if(isPossible) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }
        
        System.out.println(sb.toString());
        
    }
    
    static void simulate(int matchIndex){
        // 이미 끝난 경우
        if (isPossible) return;
        
        // 만약 전부 플레이한 경우
        if (matchIndex == 15){
            isPossible = true;
            return;
        }
        
        // 대결 팀 결정 (인덱스 기준)
        int firstTeam = matchs[matchIndex][0];
        int secondTeam = matchs[matchIndex][1];
        
        // 첫번째 팀이 이긴 경우
        if (results[firstTeam][0] > 0 && results[secondTeam][2] > 0){
            results[firstTeam][0]--;
            results[secondTeam][2]--;
            simulate(matchIndex + 1);
            results[firstTeam][0]++;
            results[secondTeam][2]++;
        }
        
        // 둘이 비긴 경우
        if (results[firstTeam][1] > 0 && results[secondTeam][1] > 0){
            results[firstTeam][1]--;
            results[secondTeam][1]--;
            simulate(matchIndex + 1);
            results[firstTeam][1]++;
            results[secondTeam][1]++;
        }
        
        // 두번째 팀이 이긴 경우
        if (results[firstTeam][2] > 0 && results[secondTeam][0] > 0){
            results[firstTeam][2]--;
            results[secondTeam][0]--;
            simulate(matchIndex + 1);
            results[firstTeam][2]++;
            results[secondTeam][0]++;
        }
    }
    
    static void init(){
        int size = 0;
		for(int i = 1; i < 6; i++) {
			size += i;
		}
        
        matchs = new int[size][2];
        int idx = 0;
        for (int i = 0; i < 6; i ++){
            for (int j = i+1; j < 6; j++){
                matchs[idx][0] = i;
                matchs[idx][1] = j;
                idx++;
            }
        }
    }
    


}
