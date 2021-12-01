//[문제8] 문제7에서 구현한 find에 패턴을 지원하도록 기능을 확장한 find2명령어를 구현하라.
//find는 하나의 파일에 대해서만 찾기가 가능했지만, find2는 'find2 if *.java'와 같이
//패턴을 이용해서 여러파일에 대한 찾기가 가능해야한다.(실행결과 참고)
//
//
//
//[예제ConsoleEx8.java]
package topic03.console_app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class E0016_CD {

	static String[] argArr; // 입력한 매개변수를 담기위한 문자열배열
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐(Queue)
	static final int MAX_SIZE = 5; // Queue에 최대 5개까지만 저장되도록 한다.

	static File curDir;

	static {
		try {
			curDir = new File("/Users/macintosh/git/JavaStandard/E1000/src/topic03/console_app");
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // 한번만 생성해서 재사용하면 되므로 반복문 밖으로 이동

		while (true) {
			try {
				String prompt = curDir.getCanonicalPath() + ">>";
				System.out.print(prompt);

				// 화면으로부터 라인단위로 입력받는다.
				String input = sc.nextLine();

				save(input);

				input = input.trim(); // 입력받은 값에서 불필요한 앞뒤 공백을 제거한다.
				argArr = input.split(" +");

				String command = argArr[0].trim();

				if ("".equals(command))
					continue;

				command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.

				if (command.equals("q")) { // q 또는 Q를 입력하면 실행종료한다.
					System.exit(0);
				} else if (command.equals("history")) {
					history();
				} else if (command.equals("dir")) {
					dir();
				} else if (command.equals("type")) {
					type();
				} else if (command.equals("find")) {
					find();
				} else if (command.equals("find2")) {
					find2();
				} else if (command.equals("cd")) {
					cd();
				} else {
					for (int i = 0; i < argArr.length; i++) {
						System.out.println(argArr[i]);
					}
				}
			} catch (Exception e) {
				System.out.println("입력오류입니다.");
			}
		} // while(true)

	} // main

	public static void save(String input) {
		if (input == null || "".equals(input))
			return;

		q.add(input); // queue에 저장한다.

		// queue의 최대크기를 넣으면 제일 오래된 저장값을 삭제한다.
		if (((LinkedList) q).size() > MAX_SIZE)
			q.remove();
	}

	public static void history() {
		int i = 0;

		// LinkedList의 내용을 보여준다.
		ListIterator it = q.listIterator();

		while (it.hasNext()) {
			System.out.println(++i + "." + it.next());
		}
	}

	public static void dir() {
		String pattern = "";

		switch (argArr.length) {
		case 1: 
			for (File list : curDir.listFiles()) {
				if (list.isDirectory()) {
					System.out.println("[" + list.getName() + "]");
				} else {
					System.out.println(list.getName());
				}
			}
			break;
		case 2: 
			pattern = argArr[1];
            pattern = pattern.toUpperCase();
            pattern = pattern.replace(".","\\.");
            pattern = pattern.replace("*",".*");
            pattern = pattern.replace("?",".{1}");
			for(File list : curDir.listFiles()) {
				String s = list.getName().toUpperCase();
				if (s.contains(pattern)) {
					if(list.isDirectory()) {
						System.out.println("[" + list.getName() + "]");
					}
					System.out.println(list.getName());
				}
			}
			break;
		default:
			System.out.println("USAGE : dir [FILENAME]");
		}

	} // dir()

	public static void type() throws IOException {
		/* 내용 생략 */

	}

	public static void find() throws IOException {
		if (argArr.length != 3) {
			System.out.println("USAGE : find KEYWORD FILE_NAME");
			return;
		}

		String keyword = argArr[1];
		String fileName = argArr[2];

		File tmp = new File(curDir, fileName);

		if (tmp.exists()) {
			FileReader fr = new FileReader(tmp);
			BufferedReader br = new BufferedReader(fr);

			String line = "";

			for (int i = 1; (line = br.readLine()) != null; i++) {
				// keyword를 포함한 라인을 출력한다.
				if (line.indexOf(keyword) != -1)
					System.out.println(i + ":" + line);
			}
		} else {
			System.out.println(fileName + " 존재하지 않는 파일입니다.");
		}

		return;
	}

	public static void find2() throws IOException {
		if (argArr.length != 3) {
			System.out.println("USAGE : find2 KEYWORD FILE_NAME");
			return;
		}

		String keyword = argArr[1];
		String pattern = argArr[2];

		pattern = pattern.toUpperCase();
		pattern = pattern.replace(".", "\\.");
		pattern = pattern.replace("*", ".*");
		pattern = pattern.replace("?", ".{1}");

		Pattern p = Pattern.compile(pattern);

		for (File f : curDir.listFiles()) {
			String tmp = f.getName().toUpperCase();
			Matcher m = p.matcher(tmp);

			if (m.matches()) {
				if (f.isDirectory()) {
					System.out.println("[" + f.getName() + "]");
				} else if (f.exists()) {
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					ArrayList<String> al = new ArrayList<>();

					for (int i = 1; (line = br.readLine()) != null; i++) {
						// keyword를 포함한 라인을 출력한다.
						if (line.indexOf(keyword) != -1) {
							al.add(i + ":" + line);
						}
					}
					if (al.size() != 0) {
						System.out.println("---------" + f.getName());
						for (int i = 0; i < al.size(); i++) {
							System.out.println(al.get(i));
						}
					}

				}
			}
		}

		/*
		 * 
		 * 다음의 코드를 완성하세요.
		 * 
		 * 
		 * 
		 * 1. 입력된 패턴(pattern)을 정규식 표현(Regular Expression)에 알맞게 치환한다.
		 * 
		 * String클래스의 String replace(CharSequence target, CharSequence replacement)를
		 * 사용하자.
		 * 
		 * 예를 들면, pattern = pattern.replace("A","AA")는 pattern의 "A"를 "AA"로 치환한다.
		 * 
		 * 
		 * 
		 * 2. 반복문을 이용해서 현재 디렉토리 중, 입력된 패턴과 일치하는 것들에 대해서,
		 * 
		 * 2.1 반복문을 이용해서 라인별로 읽어서 keyword가 포함되었는지 확인한다.
		 * 
		 * (BufferedReader의 readLine()사용)
		 * 
		 * 2.2 keyword가 포함된 라인을 발견하면, 라인번호와 함께 해당 라인을 화면에 출력한다.
		 * 
		 */

		return;
	}

	public static void cd() {

		if (argArr.length == 1) {
			System.out.println(curDir);
			return;
		} else if (argArr.length > 2) {
			System.out.println("USAGE : cd directory");
			return;
		}

		String subDir = argArr[1];
		
		if (subDir.equals("..")) {
			curDir = curDir.getParentFile();
			System.out.println(curDir);
		} else if (subDir.equals(".")) {
			System.out.println(curDir);
		} else {
			for (File f : curDir.listFiles()) {
				if (!f.getName().equalsIgnoreCase(subDir)) {
					String path = curDir.getPath() + "/" + subDir;
					curDir = new File (path);
					System.out.println(curDir);
				} else if (!f.getName().equalsIgnoreCase(subDir)) {
					System.out.println("유효하지 않은 디렉토리 입니다.");
				}
			}
		}
		/*

        다음의 코드를 완성하세요.

        1. 입력된 디렉토리(subDir)가 ".."이면,

             1.1  현재 디렉토리의 조상 디렉토리를 얻어서 현재 디렉토리로 지정한다.

                   (File클래스의 getParentFile()을 사용)

        2. 입력된 디렉토리(subDir)가 "."이면,

            단순히 현재 디렉토리의 경로를 화면에 출력한다.

        3. 1 또는 2의 경우가 아니면, 입력된 디렉토리(subDir)가 현재 디렉토리의 하위디렉토리인지 확인한다.

            3.1 확인결과가 true이면, 현재 디렉토리(curDir)을 입력된 디렉토리(subDir)로 변경한다.

            3.2 확인결과가 false이면, "유효하지 않은 디렉토리입니다."고 화면에 출력한다.

   */
	}
} // class

//[실행결과] - 현재 작업중인 폴더가 C:\java1000\work\Console일 경우
//C:\java1000\work\Console>>cd
//C:\java1000\work\Console
//C:\java1000\work\Console>>cd .
//C:\java1000\work\Console
//C:\java1000\work\Console>>cd ..
//C:\java1000\work>>cd console
//C:\java1000\work\Console>>q

