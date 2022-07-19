import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T, ans ,map[][], dy[]={-1,1,0,0} , dx[]={0,0,-1,1};
    static boolean[][][] visit; // 칼을 먹은경우와 안먹은 경우 체크를 위해 3차원 배열 사용
    static Queue<Hero> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M][2]; // 0은 칼못먹었을때 1은 칼먹었을때

        for (int i = 0; i < N; i++) { // 입력처리
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = bfs();
        if(ans == -1)
            System.out.println("Fail");
        else
            System.out.println(ans);
    }

    private static int bfs() {
        visit[0][0][0] = true;
        queue.add(new Hero(0,0,0,false));
        while (!queue.isEmpty()){
            Hero hero = queue.poll();
            if(map[hero.y][hero.x] == 2) // 위치가 칼일경우 객체 sword값을 변경한다.
                hero.sword = true;
            if(hero.y == N - 1 && hero.x == M - 1) { // 도착한경우 걸린시간을 체크해서 리턴해준다.
                if (hero.n <= T)
                    return hero.n;
                return -1;
            }
            for (int i = 0; i < 4; i++) {
                int ny = hero.y + dy[i];
                int nx = hero.x + dx[i];
                if(ny <0 || nx <0 || ny >= N || nx >= M) // 배열 범위를 벗어나면 건너뛴다.
                    continue;
                if(hero.sword){ // 칼을 먹은 경우
                    if(!visit[ny][nx][1]){ // 방문했는지만 체크하고 모두 큐에 담는다.
                        visit[ny][nx][1] = true;
                        queue.add(new Hero(ny,nx, hero.n+1, true));
                    }
                }else{ // 칼을 못먹은 경우
                    if(map[ny][nx] == 1 || visit[ny][nx][0]) // 벽인지와 방문했는지를 체크하고 큐에담는다.
                        continue;
                    visit[ny][nx][0] = true;
                    queue.add(new Hero(ny,nx, hero.n+1, false));
                }
            }
        }
        return -1; // 도착하지 못한경우
    }

    static class Hero{
        int y, x, n;
        boolean sword;
        Hero(int y, int x, int n, boolean sword){
            this.y = y;
            this.x = x;
            this.n = n;
            this.sword = sword;
        }
    }
}
