import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, map[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) { // 플로이드 와샬을 위해 max값으로 초기화
			for (int j = 0; j < N; j++) {
				if(i!=j)
					map[i][j] = 1000000;
			}
		}
		for (int i = 0; i < M; i++) { // 이동가능한곳은 값 갱신해줌
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken());
			map[y][x] = map[x][y] = v;
		}
		for (int k = 0; k < N; k++) { // 플로이드 와샬 적용
			for (int i = 0; i < N; i++) {
				if(i == k)
					continue;
				for (int j = 0; j < N; j++) {
					if(i == j || j == k)
						continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		int ans[] = new int[N]; // 컴퓨터별 성능 저장
		int min = Integer.MAX_VALUE; // 최소값 저장
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
			ans[i] = sum;
			min = Math.min(min, sum);
		}
		for (int i = 0; i < N; i++) {
			if(min == ans[i]) {
				System.out.println(i+1);
				break;
			}
		}
	}
}
