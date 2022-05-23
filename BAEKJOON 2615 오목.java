import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int map[][] = new int[19][19];
	static int[] dy = { 0, 0, 1, -1, 1, -1, -1, 1 }, dx = { 1, -1, 1, -1, 0, 0, 1, -1 }; //우 좌 우하대각선, 좌상대각선, 하,상, 우상대각선,좌하대각선
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if(map[i][j] != 0) // 바둑돌이면 체크함
					check(i,j);
			}
		}
		System.out.println(0);
	}
	private static void check(int y, int x) {
		for (int i = 0; i < 8; i = i + 2) { // 우, 우하대각선, 하, 우상대각선만 체크하면된다.
			int ny = y;
			int nx = x;
			int cnt = 1;
			while (true) {// 시작좌표에서 같은방향으로 계속 탐색
				ny = ny + dy[i];
				nx = nx + dx[i];
				if (ny < 0 || nx < 0 || ny >= 19 || nx >= 19 || map[ny][nx] != map[y][x]) { // 범위 벗어나거나 값다르면 멈춤
					break;
				}
				cnt++;
			}
			if (cnt == 5) { // 카운트가 5면 오목인경우임 이제 반대방향 하나의 값을 체크해줌
				int nyy = y + dy[i + 1]; // 시작좌표에서 반대방향
				int nxx = x + dx[i + 1]; // 시작좌표에서 반대방향
				if (nyy < 0 || nxx < 0 || nyy >= 19 || nxx >= 19 || map[nyy][nxx] != map[ny+dy[i+1]*5][nx+dx[i+1]*5]) { // 반대방향이 범위를 벗어나거나 값이 다르면 성공
					System.out.println(map[ny+dy[i+1]*5][nx+dx[i+1]*5]); // 해당 바둑돌 출력
					System.out.println((ny+dy[i+1]*5+1) + " " + (nx+dx[i+1]*5+1)); // 좌표출력해주는데 +1씩해줌 0,0으로 했기때문
					System.exit(0);
				}
			}
		}
	}
}
