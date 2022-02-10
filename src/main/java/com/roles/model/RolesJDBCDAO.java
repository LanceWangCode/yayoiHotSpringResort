package com.roles.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesJDBCDAO implements RolesDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO ROLES (ROLES_ID,ROLES_NAME) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT ROLES_ID,ROLES_NAME FROM ROLES ORDER BY ROLES_ID";
	private static final String GET_ONE_STMT = "SELECT ROLES_ID,ROLES_NAME FROM ROLES WHERE ROLES_ID = ?";

	private static final String UPDATE = "UPDATE ROLES SET ROLES_NAME=? WHERE ROLES_ID = ?";

	@Override
	public void insert(RolesVO rolesVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rolesVO.getRoles_id());
			pstmt.setString(2, rolesVO.getRoles_name());

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
	public void update(RolesVO rolesVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, rolesVO.getRoles_name());
			pstmt.setInt(2, rolesVO.getRoles_id());

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
	public RolesVO findByPrimaryKey(Integer roles_id) {
		RolesVO rolesVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roles_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rolesVO = new RolesVO();
				rolesVO.setRoles_id(rs.getInt("roles_id"));
				rolesVO.setRoles_name(rs.getString("roles_name"));
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
		return rolesVO;
	}

	@Override
	public List<RolesVO> getAll() {
		List<RolesVO> list = new ArrayList<RolesVO>();
		RolesVO rolesVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				rolesVO = new RolesVO();
				rolesVO.setRoles_id(rs.getInt("roles_id"));
				rolesVO.setRoles_name(rs.getString("roles_name"));
				list.add(rolesVO); // Store the row in the list
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

		RolesJDBCDAO dao = new RolesJDBCDAO();

//		// 新增
//		RolesVO rolesVO = new RolesVO();
//		rolesVO.setRoles_id(2);
//		rolesVO.setRoles_name("MANAGER");
//		
//		dao.insert(rolesVO);
		
//		// 修改
//		RolesVO rolesVO2 = new RolesVO();
//		rolesVO2.setRoles_name("活動管理員");
//		rolesVO2.setRoles_id(1);
//		dao.update(rolesVO2);

		// 主鍵查詢
//		RolesVO rolesVO3 = dao.findByPrimaryKey(1);
//		System.out.print(rolesVO3.getRoles_id() + ",");
//		System.out.print(rolesVO3.getRoles_name());
//		System.out.println("---------------------");
//
//		// 查詢
		List<RolesVO> list = dao.getAll();
		for (RolesVO aRoles : list) {
		System.out.print(aRoles.getRoles_id() + ",");
		System.out.print(aRoles.getRoles_name());
		System.out.println();
	    }
	}
}
