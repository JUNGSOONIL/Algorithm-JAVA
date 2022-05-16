import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

class Solution {

      public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        Map<Data, Integer> map2 = new HashMap<Data, Integer>();
        for (int i = 0; i < genres.length; i++) {
        	map1.put(genres[i],map1.getOrDefault(genres[i], 0) + plays[i]); // 장르별 총합 저장
        	map2.put(new Data(genres[i],plays[i]),i); // 장르와 재생횟수 고유번호 저장
		}
        
        // 장르별 총합 기준으로 내림차순 정렬
        List<Map.Entry<String, Integer>> list1 = new LinkedList<>(map1.entrySet());
        list1.sort(Map.Entry.comparingByValue((a,b) -> b - a)); 
        
        // 입력데이터별 장르가 같으면 재생횟수를 체크하고 재생횟수도 같으면 고유번호기준으로 오름차순 정렬을 해준다. 재생 횟수가 다르면 재생횟수 기준 내림차순 정렬을 한다.
        List<Map.Entry<Data, Integer>> list2 = new LinkedList<>(map2.entrySet());
        list2.sort(Map.Entry.comparingByKey((a,b) -> a.s.equals(b.s) ? a.n == b.n ? map2.get(a) - map2.get(b) : b.n - a.n: a.s.compareTo(b.s)));
        
        String str = ""; 
        int index = 0;
        for (int i = 0; i < list1.size(); i++) { 
            int cnt = 0;
        	String s = list1.get(i).getKey();
        	for (Entry<Data, Integer> entry : list2) { 
				if(entry.getKey().s.equals(s)) { // 높은 장르와 입력데이터 장르가 같으면 최종 출력에 추가해줌
					str += map2.get(entry.getKey())+",";
					index++; // 최종 출력 배열 크기 지정
					if(++cnt % 2 == 0) // 2로 나눠지면 2개가 들어간 경우 다음 장르로 넘김
						break;
				}
			}
		}
        str = str.substring(0, str.length()-1); // 마지막 콤마 제거
        int[] answer = new int[index]; 
        StringTokenizer st = new StringTokenizer(str,","); //콤마 기준 짜르기
        index = 0;
        while(st.hasMoreElements()) { // st 다음이 있으면 결과 배열에 추가
        	answer[index++] = Integer.parseInt(st.nextToken());
        }
        
        return answer;
    }
    
    static class Data{
    	String s;
    	int n;
    	Data(String s, int n){
    		this.s = s;
    		this.n = n;
    	}
    }
}
