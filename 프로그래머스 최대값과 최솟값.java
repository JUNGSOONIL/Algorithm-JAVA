import java.util.Arrays;

class Solution {
    public static String solution(String s) {
        String str[] = s.split(" "); // 문자열을 띄어쓰기 기준으로 짤라서 String 배열로 저장
        int number[] = Arrays.stream(str).mapToInt(Integer::parseInt).toArray(); // String 배열을 int 배열로 변환
        Arrays.sort(number);
        return number[0] +" "+ number[number.length-1];
    }
}
