import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) { //처음 키워드를 모두 추가한
			set.add(br.readLine());
		}
		
		for (int i = 0; i < M; i++) { //사용 키워드를 입력받아 파싱한
			st = new StringTokenizer(br.readLine(),",");
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				if(set.contains(s)) // 파싱한 키워드가 존재하면 제거한
					set.remove(s);
			}
			System.out.println(set.size());// 사이즈 출
		}
		
	}
}
