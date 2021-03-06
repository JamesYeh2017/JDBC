package com.iii.bb101.james;

import java.sql.*;

public class SQLExceptionDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {

			String qryStmt = "SELECT * FROM employe"; // error here
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("name = " + rs.getString("ename"));
				System.out.printf("salary =" + rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			System.out.println("Message : " + e.getMessage());
			System.out.println("Vendor code : " + e.getErrorCode());
		} 
	}// end of main()
}// end of class SQLExceptionDemo
