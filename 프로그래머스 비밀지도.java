class Solution {
public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        for (int i = 0; i < answer.length; i++) {
        	String start = Integer.toBinaryString(arr1[i] | arr2[i]); // 10진수를 2진수로 변환 , or 연산 적용
        	while(start.length() < n) { // 자릿수가 모자를경우 앞에 0을 추가해줌
        		start = "0"+start;
    		}
        	String end = "";
        	for (int j = 0; j < answer.length; j++) { // 2진수를 비교하면서 # ' '으로 변경 
				if(start.charAt(j) == '1')
					end += "#";
				else
					end += " ";
			}
			answer[i] = end;
		}
        return answer;
    }
}
