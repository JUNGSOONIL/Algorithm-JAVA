package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3187양치기꿍 {

	static int R, C, ans_K, ans_V,  k, v, dy[]= {1,-1,0,0}, dx[]= {0,0,-1,1};
	static char[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != '#' && !visit[i][j]) { //양 늑대수 체크하고 비교해서 최종값 갱신
					k = 0;
					v = 0;
					dfs(i,j);
					ans_K += k > v ? k : 0;
					ans_V += v >= k ? v : 0;
				}
			}
		}
		System.out.println(ans_K + " " + ans_V);
	}
	private static void dfs(int y, int x) {
		visit[y][x] = true; // 방문처리하고 양이나 늑대 수 증가시킴
		if(map[y][x] == 'v')
			v++;
		else if(map[y][x] == 'k') {
			k++;
		}

		for (int i = 0; i < 4; i++) {
			int ny = y+ dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == '#' || visit[ny][nx])
				continue;
			dfs(ny,nx);
		}
	}
}
