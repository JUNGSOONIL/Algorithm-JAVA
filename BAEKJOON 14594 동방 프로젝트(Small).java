import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N+1];
		for (int i = 1; i <= N; i++) { // 배열이 자기 자신을 가리키도록 한다.
			map[i] = i;
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int temp = 0;
			for (int j = a; j <= b; j++) { // 입력된 두숫자만큼 반복을 하면서 배열을 갱신해준다.
				if(map[j] != j) // 만약 배열이 자기 자신을 가리키지 않는다면 이미 연결된 상태 해당값을 저장한다.
					temp = map[j];
				if(temp != 0) // 저장된 값이 있으면 해당값으로 연결되도록 한다. 
					map[j] = temp;
				else // 시작값으로 연결한다.
					map[j] = a;
			}
		}
		int ans = 0;
		for (int i = 1; i <= N; i++) { // 배열을 탐색하면서 배열 인덱스와 배열 값이 같으면 하나의 그룹이므로 수를 증가시킨다.
			if(map[i] == i)
				ans++;
		}
		System.out.println(ans);
	}
}
