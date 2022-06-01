import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new TreeMap<>(); // treemap는 자동으로 키값으로 정렬해줌 (사전순 정렬)
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			map.put(str, map.getOrDefault(str, 0)+1); // 해당 값이 있으면 가져온값 +1 없으면 0+1한값을 넣어줌
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet()); // map를 정렬하기위해 list로 변환
		list.sort(Map.Entry.comparingByValue((a,b) -> b - a)); // 벨류를 내림차순으로 람다식 정렬
		System.out.println(list.get(0).getKey()); // 첫번째 값의 키를 출력
	}
}
