//[문제2] 문제1에서 완성된 코드에 사용자 입력을 받아서 정답여부를 판단하여 마지막에 최종점수를
//
//실행결과와 같이 출력하도록 코드를 완성하세요.
//
//
//
//[예제QuizEx2.java]
package topic02.simple_quiz;

import java.util.Scanner;

public class E0006 {
	public static void main(String[] args) {
		String[] data = { "다음 중 키워드가 아닌 것은?`2`final`True`if`public", "다음 중 자바의 연산자가 아닌 것은?`6`&`|`++`!=`/`^",
				"다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false`", };

		Scanner sc = new Scanner(System.in);
		int score = 0;

		for (int i = 0; i < data.length; i++) {
			String[] tmp = data[i].split("`", 3);

			String question = tmp[0];
			String answer = tmp[1];
			String[] choices = tmp[2].split("`");

			System.out.println("[" + (i + 1) + "] " + question);

			for (int x = 0; x < choices.length; x++) {
				System.out.print((x + 1) + "." + choices[x] + "\t");
			}
			System.out.println();
			System.out.print("[답]");
			String input = sc.next();
			if (choices[Integer.parseInt(input)-1].equalsIgnoreCase("if")) {
				score++;
			}
			if (choices[Integer.parseInt(input)-1].equalsIgnoreCase("^")) {
				score++;
			}
			if (choices[Integer.parseInt(input)-1].equalsIgnoreCase("void")) {
				score++;
			}
			
			/*
			 * 
			 * 알맞은 코드를 넣으세요.
			 * 
			 */

			System.out.println();
			System.out.println();
		}
		System.out.println("정답개수/전체문항수: " + score + "/" + data.length);
		// 알맞은 코드를 넣으세요.
	} // main
}

//[실행결과]
//
//[1] 다음 중 키워드가 아닌 것은?
//1.final 2.True  3.if    4.public
//[답]2
//
//
//[2] 다음 중 자바의 연산자가 아닌 것은?
//1.&     2.|     3.++    4.!=    5./     6.^
//[답]6
//
//
//[3] 다음 중 메서드의 반환값이 없음을 의미하는 키워드는?
//1.void  2.null  3.false
//[답]1
//
//
//정답개수/전체문항수 :3/3