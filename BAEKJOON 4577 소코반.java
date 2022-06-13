import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int R, C, sy,sx, t , dy[] = {-1,1,0,0}, dx[]= {0,0,-1,1};
	static char map[][], cmd[];
	static List<Node> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(R ==0 && C == 0)
				break;
			map = new char[R][C];
			list = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if(map[i][j] == 'w' ) { // 캐릭터 좌표 저장
						sy = i;
						sx = j;
						map[i][j] = '.';
					}else if(map[i][j] == 'W') { // 캐릭터 좌표 저장 + 목표물 좌표 저장
						list.add(new Node(i,j));
						sy = i;
						sx = j;
						map[i][j] = '.';
					}
					else if(map[i][j] == '+') { // 목표물 좌표 저장
						list.add(new Node(i,j));
						map[i][j] = '.';
					}else if(map[i][j] == 'B') { // 박스 데이터 변경 + 목표물 좌표 저장
						list.add(new Node(i,j));
						map[i][j] = 'b';
					}
				}
			}
			cmd = br.readLine().toCharArray(); // 이동해야하는 명령어 저장
			for (int i = 0; i < cmd.length; i++) {
				int n = 0; // 상
				if(cmd[i] =='D') // 하
					n = 1;
				else if(cmd[i] =='L') // 좌
					n = 2;
				else if(cmd[i] =='R') // 우
					n = 3;
				move(n);
				if(check())
					break;
			}
			print();
		}
	}
	
	private static void print() {
		int cnt = 0;
		map[sy][sx] ='w'; 
		for (int i = 0; i < list.size(); i++) {
			Node n = list.get(i);
			if(map[n.y][n.x] == 'b') {
				cnt++;
				map[n.y][n.x] = 'B';
			}else if(map[n.y][n.x] == 'w')
				map[n.y][n.x] = 'W';
			else
				map[n.y][n.x] = '+';
		}
		if(cnt == list.size())
			System.out.println("Game "+(++t)+": complete");
		else
			System.out.println("Game "+(++t)+": incomplete");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static boolean check() { // 목표점에 모드 들어왔는지 확인
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			Node n = list.get(i);
			if(map[n.y][n.x] == 'b') 
				cnt++;
		}
		if(cnt == list.size())
			return true;
		else
			return false;
	}

	
	private static void move(int n) {
		int ny = sy + dy[n];
		int nx = sx + dx[n];
		if(map[ny][nx] == '.') { // 가려는 곳이 이동가능하면 좌표값 갱신
			sy = ny;
			sx = nx;
		}else if(map[ny][nx] == 'b') { // 가려는 곳이 박스면 그다음 까지 체크
			int nny = ny + dy[n];
			int nnx = nx + dx[n];
			if(map[nny][nnx] == '.') { // 그다음 가려는 곳이 이동가능하면 싹 이동시킴
				map[nny][nnx] = 'b';
				map[ny][nx] = '.';
				sy = ny;
				sx = nx;
			}
		}
	}

	static class Node{
		int y, x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
