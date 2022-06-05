import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans, dx[] = { 0, 0, 1, -1, 1, 1, -1, -1 }, dy[] = { 1, -1, 0, 0, 1, -1, -1, 1 };
	static int[][] map, cnt;
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cnt = new int[N][M];
		
		for (int i = 0; i < N; i++) { // 입력 처리하면서 상어의 위치를 큐에 담음
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.add(new Node(i, j));
				}
			}
		}
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {// 상어들을 빼내면서 갈수있는애들을 카운트 증가시켜줌
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 8; i++) {
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) 
					continue;
				if (cnt[ny][nx] != 0 || map[ny][nx] == 1)
					continue;
				cnt[ny][nx] = cnt[n.y][n.x] + 1;
				q.add(new Node(ny, nx));
				ans = Math.max(ans, cnt[ny][nx]);
			}
		}
	}

	static class Node {
		int y, x;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
