import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, arr[], ans = 1; // 시작점이 있으므로 카운트 1에서 시작
	static boolean visit[];
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		visit = new boolean[N+1];
		for (int i = 1; i <=N; i++) { // 입력값 처리
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int n = Integer.parseInt(br.readLine()); // 시작점 갱신
		q.add(n); // 시작점 큐에 담고 bfs 돌리기
		visit[n] = true;
		bfs();
		System.out.println(ans);
	}
	private static void bfs() {
		while(!q.isEmpty()) {
			int n = q.poll();
			if(n + arr[n] <= N && !visit[n + arr[n]]) { // 배열 범위 안이고 아직 방문안한경우
				visit[n + arr[n]] = true;
				q.add(n + arr[n]);
				ans++;
			}
			if(n-arr[n] > 0 && !visit[n - arr[n]]) {  // 배열 범위 안이고 아직 방문안한경우
				visit[n - arr[n]] = true;
				q.add(n - arr[n]);
				ans++;
			}
		}
	}
}
