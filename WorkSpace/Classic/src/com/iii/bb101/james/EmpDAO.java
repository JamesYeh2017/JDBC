package com.iii.bb101.james;

import java.util.*;
import java.sql.*;

public class EmpDAO implements IEmpDAO {
//sql�y�y
	private static final String INSERT_STMT = 
		"INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE employee SET ename=?, hiredate=?, salary=?, deptno=?, title=? WHERE empno=?";
	private static final String DELETE_STMT = 
		"DELETE FROM employee WHERE empno=?";
	
	private static final String GET_ONE_STMT = 
		"SELECT empno, ename, hiredate, salary, deptno, title FROM employee WHERE empno=?";
	private static final String GET_ALL_STMT = 
		"SELECT empno, ename, hiredate, salary, deptno, title FROM employee ORDER BY empno";
//getConnection()
	Connection conn = null;

	public void getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc?useSSL=false";
		conn = DriverManager.getConnection(url, "root", "abcdggd123");
	}
//insert(EmpVO emp)
	public int insert(EmpVO emp) throws SQLException {
		int updateCount = 0;
		PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
		pstmt.setInt(1, emp.getEmpno());
		pstmt.setString(2, emp.getEname());
		pstmt.setDate(3, emp.getHiredate());
		pstmt.setDouble(4, emp.getSalary());
		pstmt.setInt(5, emp.getDeptno());
		pstmt.setString(6, emp.getTitle());
		updateCount = pstmt.executeUpdate();
		return updateCount;
	}
//update(EmpVO emp)
	public int update(EmpVO emp) throws SQLException {
		int updateCount = 0;
		PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);
		pstmt.setString(1, emp.getEname());
		pstmt.setDate(2, emp.getHiredate());
		pstmt.setDouble(3, emp.getSalary());
		pstmt.setInt(4, emp.getDeptno());
		pstmt.setString(5, emp.getTitle());
		pstmt.setInt(6, emp.getEmpno());
		updateCount = pstmt.executeUpdate();
		return updateCount;
	}
//delete(int empno)
	public int delete(int empno) throws SQLException {
		int updateCount = 0;
		PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);
		pstmt.setInt(1, empno);
		updateCount = pstmt.executeUpdate();
		return updateCount;
	}
//public EmpVO findByPrimaryKey(int empno)
	public EmpVO findByPrimaryKey(int empno) throws SQLException {
		EmpVO emp = null;
		PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);
		pstmt.setInt(1, empno);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			emp = new EmpVO();
			emp.setEmpno(rs.getInt("empno"));
			emp.setEname(rs.getString("ename"));
			emp.setHiredate(rs.getDate("hiredate"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setDeptno(rs.getInt("deptno"));
			emp.setTitle(rs.getString("title"));
		}
		return emp;
	}
//public List<EmpVO> getAll()
	public List<EmpVO> getAll() throws SQLException {
		EmpVO emp = null;
		List<EmpVO> emps = new ArrayList<EmpVO>();  //���ȮɱNEmpVO�����x�s��ArrayList
		PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			emp = new EmpVO();
			emp.setEmpno(rs.getInt("empno"));	//sql���ȩ�JEmpVO���O
			emp.setEname(rs.getString("ename"));
			emp.setHiredate(rs.getDate("hiredate"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setDeptno(rs.getInt("deptno"));
			emp.setTitle(rs.getString("title"));
			
			emps.add(emp);
		}
		return emps;
	}
//closeConn()
	public void closeConn() throws SQLException {
		if (conn != null)
			conn.close();
	}
} // end of class EmpDAO