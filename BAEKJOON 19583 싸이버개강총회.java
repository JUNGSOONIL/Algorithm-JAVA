import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] times = br.readLine().split(" "); // 시간들을 파싱한다.
		int start =  Integer.parseInt(times[0].split(":")[0] + times[0].split(":")[1]); 
		int end =  Integer.parseInt(times[1].split(":")[0] + times[1].split(":")[1]); 
		int all_end =  Integer.parseInt(times[2].split(":")[0] + times[2].split(":")[1]); 
		String input = br.readLine();
		Map<String, Integer> map = new HashMap<String, Integer>(); // map에 값을 넣어서 확인하기
		while (true) { 
			if(input == null || input.length() == 0)
				break;
			String[] check = input.split(" ");
			int time = Integer.parseInt(check[0].split(":")[0]+check[0].split(":")[1]); // 채팅 시간을 저장
			if(time <= start) // 시간이 시작시간 이전이면 map에 담음
				map.put(check[1], 1);
			else if(time >= end && time <= all_end && map.get(check[1]) != null) // 시간이 종료시간 이후면서 스트리밍종료시간 안이고 map에 값이 있으면
				map.put(check[1], 2);
			input = br.readLine();
		}
		int cnt = 0;
		for (int i : map.values()) { // 2인애들찾아서 카운트 증가시키기
			cnt += i == 2 ? 1: 0; 
		}
		System.out.println(cnt);
	}

}
