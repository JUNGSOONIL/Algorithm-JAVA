import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr); // 배열을 오름차순으로 정렬해준다.
		int ans = 0; // 결과 저장
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, arr[i] * (N - i)); // 최대값을 갱신하는데 자기 자신 * 남은 로프수를 곱해준다
		}
		System.out.println(ans);
	}
}
