package com.iii.bb101.james;

import java.sql.*;
import java.io.*;

public class BLOBDemo {
	public static void main(String[] args) {
		
		String inFile = args[0];  //Run Configuration--->Arguments=String_Prompt
		String outFile = args[1];
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		try(Connection conn = DriverManager.getConnection(url, "root", "abcdggd123");) {
			//BlobName寫入資料庫
			String qryStmt = "SELECT photo FROM blobtest WHERE name = ?";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			stmt.setString(1, inFile);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String deleteStmt = "DELETE FROM blobtest WHERE name = ?"; 
				stmt = conn.prepareStatement(deleteStmt);
				stmt.setString(1, inFile);
				stmt.executeUpdate();
				System.out.println("Delete blob is successful!");
			}
			//Blob存入資料庫
			File f = new File(inFile);
			FileInputStream fis = new FileInputStream(f);
			String insertStmt = "INSERT INTO blobtest VALUES(?,?)";		
			stmt = conn.prepareStatement(insertStmt);
			stmt.setString(1, inFile);
			stmt.setBinaryStream(2, fis, f.length());
			stmt.executeUpdate();
			System.out.println("Insert blob is successful!");
			
			//擷取資料庫Blob存入
			stmt = conn.prepareStatement(qryStmt);
			stmt.setString(1, inFile);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				FileOutputStream fos = new FileOutputStream(outFile);
				Blob b = rs.getBlob("photo");
				byte[] data = b.getBytes(1, (int)b.length());  //(position 1,length())
				fos.write(data, 0, (int)b.length());  // (byte[],0,length())
				fos.close();
				System.out.println("File output is successful!");
			} // end of if (rs.next()) 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}// end of main()
}// end of class BLOBDemo 

//CREATE TABLE blobtest (
//		name		varchar(30)		PRIMARY KEY, 
//		photo		blob			NOT NULL
//	);


