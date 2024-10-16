import java.io.*;
import java.util.*;

class Solution {
    private static int cnt;
    private static int result;
    private static String[] dics = {"A","E","I","O","U"};
    public int solution(String word) {
        dfs(new ArrayList<>(), word);
        return result;
    }
    
    private void dfs(List<String> now ,String word){
        if(now.size() > 5){
            return;
        }

        // 정답 체크
        if(word.equals(makeWord(now))){
            result = cnt;
            return;
        }
        cnt ++;
        // 자리수 조정
        for(int i = 0; i< 5; i++){
            now.add(dics[i]);


            dfs(now, word);
            now.remove(now.size() - 1);
        }
    }
    
    private String makeWord(List<String> list){
        String result = "";
        
        for(String x : list){
            result+= x;
        }
        return result;
    }
}