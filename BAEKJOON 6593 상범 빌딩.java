import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C, dy[]={-1,1,0,0,0,0}, dx[]={0,0,-1,1,0,0}, dh[]={0,0,0,0,-1,1}; // 3차원 좌표 체크
    static char[][][] map;
    static boolean[][][] visit;
    static Queue<Node> queue;
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0 && R==0 && C==0)
                break;

            map = new char[L][R][C];
            visit = new boolean[L][R][C];
            queue = new LinkedList<>();

            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    map[k][i] = br.readLine().toCharArray(); // map 에 데이터 넣어줌
                    for (int j = 0; j < C; j++) {
                        if(map[k][i][j] == 'S'){ // 시작 좌표 큐에 넣고 방문 체크함
                            queue.add(new Node(k,i,j,0));
                            visit[k][i][j] = true;
                        }
                    }
                }
                br.readLine(); // 한줄쓰기
            }
            int ans = bfs(); // bfs 돌림
            if(ans == -1) // 실패한경우
                System.out.println("Trapped!");
            else // 성공한경우
                System.out.println("Escaped in "+ans+" minute(s).");
        }
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (map[n.d][n.y][n.x] == 'E') // 도착하면 리턴
                return n.n;

            for (int i = 0; i < 6; i++) {
                int ny = n.y + dy[i];
                int nx = n.x + dx[i];
                int nh = n.d + dh[i];
                if (ny < 0 || nx < 0 || nh < 0 || ny >= R || nx >= C || nh >= L)
                    continue; // 배열 범위 벗어날경우
                if (visit[nh][ny][nx] || map[nh][ny][nx] == '#')
                    continue; // 방문했거나 금인경우
                queue.add(new Node(nh, ny, nx, n.n + 1));
                visit[nh][ny][nx] = true;
            }
        }
        return -1;
    }

    static class Node{
        int d, y, x, n;
        Node(int d, int y , int x, int n){
            this.d = d;
            this.y = y;
            this.x = x;
            this.n = n;
        }
    }
}
