import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, dy[]= {-1,1,0,0}, dx[] = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visit;
	static Queue<Node> jq = new LinkedList<>(); // 지훈이 담을 큐
	static Queue<Node> fq = new LinkedList<>(); // 불 담을 큐
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
				if(map[i][j] =='J') {
					jq.add(new Node(i,j,1)); // 지훈이의 이동 시작을 1로해서 큐에 담음
					visit[i][j] = true;
				}else if(map[i][j] =='F') {
					fq.add(new Node(i,j));
				}
			}
		}
		boolean check = false; // 지훈이 도착여부 확인 변수
		while(true) {
			fdfs(); // 먼저 불에대해서 큐에서 하나씩 빼내어 이동시켜준다.
			if(!jq.isEmpty()) { // 지훈이 큐가 안비었으면 지훈이큐에서 하나씩 빼내어 이동시켜준다.
				check = jdfs();
			}else { // 지훈이 큐가 비었으면 더이상 이동불가 실패
				System.out.println("IMPOSSIBLE");
				break;
			}
			if(check) // 이미 탈출한상태 반복문 빠져나옴
				break;
			//2. 불큐 확산시킴
			//1. 지훈이 큐에서 값있으면 지훈이 이동시킴
			//1-1. 지훈이 큐 비었으면 종료 (실패)
			
		}
	}
	private static void fdfs() {
		int size = fq.size(); // 한사이클만 돌기위해 최초 큐의 사이즈만큼 반복
		for (int s = 0; s < size; s++) {
			Node n = fq.poll();
			for (int i = 0; i < 4; i++) {
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] =='#' || map[ny][nx] =='F') // 벽이거나 불이면 이동불가 map 배열로 방문체크
					continue;
				fq.add(new Node(ny,nx));
				map[ny][nx] = 'F';
			}
		}
	}
	private static boolean jdfs() {
		int size = jq.size(); // 한사이클만 돌기위해 최초 큐의 사이즈만큼 반복
		for (int s = 0; s < size; s++) {
			Node n = jq.poll();
			if(n.y == 0 || n.x ==0 || n.y == R-1 || n.x == C-1) { // 외각선은 총 4군데 잘 체크하기!
				System.out.println(n.n);
				return true;
			}
			for (int i = 0; i < 4; i++) {
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] !='.' || visit[ny][nx]) // visit를 이용해 방문 체크
					continue;
				jq.add(new Node(ny,nx,n.n+1));
				visit[ny][nx] = true;
			}
		}
		return false;
	}
	static class Node{
		int y, x, n;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
		Node(int y, int x, int n){
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}
}
