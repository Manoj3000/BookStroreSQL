package bookstore.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilValidation {
	public static boolean isValid(String regexExpression, String input) {
		Pattern pattern = Pattern.compile(regexExpression);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}
	
	public static void isEmpty(Object obj) {
		
		System.out.println(obj+" is Empty ");
	}
	
	public static boolean isNull(Object obj) {
		
		return obj==null;
	}
	
	public static boolean isNotNull(Object obj) {
		
		return obj!=null;
	}
}
