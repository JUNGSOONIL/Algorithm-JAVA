import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int[][] bingo;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		bingo = new int[5][5]; // 빙고 판 
		Map<Integer, int[]> map = new HashMap<>(); //map에 숫자에대한 좌표 정보를 담는다 
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++) {
					map.put(Integer.parseInt(st.nextToken()), new int[] {i,j});
			}
		}
		
		
		int ans = 0;
		roop: // 이중 포문을 빠져나오기 위해 사용 체크 포인트 
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int[] n = map.get(Integer.parseInt(st.nextToken()));
				bingo[n[0]][n[1]] = 1;
				ans++;
				if(ans >= 12 && check()){ // 최소 12턴은 지나야 빙고가 가능 이후부터 빙고 체크  
					System.out.println(ans);
					break roop;
				}
			}
		}
	}
	private static boolean check() {
		int cnt = 0, sum = 0;
		for (int i = 0; i < 5; i++) { // 행 체크 
			sum = 0;
			for (int j = 0; j < 5; j++) {
				sum+= bingo[i][j];
			}
			if(sum == 5)
				cnt++;
		}
		
		for (int i = 0; i < 5; i++) { // 열 체크 
			sum = 0;
			for (int j = 0; j < 5; j++) {
				sum+= bingo[j][i];
			}
			if(sum == 5)
				cnt++;
		}
		sum = 0;
		for (int i = 0; i < 5; i++) { // 하향 대각선 체크 
			sum+= bingo[i][i];
		}
		if(sum == 5)
			cnt++;
		
		sum = 0;
		for (int i = 0; i < 5; i++) { // 상향 대각선 체크 
			sum+= bingo[i][4-i];
		}
		if(sum == 5)
			cnt++;
		
		if (cnt >= 3)
			return true;
		return false;
	}
}
