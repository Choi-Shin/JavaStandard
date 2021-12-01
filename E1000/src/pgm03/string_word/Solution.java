package pgm03.string_word;
//네오와 프로도가 숫자놀이를 하고 있습니다. 

//네오가 프로도에게 숫자를 건넬 때 일부 자릿수를 
//영단어로 바꾼 카드를 건네주면 프로도는 원래 숫자를 찾는 게임입니다.
//
//다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.
//
//1478 → "one4seveneight"
//234567 → "23four5six7"
//10203 → "1zerotwozero3"
//이렇게 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 
//혹은 바뀌지 않고 그대로인 문자열 s가 매개변수로 주어집니다. 
//s가 의미하는 원래 숫자를 return 하도록 solution 함수를 완성해주세요.

public class Solution {
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		int s = sol.solution("2three45sixseven");
		System.out.println(s);
	}
	public int solution(String s) {
		s = s.replaceAll("zero", "0");
		s = s.replaceAll("one", "1");
		s = s.replaceAll("two", "2");
		s = s.replaceAll("three", "3");
		s = s.replaceAll("four", "4");
		s = s.replaceAll("five", "5");
		s = s.replaceAll("six", "6");
		s = s.replaceAll("seven", "7");
		s = s.replaceAll("eight", "8");
		s = s.replaceAll("nine", "9");
		
		int answer = Integer.parseInt(s);
		return answer;

	}
}
