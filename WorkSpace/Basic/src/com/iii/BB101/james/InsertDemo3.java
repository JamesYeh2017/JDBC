package com.iii.BB101.james;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

// Insert one employee
public class InsertDemo3 {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
			conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");
			

			java.util.Date du = new java.util.Date();
			long long1 = du.getTime();
			java.sql.Date ds1 = new java.sql.Date(long1);
			String now2 = ds1.toString();
			
			
			String insStmt = "INSERT INTO employee VALUES (?, ?, CURDATE, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");
			pstmt.setDouble(3, 55000);
			pstmt.setInt(4, 100);
			pstmt.setString(5, "senior engineer");
			
			int num = pstmt.executeUpdate();
			System.out.println("insert count = " + num);
			
			pstmt = conn.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
				System.out.println("salary = " + rs.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) { 
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class InsertDemo
