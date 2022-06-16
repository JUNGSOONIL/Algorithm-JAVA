import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int city[][] = new int[N+1][3];
		for (int i = 1; i <=N; i++) { // 배열에 도시 정보를 저장
			st = new StringTokenizer(br.readLine());
			city[i][0] = Integer.parseInt(st.nextToken());
			city[i][1] = Integer.parseInt(st.nextToken());
			city[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int map[][] = new int[N+1][N+1];
		
		for (int i = 0; i < N; i++) { // 플로이드를 돌리기위해 도시별 이동 정보를 갱신
			for (int j = 0; j < N; j++) {
				if(i == j)
					continue;
				// 먼저 두 도시에대해서 거리를 저장함
				map[i+1][j+1] = map[j+1][i+1] = Math.abs(city[i+1][1] - city[j+1][1]) + Math.abs(city[i+1][2] - city[j+1][2]);
				if(city[i+1][0] + city[j+1][0] == 2 && map[i+1][j+1] > T) // 두도시가 특별한 도시고 거리가 T보다 멀면 T로 갱신
					map[i+1][j+1] = map[j+1][i+1] = T;
			}
		}
		
		for (int k = 1; k <=N; k++) { // 플로이드 와샬 적용
			for (int i = 1; i <=N; i++) {
				if(k == i)
					continue;
				for (int j = 1; j <=N; j++) {
					if(k == j || i == j)
						continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine()); // 출력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(map[a][b]);
		}
	}
}
