package com.iii.BB101.james;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Insert one employee
public class InsertDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
			conn = DriverManager.getConnection(connUrl, "root", "abcdggd123");
			

//			java.util.Date today = new java.util.Date();
//			long long1 = today.getTime();
//			java.sql.Date ds1 = new java.sql.Date(long1);
			
			java.sql.Date ds1 = new java.sql.Date(new java.util.Date().getTime());
			String now = ds1.toString();
			
//			java.util.Date today = new java.util.Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
//			System.out.println(sdf.format(today));
			
//			java.util.Calendar today = java.util.Calendar.getInstance(); //Calendar建構子不能new
//			//只要有getInstance()建構子通常是不能new
//			int year = today.get(Calendar.YEAR);
//			int month = today.get(Calendar.MONTH)+1;
//			int day = today.get(Calendar.DATE);
//			System.out.println(year + "/" + month + "/" + day);
			
			String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setInt(1, 1009);
			pstmt.setString(2, "Jean Tsao");
			pstmt.setString(3, now);
			pstmt.setDouble(4, 55000);
			pstmt.setInt(5, 100);
			pstmt.setString(6, "senior engineer");
			
			int num = pstmt.executeUpdate();	//Update
			System.out.println("insert count = " + num);
			
			pstmt = conn.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pstmt.executeQuery();	//Query
			
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
