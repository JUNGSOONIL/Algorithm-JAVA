import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M,T,ans, map[][], dy[] = {-1,1,0,0}, dx[] = {0,0,-1,1};
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) { // 입력처리하면서 평균값으로 저장한다.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int sum = Integer.parseInt(st.nextToken())+ Integer.parseInt(st.nextToken())+ Integer.parseInt(st.nextToken());
				map[i][j] = sum / 3;
			}
		}
		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) { // T값 이상인 값들은 255로 아닌값은 0으로 변경한다
			for (int j = 0; j < M; j++) {
				if(map[i][j] >= T)
					map[i][j] = 255;
				else
					map[i][j] = 0;
			}
		}
		for (int i = 0; i < N; i++) { // dfs돌면서 체크한다
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 255 && !visit[i][j]) {
					dfs(i,j);
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	private static void dfs(int y, int x) {
		visit[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 0)
				continue; // 배열 범위 벗어나거나 이미 방문했거나 0이면 다음반복 진행
			dfs(ny,nx);
		}
	}
}
