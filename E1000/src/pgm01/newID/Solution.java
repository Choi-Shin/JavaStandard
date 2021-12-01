package pgm01.newID;

public class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		String new_id = "................b";
		System.out.println(s.solution(new_id));
	}

	public String solution(String new_id) {
		String answer = "";
		// 1단계 소문자 치환
		String step1 = new_id.toLowerCase();
		// 2단계 소문자,숫자,-,_,.빼고 제거
		char[] chArr = step1.toCharArray();
		StringBuilder step2 = new StringBuilder();
		for (char c : chArr) {
			if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.') {
				step2.append(c);
			}
		}
		// 3단계 마침표(.)가 2번 이상 연속된 부분을 하나로 치환
		String step3 = String.valueOf(step2).replace("..", ".");
		while (step3.contains("..")) {
			step3 = step3.replace("..", ".");
		}
		// 4단계 처음이나 끝에 마침표가 있다면 제거한다.
		String step4 = step3;
		if (step4.length() > 0) {
			if (step4.charAt(0) == '.') {
				step4 = step4.substring(1, step4.length());
			}
		}
		if (step4.length() > 0) {
			if (step4.charAt(step4.length() - 1) == '.') {
				step4 = step4.substring(0, step4.length() - 1);
			}
		}
		// 5단계 빈 문자열이면 "a"를 대입한다.
		String step5 = step4;
		if (step5.equals("")) {
			step5 = "a";
		}
		// 6단계 16자 이상이면 앞에서부터 15개의 문자를 제외하고 제거
		// 제거 후 마침표(.)가 마지막에 위치한다면 그 마침표를 제거
		String step6 = step5;
		if (step6.length() >= 16) {
			step6 = step6.substring(0, 15);
			if (step6.charAt(step6.length() - 1) == '.') {
				step6 = step6.substring(0, step6.length() - 1);
			}
		}
		// 7단계 길이가 2자 이하면 마지막 문자를 길이 3이 될 때까지 끝에 붙인다.
		StringBuilder step7 = new StringBuilder(step6);
		char last = step6.charAt(step6.length() - 1);
		while (step7.length() < 3) {
			step7 = step7.append(last);
		}

		answer = String.valueOf(step7);
		return answer;
	}
}