package com.iii.bb101.james;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetMetaDataDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			//欄位資訊
			String qryStmt = "SELECT * FROM department";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			ResultSet rs = stmt.executeQuery();
			
//透過ResultSet的getMetaData()取得ResultSetMetaData物件=rsmd
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			System.out.println(count);  	//2欄
			for(int i = 1; i <= count; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "(" + rsmd.getColumnType(i) + ", "
					+ rsmd.getColumnTypeName(i)+"), ");
			}
			System.out.print("\n");
			
			while(rs.next()) {  //rows 每筆記錄
	     		for(int i = 1; i <= count; i++)
	     			System.out.print(rs.getString(i) + ", ");	     			
	     		System.out.print("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of main()
}// end of class ResultSetMetaDataDemo 
