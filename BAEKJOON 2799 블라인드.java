import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char map[][] = new char[N*5+1][M*5+1];
        int ans[] = new int[5];
        for (int i = 0; i < N*5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 1; i <= N*5; i = i + 5) {
            for (int j = 1; j <= M*5 ; j = j + 5) {
                int cnt = 0;
                for (int k = 0; k < 5; k++) {
                    if(map[i+k][j] =='*')
                        cnt++;
                    else
                        break;;
                }
                ans[cnt]++;
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(ans[i]+" ");
        }
    }
}
