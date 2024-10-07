import java.io.*;
import java.util.*;

class Solution {
    boolean solution(String s) {
        
        Queue<Character> q = new LinkedList<>();
        for (int i =0; i < s.length(); i++){
            char now = s.charAt(i);
            
            if (now == '('){
                q.add(now);
            } else{
                if(!q.isEmpty()){
                   q.poll(); 
                } else{
                    return false;
                }
            }
        }
        
        if (q.size() == 0){
            return true;
        } else{
            return false;
        }
    }
}