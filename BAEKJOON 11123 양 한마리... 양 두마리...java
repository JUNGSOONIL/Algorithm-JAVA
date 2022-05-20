import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static char[][] map;
	static int N, M, dy[] = {-1,1,0,0}, dx[] = {0,0,-1,1};
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int cnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!visit[i][j] && map[i][j] == '#') { // 방문 안했고 양인경우
						dfs(i,j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	private static void dfs(int y, int x) {
		visit[y][x]= true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny <0 || nx <0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] != '#') // 배열범위 벗어나거나 이미 방문했거나 양이 아닌경우
				continue;
			dfs(ny,nx);
		}
	}
}
