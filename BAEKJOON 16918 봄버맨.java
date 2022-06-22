import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, N, dy[] = {-1,1,0,0}, dx[]= {0,0,-1,1};
	static char[][] map;
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];	
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		boolean check = true; //시간마다 하는 동작이다르니 체크
		while(N-->1) {
			if(check) { // 여기서 꽉 채우기 + 큐에담기
				insert();
			}else { // 여기선 큐에 담긴거 터트리기
				bomb();
			}
			check = !check;
		}
		print();
	}
	private static void print() { // 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]+"");
			}
			System.out.println();
		}
	}
	private static void bomb() { // 폭탄 폭발
		while(!q.isEmpty()) {
			Node n = q.poll();
			map[n.y][n.x] = '.';
			for (int i = 0; i < 4; i++) {
				int ny = n.y + dy[i];
				int nx = n.x + dx[i];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == '.')
					continue;
				map[ny][nx] = '.';
			}
		}
	}
	
	private static void insert() { // 폭탄 추가 설치 및 기존 폭탄 담기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'O')
					q.add(new Node(i,j));
				else
					map[i][j] = 'O';
			}
		}
	}
	static class Node{
		int y, x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
