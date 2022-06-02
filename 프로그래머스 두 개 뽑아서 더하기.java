import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int[] solution(int[] numbers) {
    	Set<Integer> set = new HashSet<>();
		for (int i = 0; i < numbers.length; i++) { // 2중 반복문으로 모든경우를 체크함
			for (int j = i + 1; j < numbers.length; j++) {
				set.add(numbers[i] + numbers[j]); // set에 넣어서 중복 없애기
			}
		}
		int[] answer = new int[set.size()];
		int index = 0;
		for (int i : set) { // 반복하면서 배열에 넣기
			answer[index++] = i;
		}
		Arrays.sort(answer); // 배열 정렬
		return answer;
    }
}
