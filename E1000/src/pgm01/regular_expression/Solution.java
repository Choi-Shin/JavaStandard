package pgm01.regular_expression;

class Solution {
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase(); //1단
        String step2 = step1.replaceAll("[^a-z0-9-_.]", "");
        String step3 = step2.replaceAll("[.]{2,}", ".");
        String step4 = step3.replaceAll("^[.]|[.]$", "");
        String step5 = step4;
        if(step5.equals("")) {
        	step5 = "a";
        }
        String step6 = step5;
        if(step6.length() >= 16) {
        	step6 = step6.substring(0, 15);
        	step6 = step6.replaceAll("[.]$", "");
        }
        String step7 = step6;
        if(step7.length() <= 2) {
        	while (step7.length() < 3) {
        		step7 += step7.charAt(step7.length()-1);
        	}
        }
        String answer = step7;
        return answer;
    }
}