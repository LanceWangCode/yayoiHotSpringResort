package com.functionAccessRight.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionAccessRightJDBCDAO implements FunctionAccessRightDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Function_Access_Right (FUNCTION_ID,FUNCTION_NAME) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT FUNCTION_ID,FUNCTION_NAME FROM Function_Access_Right ORDER BY FUNCTION_ID";
	private static final String GET_ONE_STMT = 
			"SELECT FUNCTION_ID,FUNCTION_NAME FROM Function_Access_Right WHERE FUNCTION_ID = ?";
	private static final String UPDATE = 
			"UPDATE Function_Access_Right SET FUNCTION_NAME=? WHERE FUNCTION_ID = ?";
	private static final String DELETE = 
			"DELETE FROM Function_Access_Right where FUNCTION_ID = ?";
	
	@Override
	public void insert(FunctionAccessRightVO functionAccessRightVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, functionAccessRightVO.getFunction_id());
			pstmt.setString(2, functionAccessRightVO.getFunction_name());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void update(FunctionAccessRightVO functionAccessRightVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, functionAccessRightVO.getFunction_name());
			pstmt.setInt(2, functionAccessRightVO.getFunction_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
		
	
	@Override
	public void delete(Integer function_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, function_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
		
	
	@Override
	public FunctionAccessRightVO findByPrimaryKey(Integer function_id) {
		FunctionAccessRightVO functionAccessRightVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, function_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				functionAccessRightVO = new FunctionAccessRightVO();
				functionAccessRightVO.setFunction_id(rs.getInt("FUNCTION_ID"));
				functionAccessRightVO.setFunction_name(rs.getString("FUNCTION_NAME"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return functionAccessRightVO;
	}
	@Override
	public List<FunctionAccessRightVO> getAll() {
		List<FunctionAccessRightVO> list = new ArrayList<FunctionAccessRightVO>();
		FunctionAccessRightVO functionAccessRightVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				functionAccessRightVO = new FunctionAccessRightVO();
				functionAccessRightVO.setFunction_id(rs.getInt("FUNCTION_ID"));
				functionAccessRightVO.setFunction_name(rs.getString("FUNCTION_NAME"));
				list.add(functionAccessRightVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	


	
	public static void main(String[] args) {
		FunctionAccessRightJDBCDAO dao = new FunctionAccessRightJDBCDAO();
		FunctionAccessRightVO vo = new FunctionAccessRightVO();

		// 新增
		vo.setFunction_id(1);
		vo.setFunction_name("新增房型");
		dao.insert(vo);
		
		//update
//		vo.setFunction_id(3);
//		vo.setFunction_name("c8 c8 ");
//		dao.update(vo);
		
		//find
//		vo=dao.findByPrimaryKey(3);
//		System.out.println(vo.getFunction_id());
//		System.out.println(vo.getFunction_name());
		
		//findall
		List<FunctionAccessRightVO> list = dao.getAll();
		for (FunctionAccessRightVO functionAccessRightVO : list) {
			System.out.print(functionAccessRightVO.getFunction_id() + ",");
			System.out.print(functionAccessRightVO.getFunction_name() + ",");
			System.out.println();
		

		}
	}

}
