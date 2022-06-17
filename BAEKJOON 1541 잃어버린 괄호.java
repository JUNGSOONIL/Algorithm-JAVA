import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sub = new StringTokenizer(br.readLine(),"-"); // -를 기준으로 자른다
	
		boolean first = true; // 첫번째 확인
		int ans = 0; // 최종 계산값 저장
		while (sub.hasMoreTokens()) {
			StringTokenizer add = new StringTokenizer(sub.nextToken(), "+"); // -로 짜른것중에 +로 짤라서 연산
			
			int sum = 0;
			while (add.hasMoreTokens()) {
				sum += Integer.parseInt(add.nextToken());
			}
			
			if(first) { // 첫번째는 그냥 저장을 해준다 이후 값들에대해선 - 연산
				ans = sum;
				first = false;
			}
			else
				ans-=sum;
		}
		System.out.println(ans);
	}
}
