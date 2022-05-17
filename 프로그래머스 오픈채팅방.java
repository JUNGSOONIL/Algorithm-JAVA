import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
       public static String[] solution(String[] record) {
        Queue<Data> q = new LinkedList<>();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
			String[] s = record[i].split(" "); // 행동, 아이디, 닉네임
			if(!"Leave".equals(s[0])) { // 나가는게 아니면 닉네임 갱신
				map.put(s[1], s[2]);
			}
			if(!"Change".equals(s[0])) { // 닉네임 변경이 아니면 들오고 나가는 정보 큐에 갱신
				q.add(new Data(s[1],s[0]));
			}
		}
        String[] answer = new String[q.size()]; // 큐 크기가 곧 출력 크기
        int index = 0; // 배열 인덱스
        while(!q.isEmpty()){
        	Data d = q.poll();
        	String str = ""; 
        	str += map.get(d.s)+"님이 "; // 아이를 통해서 닉네임을 가져옴
        	if("Leave".equals(d.t)) { // 나간경우
        		str+="나갔습니다."; 
        	}else { // 들어온경우 (닉네임 교환은 위에서 걸러줬음)
        		str+="들어왔습니다.";
        	}
        	answer[index++] = str; // 리턴 배열에 값 저장
        }
        return answer;
    }
    static class Data{
    	String s, t;
    	Data(String s, String t){
    		this.s = s;
    		this.t = t;
    	}
    }
}
