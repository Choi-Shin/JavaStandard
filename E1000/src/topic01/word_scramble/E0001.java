//[문제1] 다음의 예제를 완성하시오.
//
//getAnswer(String[] strArr)는 배열strArr의 요소중의 하나를 
//임의로 골라서 반환한다.(Math.random()사용)
//getScrambledWord(String str)는 주어진 문자열 str의 각 문자의 순서를 
//뒤섞은 다음, 새로운 문자열로 반환한다.
//(Math.random()사용)
package topic01.word_scramble;

public class E0001 {
	public static void main(String[] args) {
		String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW" };

		String answer = getAnswer(strArr);
		String question = getScrambledWord(answer);

		System.out.println("Question:" + question);
		System.out.println("Answer:" + answer);
	} // main

	public static String getAnswer(String[] strArr) {
		// 내용을 완성하세요.
		int idx = (int) (Math.random() * strArr.length);
		return strArr[idx];
	}

	public static String getScrambledWord(String str) { 
        // 내용을 완성하세요.
		int idx = (int) (Math.random()*str.length());
		char[] ch = str.toCharArray();
		for (int i=0; i<str.length(); i++) {
			char c = ch[idx];
			ch[idx] = str.charAt(i);
			ch[i] = c;
		}
    	return new String(ch);
      } // scramble(String str)
}
//
// 
//
//[실행결과]
//
//Question:HEPO
//Answer:HOPE
//
// 
//
//[참고]Math.random()을 사용하기 때문에 위의 실행결과와 다를 수 있습니다.