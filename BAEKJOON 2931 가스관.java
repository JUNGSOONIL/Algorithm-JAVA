import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, Y,X,dy[]={-1,0,1,0}, dx[]={0,1,0,-1}; // 상하좌우
    static char map[][], ans;
    static boolean visit[][];
    static Queue<Node> queue = new LinkedList<>();
    static List<Node> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'M' || map[i][j] =='Z'){ // 리스트에 각각의 시작점을 담아둔다.
                    list.add(new Node(i,j));
                }
            }
        }
        bfs(); // bfs를 돌려서 끊어진 부분을 찾는다.
        check(); // 여기서는 끊어진 부분에 들어가 파이으 종류 찾기 
        System.out.println((Y+1) + " "+(X+1)+ " " + ans); // 출력할때 문제는 1,1 시작 나는 0,0 시작이므로 +1해준다
    }

    private static void check() {
        boolean d[] = new boolean[4];
        for (int i = 0; i < 4; i++) { // 끊어진 파이프 4방향을 체크하면서 연결될수있는 부분들에 대해 체크한다.
            int ny = Y + dy[i];
            int nx = X + dx[i];
            if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] =='.')
                continue;
            if(i == 0 && ( map[ny][nx] =='|' || map[ny][nx] =='+' || map[ny][nx] =='1' || map[ny][nx] =='4'))
                d[i] = true;
            else if(i == 2  && (  map[ny][nx] =='|' || map[ny][nx] =='+' || map[ny][nx] =='2' || map[ny][nx] =='3'))
                d[i] = true;
            else if(i == 1 && (  map[ny][nx] =='-' || map[ny][nx] =='+' || map[ny][nx] =='3' || map[ny][nx] =='4'))
                d[i] = true;
            else if(i == 3 && (map[ny][nx] =='-' || map[ny][nx] =='+' || map[ny][nx] =='1' || map[ny][nx] =='2'))
                d[i] = true;
        }
        if(d[0] && d[1] && d[2] && d[3]) // 연결할수있는 파이프 방향을 체크해서 최종 값을 결정한다.
            ans = '+';
        else if(d[1] && d[3])
            ans = '-';
        else if(d[0] && d[2])
            ans = '|';
        else if(d[1] && d[2])
            ans = '1';
        else if(d[0] && d[1])
            ans = '2';
        else if(d[0] && d[3])
            ans = '3';
        else if(d[2] && d[3])
            ans = '4';
    }

    private static void bfs() {
        int index = 0;
        while(queue.isEmpty()){ // 시작점이 두개니깐 둘중 되는걸로 체크
            Node node = list.get(index++);
            visit[node.y][node.x]= true;
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] =='.' || map[ny][nx] =='M' || map[ny][nx] =='Z')
                    continue;
                visit[ny][nx] = true;
                queue.add(new Node(ny,nx)); // 시작점에서 인접한 파이프관 하나를 찾아서 큐에 넣음.
                break;
            }
        }

        while(!queue.isEmpty()){ // 큐가 빌때까지 bfs 돌려준다.
            Node node = queue.poll();
            if(map[node.y][node.x] =='Z' || map[node.y][node.x] =='M') // 큐에 출발점이 담길수도있음 이를 체크해준다
                continue;
            int[] d = null;
            if(map[node.y][node.x] == '|'){ // 각각의 경우의수에 따른 방향값을 배열로 저장한다.
                d = new int[]{0,2};
            }else if(map[node.y][node.x] == '-'){
                d = new int[]{1,3};
            }else if(map[node.y][node.x] == '+') {
                d = new int[]{0,1,2,3};
            }else if(map[node.y][node.x] == '1'){
                d = new int[]{1,2};
            }else if(map[node.y][node.x] == '2'){
                d = new int[]{0,1};
            }else if(map[node.y][node.x] == '3'){
                d = new int[]{0,3};
            }else if(map[node.y][node.x] == '4'){
                d = new int[]{2,3};
            }

            for (int i = 0; i < d.length; i++) { // 위에서 정한 방향만큼만 이동을 실시
                int ny = node.y + dy[d[i]];
                int nx = node.x + dx[d[i]];
                if(visit[ny][nx])
                    continue;
                if(map[ny][nx] =='.') { // 만약 막혀있다면 해당 부분이 끊어진 부분 좌표값 갱신하고 멈춘다.
                    Y = ny;
                    X = nx;
                    return;
                }
                visit[ny][nx] = true;
                queue.add(new Node(ny,nx));
            }
        }
    }
    static class Node{
        int y,x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
