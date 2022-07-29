import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a); // 우선순위 큐를 내림차순으로 람다식으로 정렬한다.

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) { // 우선순위 큐에 숫자를 모두 넣어준다.
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < N-1; i++) { // 이전값만큼은 빼내고 
            pq.poll();
        }
        System.out.println(pq.poll()); // 출력값 출력
    }
}
