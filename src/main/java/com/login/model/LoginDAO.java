package com.login.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDAO implements LoginDAO_interface {

	private static final String URL = "jdbc:mysql://localhost:3306/cfa104g1db?serverTimezone=Asia/Taipei";
	private static final String USER = "root";
	private static final String PWD = "12345678";
	
	
	//// 使用JNDI
	//private static DataSource ds = null;
	//static {
	//	try {
	//		Context ctx = new InitialContext();
	//		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cfa104g1DB");
	//	} catch (NamingException e) {
	//		e.printStackTrace();
	//	}
	//}

	@Override
	public LoginVO selectByUsernameAndPassowrd(String username, String password) {
		//Connection con = null;
		//PreparedStatement pstmt = null;
		
		//String sql = "select * from EMPLYEE where EMP_ACCOUNT = ?  and EMP_PASSWORD	 = ?";

		//try {
		
		String sql = "select * from EMPLYEE where EMP_ACCOUNT = ?  and EMP_PASSWORD = ?";
		try (
			Connection con = DriverManager.getConnection(URL, USER, PWD);
			PreparedStatement pstmt = con.prepareStatement(sql);
		)
		{
			//con = ds.getConnection();
			//pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			try (ResultSet rs =  pstmt.executeQuery()) {
				if (rs.next()) {
					LoginVO LoginVO = new LoginVO();
					LoginVO.setEmpno(rs.getInt("empno"));
					LoginVO.setRoles_id(rs.getInt("roles_id"));
					LoginVO.setEmp_name(rs.getString("emp_name"));
					LoginVO.setEmp_account(rs.getString("emp_account"));
					LoginVO.setEmp_password(rs.getString("emp_password"));
					LoginVO.setEmp_status(rs.getString("emp_status"));
					return LoginVO;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}	
}