import java.io.*;
import java.util.*;
class Solution {
    public String solution(String s) throws Exception{
        StringTokenizer st = new StringTokenizer(s);
        int min = Integer.MAX_VALUE;
        int max = -Integer.MIN_VALUE;
        while(st.hasMoreTokens()){
            int x = Integer.parseInt(st.nextToken());
            // 현재의 최소보다 작다면
            if (min > x){
                min = x;
            }
            
            // 현재의 최대보다 크다면
            if(max < x){
                max = x;
            }
        }
        
        return min + " " + max;
    }
}