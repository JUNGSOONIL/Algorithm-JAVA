import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,ans, map[][], copy_Map[][], out[]=new int[5],dy[]= {1,0,-1,0}, dx[]= {0,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0); //순열로 가능한 경우 만들어준다.
		
		System.out.println(ans);
	}
	
	private static void move(int y,int x, int d) { // 더하기가 끝나면 0인공간에 대해 이동시켜준다 
		if(copy_Map[y][x] == 0)
			return;
		int ny = y;
		int nx = x;
		while(true) {
			ny += dy[d];
			nx += dx[d];
			
			if(ny <0|| nx <0 || ny >=N || nx >= N)
				return;
			
			if(copy_Map[ny][nx] == 0) { // 이동 시킴과 동시에 좌표 값도 경신해 준다. 
				copy_Map[ny][nx] = copy_Map[y][x];
				copy_Map[y][x] = 0;
				y = ny;
				x = nx;
			}
			else
				return;
		}
	}
	
	private static void sum(int y,int x, int d) { // 현재 값에서 다음값들을 비교하면서 더할수있으면 더하고 멈춤 
		if(copy_Map[y][x] == 0)
			return;
		int ny = y;
		int nx = x;
		while(true) {
			ny += dy[d];
			nx += dx[d];
			
			if(ny <0|| nx <0 || ny >=N || nx >= N) // 배열 범위 벗어나는 경우 
				return;
			
			if(copy_Map[y][x] == copy_Map[ny][nx]) { // 더하기 
				copy_Map[y][x] += copy_Map[ny][nx];
				copy_Map[ny][nx] = 0;
				return;
			}
			if(copy_Map[ny][nx] != 0) // 더할수 없는 숫자를 만남 
				return;
		}
	}
	
	static void check() { // 기존 배열은 유지해야 함으로 배열을 복사함.
		copy_Map = new int[N][N];
		for (int i = 0; i < N; i++) { //배열 복사 
			for (int j = 0; j < N; j++) {
				copy_Map[i][j] = map[i][j];
			}
		}
		
		for (int t = 0; t < 5; t++) { // 경우의수를 체크하면서 반복문을 돌리는데 방향에따라 반복문의 시작값이 다름. 
			if(out[t] == 0) { // 상 
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						sum(j,i,0); // 먼저 더할수있으면 더한다.
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						move(j,i,2); // 한사이클이 끝나면 빈칸에 이동시켜준다.
					}
				}
			}else if(out[t] == 1) { // 우 
				for (int i = N-1; i >= 0; i--) {
					for (int j = 0; j < N; j++) {
						sum(j,i,1);
					}
				}
				for (int i = N-1; i >= 0; i--) {
					for (int j = 0; j < N; j++) {
						move(j,i,3);
					}
				}
			}else if(out[t] == 2) { // 하 
				for (int i = N-1; i >= 0; i--) {
					for (int j = 0; j < N; j++) {
						sum(i,j,2);
					}
				}
				for (int i = N-1; i >= 0; i--) {
					for (int j = 0; j < N; j++) {
						move(i,j,0);
					}
				}
			}else { // 좌 
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						sum(i,j,3);
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						move(i,j,1);
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) { // 최대값 갱신.
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, copy_Map[i][j]);
			}
		}
	}

	static void perm(int index) { // 순열 만드는 부
		if(index == 5) {
			check(); // 가능한 경우가 만들어지면 체크한다.
			return;
		}
		for (int i = 0; i < 5; i++) {
			out[index] = i;
			perm(index+1);
		}
	}
}
