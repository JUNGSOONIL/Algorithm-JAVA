import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int dp[][] = new int[N+1][10];

        for (int i = 0; i < 10; i++) { // 처음 한자리수는 경우의수가 각각 하나임
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) { // 두자리수부터 갱신을 해준다.
            for (int j = 0; j < 10; j++) {
                for (int k = 9; k >= j; k--) { // i 는 자리수 j 는 시작하는 수
                    dp[i][j] += dp[i-1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += dp[N][i];
        }
        System.out.println(ans%10007);
    }
}
