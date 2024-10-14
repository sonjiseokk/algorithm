import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        List<Selection> list = new ArrayList<>();
        int answer = 0;
        
        // 리스트에 객체 형태로 추가
        for(int i =0; i< targets.length; i++){
            list.add(new Selection(targets[i][0], targets[i][1]));
        }
        
        
        // 끝나는 길이 기준으로 정렬
        Collections.sort(list, (x,y) -> x.e - y.e);
        
        // 끝인 애를 계속해서 갱신. 
        // 다음 들어온 타겟의 시작이 끝보다 크다면 연결이 끊긴것
        // 이때부터 카운트에 들어가는 것
        int prevEnd = 0;
        for(Selection selection : list){
            // 다음 들어온 타겟의 시작이 끝보다 크다면 연결이 끊긴것
            if(selection.s < prevEnd){
                continue;
            }
            
            // 연결이 끊겼다!
            prevEnd = selection.e;
            answer += 1;
        }
        
        return answer;
    }
}

class Selection{
    int s,e;

    public Selection(int s, int e){
        this.s = s;
        this.e = e;
    }
}

