import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int ans, dy[] = { -1, 1, 0, 0 }, dx[] = { 0, 0, -1, 1 };
	static char map[][] = new char[12][6];
	static Queue<Node> q = new LinkedList<>();
	static boolean visit[][][], check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) { // 입력 처리 
			map[i] = br.readLine().toCharArray();
		}

		while (true) {
			visit = new boolean[12][6][3]; // 매 턴마다 방문 배열 초기화 
			check = false; // 매 턴마다 연쇄 여부 체크 변수 초기화 
			// 1. 먼저 map 돌면서 4이상 같은색인애들 찾기
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						int d = map[i][j] == 'R' ? 0 : map[i][j] == 'G' ? 1 : 2;
						if(visit[i][j][d]) // 이미 방문한적 있으면 다음 반복문 진행
                            				continue;
                        			dfs(i, j, map[i][j], d);
						if (q.size() >= 4) // 큐의 사이즈가 4 이상이면 4개이상 모인경우 연쇄 시킴 
							bomd();
						else // 4 보다 작으면 연쇄 불가능 큐를 초기화해줌 
							q.clear();
					}
				}
			}

			if (check) { // 만약 연쇄된것이 있다면 빈공간 아래로 내리고 카운트 증가 시킴 
				move();				
				ans++;
			}
			else { // 연쇄가 없다면 종료  
				break;
			}
		}
		System.out.println(ans);
	}

	private static void move() { // 배열을 왼쪽 아래 한칸위부터 체크하면서 만약 빈공간이 아니고 아래가 빈공간이면 몇칸 빈공간있는지 체크해서 내려주기 
		for (int i = 10; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				int count = 0;
				if (map[i][j] != '.' && map[i + 1][j] == '.') {
					for (int k = 1; k < 12; k++) {
						if (i + k < 12 && map[i + k][j] == '.')
							count = k;
						else
							break;
					}
					if (count != 0) {
						map[i + count][j] = map[i][j];
						map[i][j] = '.';
					}
				}
			}
		}

	}

	private static void bomd() {
		check = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			map[n.y][n.x] = '.';
		}
	}

	private static void dfs(int y, int x, char c, int d) {
		q.add(new Node(y, x));
		visit[y][x][d] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6 || visit[ny][nx][d] || map[ny][nx] != c)
				continue;
			dfs(ny, nx, c, d);
		}
	}

	static class Node {
		int y, x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
