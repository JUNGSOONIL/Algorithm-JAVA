import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int R, C,dy[]= {-1,1,0,0} ,dx[]= {0,0,-1,1};
	static char[][] map;
	static boolean visit[][], fly_Check;
	static List<Node> list = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int T = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int t = 0; t < T; t++) {
			int f = Integer.parseInt(st.nextToken());
			if(t%2 == 0) {// 왼쪽에서 시작 
				bomb(f,true);
			}else {// 오른쪽에서 시작 
				bomb(f,false);
			}
			// 여기서 미네랄 떠있는거체크
			visit = new boolean[R][C];
			roop:
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] =='x' && !visit[i][j]) {
						check(i,j);
						if(fly_Check) { // 공중에 떠있지 않는 클러스터 이므로 리스트 초기화시킴 
							list.clear();							
							fly_Check = false;
						}
						else { // 공중에 떠있는 경우 이제 몇번 이동가능하지 체크하고 이동 + 반복문 종료 
							move();
							break roop;
						}
					}
				}
			}
		}
		print(); // 최종 출력 
	}

	private static void move() {
		int ans = Integer.MAX_VALUE;
		for (Node n : list)  { // map 에서 클러스터 값 초기화 해줌 
			map[n.y][n.x] = '.'; 
		}
		for (Node n : list) { // 클러스터를 하나씩 빼서 최대경우만큼 이동시켜보면서 값을 이동할수있는 값을 경신한다. 
			for (int i = 1; i < R; i++) {
				if(n.y+i >= R || map[n.y+i][n.x] == 'x') {
					ans = Math.min(ans, i-1); // 더이상 이동 못하는 경우에서 -1뺀 값이랑 기존값중 최소값으로 경신 
					break;
				}
			}
		}
		for (Node n : list)  { // 이동한거 map 에 경신 
			map[n.y+ans][n.x] = 'x'; 
		}
		list.clear(); // 리스트는 초기화해야지 다음 경우에 클러스터 입력 가능 
	}

	private static void check(int y, int x) { //dfs 를 이용하여 떠있는 클러스터를 체크한다.
		visit[y][x] = true;
		if(y == R-1) // 만약 미네랄이 하나라도 바닥에 닿았다면 공중에 떠있지 않은 클러스터 
			fly_Check = true;
		list.add(new Node(y,x));
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= R || nx >= C || visit[ny][nx] || map[ny][nx] == '.')
				continue;
			check(ny,nx);
		}
	}

	private static void bomb(int f, boolean b) {
		f = (R-1) - (f-1); // 문제는 아래에서부터 1 2 3 이지만 배열은 위에서부터 1 2 3 이라서 계산해주
		if(b) { // 왼쪽 오른쪽에 따라 미네랄을 하나 제거하기 
			for (int i = 0; i < C; i++) {
				if(map[f][i] == 'x') {
					map[f][i] = '.';
					return;
				}
			}
		}
		else {
			for (int i = C-1; i >= 0; i--) {
				if(map[f][i] == 'x') {
					map[f][i] = '.';
					return;
				}
			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
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
