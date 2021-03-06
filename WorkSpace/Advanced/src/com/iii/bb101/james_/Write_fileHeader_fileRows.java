package com.iii.bb101.james_;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Write_fileHeader_fileRows {

	public static void main(String[] args) {
		
		try(Connection conn = DriverManager.getConnection(SQLInfo.getConnUrl(), SQLInfo.getAccount(), SQLInfo.getPasswd());){
			
			String qryStmt = "SELECT * FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			ResultSet rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("./hw2.dat"), "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			StringBuilder sb = new StringBuilder();			
						
			for (int i = 1 ; i <=count; i++){ //6欄
				sb.append(rsmd.getColumnLabel(i) + "(" + rsmd.getColumnType(i) + ", "
						+ rsmd.getColumnTypeName(i)+"), \r\n");
			}
			sb.append("\r\n");   // next line
			bw.write(sb.toString()); // write header to file
			
			while (rs.next()){
				StringBuilder sb1 = new StringBuilder();//每換一筆資料就會重新new一個一新的
				for(int i = 1; i <= count ; i++){
	         		sb1.append(rs.getString(i) + ", ");
				}
				sb1.append("\r\n"); // next line
				bw.write(sb1.toString()); // write each row to file
			}		
			
			bw.close();
//			PrintWriter pw = 
//				new PrintWriter(new BufferedOutputStream(new FileOutputStream("res\\text2.txt", true)));
//
//			int count = rsmd.getColumnCount();
//
//			for (int i = 1; i <= count; i++) {
//				pw.print(rsmd.getColumnLabel(i) + "(" + rsmd.getColumnType(i) + ", " + rsmd.getColumnTypeName(i)
//						+ "), ");
//			}
//			pw.println(); // next line
//
//			while (rs.next()) {
//				for (int i = 1; i <= count; i++) {
//					pw.print(rs.getString(i) + ", ");
//				}
//				pw.println();
//				; // next line
//			}
//			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException ie) { 
			ie.printStackTrace();
		}
	}
}

class SQLInfo{
	static String getConnUrl(){
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		return url;
	}
	static String getAccount(){
		String user = "root";
		return user;
	}
	static String getPasswd(){
		String passwd = "abcdggd123";
		return passwd;
	}
}