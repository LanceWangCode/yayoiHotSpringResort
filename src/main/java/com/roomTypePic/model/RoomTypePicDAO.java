package com.roomTypePic.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomTypePicDAO implements RoomTypePicDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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
	private static final String DELETEBYROOMTYPEID = 
			"DELETE FROM ROOM_TYPE_PIC where room_type_id = ?";
	private static final String GET_FK_PIC = 
			"SELECT * FROM ROOM_TYPE_PIC where room_type_id = ?";

	@Override
	public void insert(RoomTypePicVO room_type_picVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, room_type_picVO.getRoom_type_id());
			
			pstmt.setBytes(2, room_type_picVO.getRoom_pic());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, room_type_picVO.getRoom_photo_id());
			pstmt.setInt(2, room_type_picVO.getRoom_type_id());
			
			pstmt.setBytes(3, room_type_picVO.getRoom_pic());
			pstmt.setInt(4, room_type_picVO.getRoom_photo_id());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}finally {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_photo_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
		}  catch (SQLException se) {
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
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	@Override
	public void deleteByRoomTypeId(Integer room_type_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEBYROOMTYPEID);

			pstmt.setInt(1, room_type_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<RoomTypePicVO> findByFK(Integer room_type_id) {
		List<RoomTypePicVO> list = new ArrayList<RoomTypePicVO>();
		RoomTypePicVO room_type_picVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FK_PIC);
			
			pstmt.setInt(1, room_type_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				room_type_picVO = new RoomTypePicVO();
				room_type_picVO.setRoom_photo_id(rs.getInt("room_photo_id"));
				room_type_picVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_type_picVO.setRoom_pic(rs.getBytes("room_pic"));
				list.add(room_type_picVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public RoomTypePicVO findByFKreturnVO(Integer room_type_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
