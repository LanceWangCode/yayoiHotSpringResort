package com.roomTypePic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;







public class RoomTypePicJDBCDAO implements RoomTypePicDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "qazwsx";

	private static final String INSERT_STMT = 
	"INSERT INTO ROOM_TYPE_PIC (room_type_id,room_pic) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT * FROM ROOM_TYPE_PIC order by room_photo_id";
	private static final String GET_ONE_STMT = 
	"SELECT * FROM ROOM_TYPE_PIC where room_photo_id = ?";
	private static final String DELETE = 
	"DELETE FROM ROOM_TYPE_PIC where room_photo_id = ?";
	private static final String UPDATE = 
	"UPDATE ROOM_TYPE_PIC set room_photo_id=?, room_type_id=?, room_pic=? where room_photo_id = ?";

	@Override
	public void insert(RoomTypePicVO room_type_picVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, room_type_picVO.getRoom_type_id());
			pstmt.setBytes(2, room_type_picVO.getRoom_pic());
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
	public void update(RoomTypePicVO room_type_picVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, room_type_picVO.getRoom_photo_id());
			pstmt.setInt(2, room_type_picVO.getRoom_type_id());
			pstmt.setBytes(3, room_type_picVO.getRoom_pic());

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
	public void delete(Integer room_photo_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_photo_id);

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
	public RoomTypePicVO findByPrimaryKey(Integer room_photo_id) {
		RoomTypePicVO room_type_picVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_photo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				room_type_picVO = new RoomTypePicVO();
				room_type_picVO.setRoom_photo_id(rs.getInt("room_photo_id"));
				room_type_picVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_type_picVO.setRoom_pic(rs.getBytes("room_pic"));
				
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
		return room_type_picVO;
	}

	@Override
	public List<RoomTypePicVO> getAll() {
		List<RoomTypePicVO> list = new ArrayList<RoomTypePicVO>();
		RoomTypePicVO room_type_picVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_type_picVO = new RoomTypePicVO();
				room_type_picVO.setRoom_photo_id(rs.getInt("room_photo_id"));
				room_type_picVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_type_picVO.setRoom_pic(rs.getBytes("room_pic"));
				list.add(room_type_picVO); // Store the row in the list
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

	@Override
	public void deleteByRoomTypeId(Integer room_type_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoomTypePicVO> findByFK(Integer room_photo_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomTypePicVO findByFKreturnVO(Integer room_type_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
