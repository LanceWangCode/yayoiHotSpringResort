package com.rolesAccessRight.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emplyee.model.EmplyeeVO;


public class RolesAccessRightJDBCDAO implements RolesAccessRightDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = "INSERT INTO ROLES_ACCESS_RIGHT (ROLES_ID,FUNCTION_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT ROLES_ID,FUNCTION_ID FROM ROLES_ACCESS_RIGHT ORDER BY ROLES_ID";
	private static final String GET_ONE_STMT = "SELECT ROLES_ID,FUNCTION_ID FROM ROLES_ACCESS_RIGHT WHERE ROLES_ID = ? AND FUNCTION_ID = ?";
	private static final String DELETE = "DELETE FROM ROLES_ACCESS_RIGHT WHERE ROLES_ID = ? AND FUNCTION_ID = ?";
	private static final String UPDATE = "UPDATE ROLES_ACCESS_RIGHT SET FUNCTION_ID=? WHERE ROLES_ID = ? AND FUNCTION_ID = ?";
	
	@Override
	public void insert(RolesAccessRightVO rolesAccessRightVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rolesAccessRightVO.getRoles_id());
			pstmt.setInt(2, rolesAccessRightVO.getFunction_id());
			
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
	public void update(RolesAccessRightVO rolesAccessRightVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, rolesAccessRightVO.getFunction_id());
			pstmt.setInt(2, rolesAccessRightVO.getRoles_id());
			pstmt.setInt(3, rolesAccessRightVO.getFunction_id());

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
	public void delete(Integer roles_id, Integer fuction_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, roles_id);
			pstmt.setInt(2, fuction_id);

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
	public RolesAccessRightVO findByPrimaryKey(Integer roles_id, Integer fuction_id) {
		RolesAccessRightVO rolesAccessRightVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, roles_id);
			pstmt.setInt(2, fuction_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rolesAccessRightVO = new RolesAccessRightVO();
				rolesAccessRightVO.setRoles_id(rs.getInt("roles_id"));
				rolesAccessRightVO.setFunction_id(rs.getInt("function_id"));
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return rolesAccessRightVO;
	}
	
	@Override
	public List<RolesAccessRightVO> getAll() {
		List<RolesAccessRightVO> list = new ArrayList<RolesAccessRightVO>();
		RolesAccessRightVO rolesAccessRightVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rolesAccessRightVO = new RolesAccessRightVO();
				rolesAccessRightVO.setRoles_id(rs.getInt("roles_id"));
				rolesAccessRightVO.setFunction_id(rs.getInt("function_id"));
				list.add(rolesAccessRightVO); // Store the row in the list
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {

		RolesAccessRightJDBCDAO dao = new RolesAccessRightJDBCDAO();

		// 新增
//		RolesAccessRightVO RolesAccessRightVO1 = new RolesAccessRightVO();
//		RolesAccessRightVO1.setRoles_id(2);
//		RolesAccessRightVO1.setFunction_id(3);
//		dao.insert(RolesAccessRightVO1);

		// 修改*
//		RolesAccessRightVO RolesAccessRightVO2 = new RolesAccessRightVO();
//		RolesAccessRightVO2.setRoles_id(1);
//		RolesAccessRightVO2.setFunction_id(3);
//		dao.update(RolesAccessRightVO2);

		// 刪除
//		RolesAccessRightVO RolesAccessRightVO3 = new RolesAccessRightVO();
//		RolesAccessRightVO3.setRoles_id(2);
//		RolesAccessRightVO3.setFunction_id(3);
//	    dao.delete(2, 3);
		
		// 查詢單一
//		RolesAccessRightVO rolesAccessRightVO4 = dao.findByPrimaryKey(1, 3);
//		System.out.print(rolesAccessRightVO4.getRoles_id() + ",");
//		System.out.println(rolesAccessRightVO4.getFunction_id());
//		System.out.println("---------------------");
		
		// 查詢all
		List<RolesAccessRightVO> list = dao.getAll();
		for (RolesAccessRightVO aRolesAccessRightVO : list) {
			System.out.print(aRolesAccessRightVO.getRoles_id() + ",");
			System.out.println(aRolesAccessRightVO.getFunction_id());
			System.out.println();
		}
	}
}
