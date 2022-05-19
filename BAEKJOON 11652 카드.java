import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine()); // 입력값 범위가 int형을 넘음
		TreeMap<Long, Integer> map = new TreeMap<>(); // 트리맵을 통해 키값 오름차순으로 정렬
		for (int i = 0; i < N; i++) {
			long M = Long.parseLong(br.readLine());
			map.put(M, map.getOrDefault(M,0)+1); // map에 해당값이 있으면 1증가시켜넣음
		}
		List<Map.Entry<Long, Integer>> list = new LinkedList<>(map.entrySet()); // map를 list로 변화 정렬학 위해
        list.sort(Map.Entry.comparingByValue((a,b) -> b - a)); // 람다식 사용 value값 기준 내림차순 정렬
        System.out.println(list.get(0).getKey()); // 젤 상단값 출력
	}
}
