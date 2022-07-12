import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringBuffer sb = new StringBuffer();
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int index = 0, number = 1;
		
		
		while(true) {
			stack.push(number++); // 숫자를 1~n 까지 푸시해줌 
			sb.append("+").append("\n"); // 푸시했으니 표시 
			while(!stack.isEmpty() && arr[index] == stack.peek()) { // 스택이 안비어있고 스택에 뺴올값과 배열에 인덱스 값이 같으면 계속 반복  
				stack.pop(); // 스택에서 하나 빼내고 
				sb.append("-").append("\n"); // 팝 했으니 표시 
				index++; // 배열 인덱스 1 증가 
			}
			if(index >= n || number > n) // 인덱스가n보다 크거나 같으면 연산 종료 , number이 n 보다 크면 실패한경우  
				break;
		}
		if(stack.isEmpty()) // 스택이 비었으면 성공 안비었으면 실패 
			System.out.println(sb.toString());
		else
			System.out.println("NO");
	}
}
