import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, D, ans, map[][], out[] = new int[3];
	static List<Node> list = new LinkedList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.d == b.d ? a.x - b.x : a.d - b.d);
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0); // 조합 (1,2,3 ~ M-2, M-2, M)
		System.out.println(ans);
	}
	
	private static void comb(int index, int target) { // 조합으로 3 궁수의 위치를 만듬.
		if(target == 3) {
			check(); // 조합이 완성되면 체크하기 
			return;
		}
		if(index == M)
			return;
		
		out[target] = index;
		comb(index+1, target+1);
		comb(index+1, target);
	}

	private static void check() {
		list.clear(); // 적이 담긴리스트 초기화 (이전값들 삭제)
		for (int i = 0; i < N; i++) { // 적 위치 경신 
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1)
					list.add(new Node(i,j));
			}
		}
		int cnt = 0;
		while(true) {
			for (int i = 0; i < 3; i++) {
				pq.clear(); // 궁수마다 적의 거리가 다르기때문에 초기화 실시 
				
				for (int j = 0; j < list.size(); j++) {
					Node n = list.get(j);
					n.d = Math.abs(n.x - out[i]) + Math.abs(n.y - N); // 적 위치 즉 거리를 경신한다.
				
					if(n.d <= D) // 죽일수 있으면 우선순위 큐에 담음 
						pq.add(n);
				}
				if(!pq.isEmpty()) // 가장 첫번째 적만 죽임 우선순위 큐에의해 자동 정렬 
					pq.poll().life = true;
			}
			for (int i = 0; i < list.size(); i++) { // 여기는 적 이동 및 사망된적 삭제 
				Node n = list.get(i);
				if(n.life) { // 적이 죽은 경우 
					list.remove(n);
					cnt++;
					i--; // 위에서 리스트를 하나 제거했기때문에 사이즈가 1 줄어듬 
				}else if(n.y == N-1) {// 이제 성밖으로 이동한경우 
					list.remove(n);
					i--;
				}else // 적 한칸씩 이동 
					n.y++;
			}
			if(list.size() == 0) // 적이 모두 죽거나 성밖으로 나가면 종료 
				break;
		}
		ans = Math.max(ans, cnt);
	}

	static class Node{
		int y,x,d;
		boolean life;
		Node(int y,int x){
			this.y = y;
			this.x = x;
		}
	}

}
