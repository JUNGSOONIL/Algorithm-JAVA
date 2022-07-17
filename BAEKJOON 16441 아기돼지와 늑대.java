import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, dy[]={0,-1,1,0,0} , dx[]={0,0,0,-1,1};
    static char[][] map;
    static boolean[][][] visit; // 3차원으로 방문 체크
    static Queue<Wolf> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M][5]; // 0은 첫번째 입력받을때 체크한곳 벽이랑 늑대

        for (int i = 0; i < N; i++) { // 입력처리
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(map[i][j] != '.' && map[i][j] !='+') // 초원이랑 빙판이 아니면 방문 처리
                    visit[i][j][0] = true;
                if(map[i][j] == 'W') // 늑대 좌표를 큐에 담아둔다.
                    queue.add(new Wolf(i,j));
            }
        }

        bfs();
        print();
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!(visit[i][j][0] || visit[i][j][1] || visit[i][j][2] || visit[i][j][3] || visit[i][j][4]) && map[i][j] =='.')
                    System.out.print("P");
                else
                    System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void bfs() { // bfs 돌림
        while (!queue.isEmpty()){
            Wolf wolf = queue.poll();
            for (int i = 1; i <=4; i++) {
                int ny = wolf.y + dy[i];
                int nx = wolf.x + dx[i];
                if(visit[ny][nx][i] || visit[ny][nx][0]) // 해당 방향이랑 처음 입력값 방문체크 배열 둘중 하나라도 방문하면 넘어감
                    continue;
                if(map[ny][nx] == '.'){ // 초원이면 현제 방향으로 방문처리하고 큐에담음
                    visit[ny][nx][i] = true;
                    queue.add(new Wolf(ny,nx));
                }else if(map[ny][nx] == '+'){ // 빙판이면 미끌어지기
                    while (true){
                        visit[ny][nx][i] = true; // 미끌어지면서 모두 방문체크해줌 해당 방향으로
                        ny = ny + dy[i];
                        nx = nx + dx[i];
                        if(map[ny][nx] == '#') { // 만약 산을 만나면 한칸 이전으로 이동해서 큐에 담아준다.
                            queue.add(new Wolf(ny - dy[i], nx - dx[i]));
                            break;
                        }
                        else if (map[ny][nx] == '.' || map[ny][nx] =='W') { // 초원을 만나는 경우
                            visit[ny][nx][i] = true;
                            queue.add(new Wolf(ny,nx));
                            break;
                        }
                    }
                }
            }
        }
    }

    static class Wolf{
        int y, x;
        Wolf(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
