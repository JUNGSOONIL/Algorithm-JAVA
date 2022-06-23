import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int ans, map[][], dy[]= {0, 0, 1, -1, 1, -1, -1, 1 }, dx[]= {1, -1, 1, -1, 0, 0, 1, -1}; //우 좌 우하대각선, 좌상대각선, 하,상, 우상대각선,좌하대각선
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[19][19];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 입력 좌표값 시작이 1,1 이니깐 -1해줌
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			if(i % 2 == 1)
				map[y][x] = 1; // 흑
			else
				map[y][x] = 2; // 백
			if(i >= 10 && check(y,x)) { // 적어도 돌이 10개이상이여야 오목이 가능
				ans = i;
				break;
			}
		}
		ans = ans == 0 ? -1 : ans;
		System.out.println(ans);
	}
	
	private static boolean check(int y, int x) {
		for (int i = 0; i < 8; i=i+2) { // 8방탐색을 할껀데 한번에 두방향 즉 1직선되는 양방향을 체크함
			int cnt = 1;
			int ny = y;
			int nx = x;
			while(true) { // 한쪽을 먼저 체크함
				ny += dy[i];
				nx += dx[i];
				if(ny < 0 || nx < 0 || ny >= 19 || nx >= 19 || map[ny][nx] != map[y][x] || cnt > 5) // 배열 범위 벗어나거나 돌이다르거나 카운트가 5보다 큰경우
					break;
				cnt++;
			}
			int py = y;
			int px = x;
			while(true) { // 체크한쪽 반대 방향을 체크함
				py += dy[i+1];
				px += dx[i+1];
				if(py < 0 || px < 0 || py >= 19 || px >= 19 || map[py][px] != map[y][x] || cnt > 5) // 배열 범위 벗어나거나 돌이다르거나 카운트가 5보다 큰경우
					break;
				cnt++;
			}
			if(cnt == 5) // 오목인경우
				return true;
		}
		return false;
	}
}
