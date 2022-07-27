import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int M,N,H,ans, map[][][], dy[]={0,0,-1,1,0,0}, dx[]={-1,1,0,0,0,0}, dh[]={0,0,0,0,1,-1}; // 3차원이다보니 높이까지 추가해줌 
    static Queue<Tomato> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[N][M][H];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1) { // 익은 토마토에 대해서 큐에 담아줌
                        queue.add(new Tomato(i,j,k));
                    }
                }
            }
        }
        bfs(); //bfs 돌린다.

        point :
        for (int k = 0; k < H; k++) { // 배열을 탐색하면서 걸린날짜를 갱신한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j][k] == 0){ // 만약 안익은 토마토가 존재하면 반복문 종료하고 결과값 -1로 경신
                        ans = -1;
                        break point;
                    }
                    ans = Math.max(ans,map[i][j][k]-1); // 최대값을 계속 경신
                }
            }
        }
        System.out.println(ans); // 최종 출력
    }

    private static void bfs() {
        while (!queue.isEmpty()){
            Tomato to = queue.poll();
            for (int i = 0; i < 6; i++) { // 상 하 좌 우 3차원 위아래까지 체크
                int ny = to.y + dy[i];
                int nx = to.x + dx[i];
                int nh = to.h + dh[i];

                if(ny <0 || nx <0 || nh <0|| ny >= N || nx >= M || nh >= H) // 배열의 범위를 벗어나는 경우
                    continue;
                if(map[ny][nx][nh] != 0) // 가려는 곳이 익지않은 토마토가 아닌경우
                    continue;
                map[ny][nx][nh] = map[to.y][to.x][to.h] + 1; // 방문하는데 걸린 날짜 경신 이걸 통해 방문체크 완료
                queue.add(new Tomato(ny,nx,nh)); // 큐에 담는다.
            }
        }
    }

    static class Tomato{
        int y, x,h;
        Tomato(int y, int x, int h){
            this.y = y;
            this.x = x;
            this.h = h;
        }
    }
}
