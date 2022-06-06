import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N, K, parent[];
	static boolean[] visit;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		parent = new int[100001]; // 자신의 부모를 가르키는 배열
		visit = new boolean[100001]; // 방문 체크
		visit[N] = true;
		q.offer(new Node(N, 0));
		bfs();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.index == K) {
				System.out.println(n.cnt); // 이동 횟수를 출력해줌
				Stack<Integer> s = new Stack<>(); // 스택에 먼저 역순으로 타고 시작까지의 경로를 저장
				int p = n.index;
				s.push(p);
				while (p != N) {
					p = parent[p];
					s.push(p);
				}
				StringBuilder sb = new StringBuilder();
				while (!s.isEmpty()) { // 스택에서 하나씩 빼내면서 출력값에 추가해줌.
					sb.append(s.pop()).append(" ");
				}
				System.out.println(sb.toString());
				return;
			}

			int add = n.index + 1;
			int sub = n.index - 1;
			int mul = n.index * 2;
			if (add <= 100000 && !visit[add]) { 
				q.offer(new Node(add, n.cnt + 1));
				parent[add] = n.index;
				visit[add] = true;
			}
			if (sub >= 0 && !visit[sub]) {
				q.offer(new Node(sub, n.cnt + 1));
				parent[sub] = n.index;
				visit[sub] = true;
			}
			if (mul <= 100000 && !visit[mul]) {
				q.offer(new Node(mul, n.cnt + 1));
				parent[mul] = n.index;
				visit[mul] = true;
			}
		}
	}

	static class Node {
		int index, cnt;

		Node(int index, int cnt) {
			this.index = index;
			this.cnt = cnt;
		}
	}
}
