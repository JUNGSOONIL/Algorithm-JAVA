import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, W, B, cnt, dy[]={-1,1,0,0}, dx[]= {0,0,-1,1};
	static boolean[][] visit;
	static char[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());  // N M 가로 세로 체크!!!!!!
		N = Integer.parseInt(st.nextToken()); 
		map = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray(); // String를 char형 배열로 변환
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visit[i][j]) { // 방문을 안했으면
					cnt = 0; // cnt 초기화하고
					dfs(i,j,map[i][j]); //dfs 돌림
					if(map[i][j] == 'W') // W, B에 따라 cnt 계산값을 추가
						W += cnt*cnt;
					else
						B += cnt*cnt;
				}
			} 
		}
		System.out.println(W +" "+ B);
	}
	private static void dfs(int i, int j, char c) {
		cnt++; // cnt 증가시킴 연결되어있는 칸 갯수
		visit[i][j] = true; // 방문 처리
		for (int k = 0; k < 4; k++) {
			int ny = i + dy[k];
			int nx = j + dx[k];
			if(ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] != c) // 범위 벗어나거나 방문했거나 다른팀인경우 
				continue;
			dfs(ny,nx,c);
		}
	}
}
