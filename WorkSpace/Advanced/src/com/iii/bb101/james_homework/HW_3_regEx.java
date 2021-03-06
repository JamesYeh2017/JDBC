package com.iii.bb101.james_homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW_3_regEx {

	public static void main(String[] args) throws IOException {
		
		File file = new File("./res");
		String[] images = file.list();		//��file��������
		FileInputStream fis = null;
		
		String regEx = "100[1-8].jpg";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try (Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			
			String insertStmt = "UPDATE emp SET photo=? WHERE empno=?";
			PreparedStatement ppstmt = conn.prepareStatement(insertStmt);
			int count = 0 ;
			for(String s : images){
				Matcher m = p.matcher(s);
				
				if(m.matches()){
				fis = new FileInputStream(file + "/" + s);
				int empno = Integer.valueOf(s.substring(0, 4));
				ppstmt.setBinaryStream(1, fis,file.length());
				ppstmt.setInt(2, empno);
				ppstmt.addBatch();
				count++;
				if(count==3){
					int[] a = ppstmt.executeBatch();
					System.out.println("Insert photn successfully count:" + a.length);

					count = 0;
				}
				}else{
					System.out.println(s);
				}
				
			}
			int[] a = ppstmt.executeBatch();
			System.out.println("Insert photn successfully count:" + a.length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
