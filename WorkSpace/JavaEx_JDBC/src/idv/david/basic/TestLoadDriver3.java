package idv.david.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
java -Djdbc.drivers=com.mysql.jdbc.Driver TestLoadDriver3
*/

public class TestLoadDriver3 {
	private static final String URL = "jdbc:mysql://localhost:3306/HR?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) {

		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecting to database successfully! (連線成功！)");
			con.close();

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

}
