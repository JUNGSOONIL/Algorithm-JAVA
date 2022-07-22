import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String,String> map = new HashMap<>();
        for(int i = 0 ; i < N ; i ++){
            String group = br.readLine();
            int n = Integer.parseInt(br.readLine());
            for(int j = 0; j < n ; j ++){
                map.put(br.readLine(),group); // 이름을 키값으로 하고 벨류에 그룹명 저장
            }
        }

        for(int i = 0 ; i < M ; i++){
            String name = br.readLine();
            int n = Integer.parseInt(br.readLine());
            if(n == 0){ // 이름 출력
                List<String> list = new ArrayList<>(); //정렬을 위해 사용
                for (Map.Entry<String,String> m: map.entrySet()) { // 벨류값을 통해 키값 가져오기
                    if(m.getValue().equals(name))
                        list.add(m.getKey());
                }
                list.sort((a,b) -> a.compareTo(b)); //람다식으로 정렬
                for (String s: list) {
                    System.out.println(s); // 이름 출력
                }
            }else { // 그룹명 출력 
                System.out.println(map.get(name));
            }
        }
    }
}
