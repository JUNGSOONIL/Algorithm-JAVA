import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, ay, ax, cnt, dy[] = {0,1,1,1,0,0,0,-1,-1,-1}, dx[] = {0,-1,0,1,-1,0,1,-1,0,1}; // 1~9지니깐 첫번째에 더미 추가
	static char map[][], index[];
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) { // 입력처리
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'I') {
					map[i][j] ='.';
					ay = i;
					ax = j;
				}else if(map[i][j] == 'R') {
					q.add(new Node(i,j));
				}
			}
		}
		index = br.readLine().toCharArray(); // 이동해야하는 방향 저장
		
		while(true) {
			if(move_Arduino()) { // 아두이노를 이동시킨다.
				System.out.println("kraj "+(cnt+1));
				break;
			}
			if(move_Crazy_Arduino()) { // 미친 아두이노를 이동시킨다.
				System.out.println("kraj "+(cnt+1));
				break;
			}
			map_Check();
			if(++cnt == index.length) { // 모두 이동했다면 출력하고 종료
				print();
				break;
			}
		}
	}
	
	private static void print() {
		map[ay][ax] = 'I';
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void map_Check() { // 이동시킨 좌표를 가지고 map를 갱신함 이때 새로운 배열을 만들어서 큐에있는 값을 갱신해줌 R에대해서 기존값과 충돌 방지
		char copy[][] = new char[R][C];
		while(!q.isEmpty()) {
			Node n = q.poll();
			if(copy[n.y][n.x] != 'R' && copy[n.y][n.x] != 'X') // R의 단순 이동
				copy[n.y][n.x] = 'R';
			else // R이 있는곳으로 이동
				copy[n.y][n.x] = 'X';
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(copy[i][j] == 'R') // R은 큐에 다시 담고
					q.add(new Node(i,j));
				else // null 과 X 는 .으로 갱신
					copy[i][j] = '.';
				map[i][j] = copy[i][j]; // 배열 복사
			}		
		}

	}

	private static boolean move_Crazy_Arduino() { // 큐의 사이즈만큼 돌면서 이동시키고 다시 큐에담음
		int size = q.size();
		for(int i= 0;i<size;i++) { 
			Node n = q.poll();
			if(n.y == ay) { // 같은 가로 선상
				if(n.x > ax)
					n.x--;
				else
					n.x++;
			}else if(n.x == ax) { // 같은 세로 선상
				if(n.y > ay)
					n.y--;
				else
					n.y++;
			}else { // 대각선 이동
				// 여기는 사분면으로 총 4가지 조건으로 체크
				if (ay > n.y  && ax > n.x) { // 오른쪽 상단으로 이동
					n.y++;
					n.x++;
				}else if (ay < n.y  && ax > n.x) { // 오른쪽 하단으로 이동
					n.y--;
					n.x++;
				}else if (ay < n.y  && ax < n.x) { // 왼쪽 상단으로 이동
					n.y--;
					n.x--;
				}else if (ay > n.y  && ax < n.x) { // 왼쪽 하단으로 이동
					n.y++;
					n.x--;
				}
			}
			if(n.y == ay && n.x == ax) // 이동한곳이 I 좌표라면 종료
				return true;
			q.add(new Node(n.y,n.x));
		}
		return false;
	}

	private static boolean move_Arduino() { // 이동방향 값을 더해주고 R인 경우 실패
		ay += dy[index[cnt] - '0'];
		ax += dx[index[cnt] - '0'];
		if(map[ay][ax] == 'R') {
			return true;
		}
		return false;
	}

	static class Node{
		int y, x;
		Node(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
