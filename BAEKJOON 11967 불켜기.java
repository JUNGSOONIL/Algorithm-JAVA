import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt = 1, dy[]= {-1,1,0,0} , dx[]= {0,0,-1,1};
	static boolean[][] map, visit, all_visit; //불이 켜진 맵, bfs용 방문체크, 최종 방문체크
	static ArrayList<Node>[][] list; //리스트를 2차원 배열로 만들고 안에 값을 넣는다.
	static Queue<Node> all_q = new LinkedList<>(); // 불 켜야하는 애들
	static Queue<Node> q = new LinkedList<>(); // bfs용
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][N];
		all_visit = new boolean[N][N];
		list = new ArrayList[N][N];
		for (int i = 0; i < N; i++) { // 리스트 배열 초기화
			for (int j = 0; j < N; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sY = Integer.parseInt(st.nextToken()) -1; // 문제는 1,1부터지만 0,0을 시작으로 하기위해
			int sX = Integer.parseInt(st.nextToken()) -1;
			int eY = Integer.parseInt(st.nextToken()) -1;
			int eX = Integer.parseInt(st.nextToken()) -1;
			if(sY + sX == 0) { // 더한값이 0이면 0,0인 상태 갈수있는곳 먼저 큐에 담음
				all_q.offer(new Node(eY,eX));
				continue;
			}
			list[sY][sX].add(new Node(eY,eX)); // 리스트에 값 추가해주기
		}

		map[0][0] = true; //map과 최종 방문체크를 true해줌
		all_visit[0][0] = true;
		while(true) {
			if(all_q.isEmpty()) // 큐가 비었으면 종료
				break;
			switch_on(); // 불을 켜준다
			bfs(); // bfs 돌림
		}
		System.out.println(cnt);
	}
	
	private static void bfs() {
		q.add(new Node(1,1)); // 시작점 갱신
		visit = new boolean[N][N]; // bfs용 방문체크 갱신
		while(!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if(ny < 0 || nx < 0 || ny >= N || nx >= N || !map[ny][nx] || visit[ny][nx])
					continue;
				if(!all_visit[ny][nx]) { // 처음 방문인 경우
					all_visit[ny][nx] = true; // 방문 체크
					all_q.addAll(list[ny][nx]); // 리스트에서 해당 좌표에서 갈수있는애들 모두 불켜라고 등록
				}
				visit[ny][nx] = true;	
				q.add(new Node(ny,nx));
			}
		}
	}

	private static void switch_on() {
		while(!all_q.isEmpty()) { // 불켜야할거 있는지 체크
			Node n = all_q.poll();
			if(!map[n.y][n.x]) { // 이미 불 켜져있는지 체크
				map[n.y][n.x] = true; // 불 키고 카운트 증가
				cnt++;
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
