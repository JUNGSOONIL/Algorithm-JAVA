import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans =Integer.MAX_VALUE , map[][],copy_Map[][], out[], dy[] = { -1, 0, 1, 0 }, dx[] = { 0, 1, 0, -1 }; // 상 우 하 좌
	static List<Node> list = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) 
					list.add(new Node(map[i][j], i, j)); // 리스트에 cctv 위치와 종류를 담는
			}
		}
		
		out = new int[list.size()];
		perm(0); //먼저 순열을 통해 cctv의 경우의수를 체크한다.
		
		ans = ans == Integer.MAX_VALUE ? 0 : ans;
		System.out.println(ans);
	}

	private static void perm(int index) {
		if(index == list.size()) { //순열이 완성될때마다 시물레이션을 돌려 체크한다 
			watch();
			return;
		}
		for (int i = 0; i < 4; i++) {
			out[index] = i;
			perm(index+1);
		}
	}


	private static void watch() {
		copy_Map = new int[N][M]; //map를 복사한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_Map[i][j] = map[i][j];
			}
		} 
		
		for (int i = 0; i < list.size(); i++) { // cctv종류를 확인하여 감시구역을 체크한다.
			Node n = list.get(i);
			if(n.n == 1) {
				check(n,out[i]);
			}else if(n.n == 2) {
				check(n,out[i]);
				check(n,(out[i]+2)%4);
			}else if(n.n == 3) {
				check(n,out[i]);
				check(n,(out[i]+1)%4);
			}else if(n.n == 4) {
				check(n,out[i]);
				check(n,(out[i]+1)%4);
				check(n,(out[i]+2)%4);
			}else if(n.n == 5) {
				check(n,out[i]);
				check(n,(out[i]+1)%4);
				check(n,(out[i]+2)%4);
				check(n,(out[i]+3)%4);
			}
		}
		ans = Math.min(cnt(), ans); // 사각지역 체크하여 최솟값 경
	}


	private static int cnt() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy_Map[i][j] == 0)
					count++;
			}
		}
		return count;
	}

	private static void check(Node n, int i) { //i방향으로 cctv 감시 구역을 표시한다.
		int ny = n.y;
		int nx = n.x;
		while(true) {
			ny+=dy[i];
			nx+=dx[i];
			if(ny <0 || nx < 0 || ny >= N|| nx >= M || copy_Map[ny][nx]==6)
				return;
			copy_Map[ny][nx] = n.n;
		}
	}


	static class Node {
		int n, y, x;

		Node(int n, int y, int x) {
			this.n = n;
			this.y = y;
			this.x = x;
		}
	}
}
