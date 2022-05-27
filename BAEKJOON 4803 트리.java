import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,number[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0)
				break;
			make();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(find(a) != find(b) && find(a)!= 0 && find(b)!=0) { // 둘의 부모가 다르고, 둘다 부모가 0이아닌경우 
					union(a,b);
				}else if(find(a) == find(b)){ // 둘의 부모가 같다 즉 사이클인 경우 부모를 0으로 초기화
					number[find(a)] = 0;
				}else  { // 부모는 다른데 비교하는값중 하나의 부모가 0임 결국 사이클이 생기는 경우
					number[find(a)] = 0;
					number[find(b)] = 0;
				}
			}
			int cnt = 0;
			for (int i = 1; i < number.length; i++) { // 자기 자신을 가르킨다면 트리의 최상단임 갯수 1 증가
				if(number[i] == i)
					cnt++;
			}
			if(cnt == 0) {
				System.out.println("Case "+(t++)+": No trees.");
			}else if(cnt == 1) {
				System.out.println("Case "+(t++)+": There is one tree.");
			}else {
				System.out.println("Case "+(t++)+": A forest of "+cnt+" trees.");
			}
		}
	}
	
	static void make() {
		number = new int[N+1];
		for (int i = 1; i <=N; i++) {
			number[i] = i;
		}
	}
	
	static int find(int a) {
		if(number[a] == a)
			return a;
		return number[a] = find(number[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a < b)
			number[b] = a;
		else 
			number[a]= b;
	}
}
