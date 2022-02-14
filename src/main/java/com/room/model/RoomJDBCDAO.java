package com.room.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomJDBCDAO implements RoomDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?rewriteBatchedStatements=true&serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
		"INSERT INTO CFA104G1.ROOM (room_type_id,qtyofbeds,room_guest_name,room_sale_status,room_status) "
		+ "VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT room_id,room_type_id,qtyofbeds,room_guest_name,room_sale_status,room_status FROM CFA104G1.ROOM order by room_id";
	private static final String GET_ONE_STMT = 
		"SELECT room_id,room_type_id,qtyofbeds,room_guest_name,room_sale_status,room_status FROM CFA104G1.ROOM where room_id = ?";
	private static final String DELETE = 
		"DELETE FROM CFA104G1.ROOM where room_id = ?";
	private static final String UPDATE = 
		"UPDATE CFA104G1.ROOM set room_id=?, room_type_id=?, qtyofbeds=?, room_guest_name=?, room_sale_status=?, room_status=? where room_id = ?";
	
	
	@Override
	public void insert(RoomVO roomVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, roomVo.getRoom_type_id());
			pstmt.setInt(2, roomVo.getQtyofbeds());
			pstmt.setString(3, roomVo.getRoom_guest_name());
			pstmt.setBoolean(4, roomVo.getRoom_sale_status());
			pstmt.setInt(5, roomVo.getRoom_status());

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
	public void update(RoomVO roomVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomVo.getRoom_id() );
			pstmt.setInt(2, roomVo.getRoom_type_id());
			pstmt.setInt(3, roomVo.getQtyofbeds());
			pstmt.setString(4, roomVo.getRoom_guest_name());
			pstmt.setBoolean(5, roomVo.getRoom_sale_status());
			pstmt.setInt(6, roomVo.getRoom_status());
			pstmt.setInt(7, roomVo.getRoom_id());

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
	public void delete(Integer room_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_id);

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
	public RoomVO findByPK(Integer room_id) {
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				roomVO = new RoomVO();
				roomVO.setRoom_id(rs.getInt("room_id"));
				roomVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomVO.setQtyofbeds(rs.getInt("qtyofbeds"));
				roomVO.setRoom_guest_name(rs.getString("room_guest_name"));
				roomVO.setRoom_sale_status(rs.getBoolean("room_sale_status"));
				roomVO.setRoom_status(rs.getInt("room_status"));
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
		return roomVO;
	}
	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				roomVO = new RoomVO();
				roomVO.setRoom_id(rs.getInt("room_id"));
				roomVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomVO.setQtyofbeds(rs.getInt("qtyofbeds"));
				roomVO.setRoom_guest_name(rs.getString("room_guest_name"));
				roomVO.setRoom_sale_status(rs.getBoolean("room_sale_status"));
				roomVO.setRoom_status(rs.getInt("room_status"));
				list.add(roomVO); // Store the row in the list
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
	

}
