package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1459걷기 {

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long x=Long.parseLong(st.nextToken());
		long y=Long.parseLong(st.nextToken());
		long w=Long.parseLong(st.nextToken());
		long s=Long.parseLong(st.nextToken());
		
		//1. 블록으로 이동하는 경우
		long ans = (x+y)*w;
		
		//2. 대각선으로 이동하는 경우
		if((x+y)%2==0) // 싹다 대각선 이
 			ans = Math.min(ans, Math.max(y, x)*s);
		else // 대각선 이동후 한칸은 블록이
			ans = Math.min(ans, (Math.max(y, x)-1)*s + w);
		
		//3. 대각선 + 블록으로 이동하는 경우
		ans = Math.min(ans, (Math.min(x, y))*s+(Math.abs(x-y))*w);
		
		System.out.println(ans);
	}
}
