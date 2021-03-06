package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParser {
	public static void main(String[] args) {
		String queryStr = " select empno, salary, name from employee";
		// String queryStr = " select * from employee";
		String regEx = "\\s*(select)\\s*" + "( \\w+\\s*|\\*|\\w+\\s*(,\\s*\\w+)*)" + "\\s*from\\s*(\\w+)";
						//	 \s  空白字元	 	\w  英文字母   //1個字母+\	字母    (,一個以上的英文字母)*	(一個以上的英文字母)*
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);  //compile編譯
		Matcher m = p.matcher(queryStr);		//16進位	 

		if (m.matches()) {
			System.out.println(m.group(1).toLowerCase()); // group1: select
			System.out.println(m.group(4).toLowerCase()); // group4: table name
			
			String array[] = m.group(2).split(","); // group2: column names
													// note: ignore group3

			for (String s : array) {
				System.out.println(s.trim().toLowerCase());
			}
//			System.out.println(m.group(3).toLowerCase());	//比較group(2)  group(3)
		}			
	}
}


