class Solution {
	static int answer = 0;
    public static int solution(int[] nums) {
        boolean[] visit = new boolean[nums.length];
        comb(0,0,0,nums,visit); // 조합을 이용해 전체 배열에서 3개 뽑기
        return answer;
    }

	private static void comb(int start,int index, int sum, int[] nums,boolean[] visit) {
		if(index == 3) { // 3개 뽑으면 소수인지 체크
			boolean flag = false;
			for (int i = 2; i <= Math.sqrt(sum); i++) { // 소수 확인법 사용
				if(sum%i == 0) {
					flag = true; 
					break;
				}
			}
			if(!flag) // 나눠진게 없으면 소수임 증가
				answer++;
			return;
		}
		for (int i = start; i < visit.length; i++) {
			if(visit[i])
				continue;
			visit[i] = true;
			comb(i,index+1,sum + nums[i],nums,visit);
			visit[i] = false;
		}
	}
}
