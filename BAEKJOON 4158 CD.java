import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0) // 종료
				break;

			HashSet<Integer> set = new HashSet<>();
			int cnt = 0;

			for (int i = 0; i < n; i++) { // 상근이 담기
				set.add(Integer.parseInt(br.readLine()));
			}

			for (int j = 0; j < n; j++) { // 선영이꺼 체크
				int cd = Integer.parseInt(br.readLine());

				if (set.contains(cd))
					cnt++; // 이미 있는경우 cnt 증가
			}

			System.out.println(cnt);
		}
	}
}
