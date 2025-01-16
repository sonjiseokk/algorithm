import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        // 코드를 작성해주세요
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        // 4배수 o, 100 배수 x
        if (is4okAnd100No(num) || is400ok(num)){
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
    
    private static boolean is4okAnd100No(int num){
        return num % 4 == 0 && num % 100 != 0;
    }
    
    private static boolean is400ok(int num){
        return num % 400 == 0;
    }
}
