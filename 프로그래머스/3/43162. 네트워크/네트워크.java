class Solution {
    private static boolean[] visited;
    private static int[][] computers;
    public int solution(int n, int[][] cs) {
        visited = new boolean[n];
        computers = cs;
        int cnt = 0;
        for (int i =0; i< n; i++){
            if(!visited[i]){
                // 인덱스로 알아서 찾아 써
                dfs(n,i);
                
                // 만약 한바퀴 돌았다면 이제 카운트
                cnt ++;
            }
        }
        
        return cnt;
    }
    
    private static void dfs(int n, int index){
        // 방문 체크
        visited[index] = true;
        
        // 나 말고 다른 애가 1인 경우가 있나 탐색
        for(int i = 0; i< n; i++){
            if(computers[index][i] == 1 && index != i && !visited[i]){
                dfs(n,i);
            }
        }
    }
}
