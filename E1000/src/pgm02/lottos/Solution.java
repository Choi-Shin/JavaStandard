package pgm02.lottos;

import java.util.Arrays;

class Solution {
	public int[] solution(int[] lottos, int[] win_nums) {
		int countSame = 0;
		int countZero = 0;
		for (int l : lottos) {
			for (int w : win_nums) {
				if (l == w) {
					countSame++;
				}
			}
			if(l == 0) {
				countZero++;
			}
		}

		int maxPrize = (7-(countSame+countZero)) > 6 ? 6 : (7-(countSame+countZero));
		int minPrize = (7-countSame) > 6 ? 6 : (7-countSame);

		int[] answer = {maxPrize, minPrize};
		return answer;
	}
}
