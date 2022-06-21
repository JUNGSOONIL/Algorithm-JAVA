import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>((a,b) -> a.compareTo(b));
		// 우선 순위큐 선언하면서 오름차순으로 설정
		for (int i = 0; i < N; i++) {
			pq.add(Long.parseLong(br.readLine()));
		}
		long ans = 0; // int형 범위를 벗어남
		while(pq.size() > 1) { // 큐에 값이 하나가 남을때까지 반복
			long a = pq.poll(); 
			long b = pq.poll();
			ans += a + b; // 2개를 빼내고 더한다음 결과에 추가하고 큐에도 담음
			pq.add(a+b);
		}
		System.out.println(ans);
	}
}
