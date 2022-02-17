package com.roomType.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class RoomTypeDAO implements RoomTypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ROOM_TYPE (room_type_name,room_type_amount,room_type_content,room_type_sale_status,room_total_person,room_total_score,room_type_price) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM ROOM_TYPE order by room_type_id";
	private static final String GET_ONE_STMT = "SELECT * FROM ROOM_TYPE where room_type_id = ?";
	private static final String DELETE = "DELETE FROM ROOM_TYPE where room_type_id = ?";
	private static final String UPDATE = "UPDATE ROOM_TYPE set room_type_id=?, room_type_name=?, room_type_amount=?, room_type_content=?, room_type_sale_status=?, room_total_person=?, room_total_score=?, room_type_price=? where room_type_id = ?";


	@Override
	public int insert(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int roomTypeId = -1;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, roomTypeVO.getRoom_type_name());
			pstmt.setInt(2, roomTypeVO.getRoom_type_amount());
			pstmt.setString(3, roomTypeVO.getRoom_type_content());
			pstmt.setBoolean(4, roomTypeVO.getRoom_type_sale_status());
			pstmt.setInt(5, roomTypeVO.getRoom_total_person());
			pstmt.setInt(6, roomTypeVO.getRoom_total_score());
			pstmt.setInt(7, roomTypeVO.getRoom_type_price());

			int pk = pstmt.executeUpdate();
			
			//確定PK
			if(pk>0) {
				//找出PK
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				roomTypeId = rs.getInt(1);
				}
			

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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return roomTypeId;

	}

	@Override
	public void update(RoomTypeVO roomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, roomTypeVO.getRoom_type_id() );
			pstmt.setString(2, roomTypeVO.getRoom_type_name());
			pstmt.setInt(3, roomTypeVO.getRoom_type_amount());
			pstmt.setString(4, roomTypeVO.getRoom_type_content());
			pstmt.setBoolean(5, roomTypeVO.getRoom_type_sale_status());
			pstmt.setInt(6, roomTypeVO.getRoom_total_person());
			pstmt.setInt(7, roomTypeVO.getRoom_total_score());
			pstmt.setInt(8, roomTypeVO.getRoom_type_price());
			pstmt.setInt(9, roomTypeVO.getRoom_type_id() );


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
	public void delete(Integer room_type_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

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
	public RoomTypeVO findByPK(Integer room__type_id) {
		RoomTypeVO roomTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room__type_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomTypeVO.setRoom_type_name(rs.getString("room_type_name"));
				roomTypeVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoom_type_content(rs.getString("room_type_content"));
				roomTypeVO.setRoom_type_sale_status(rs.getBoolean("room_type_sale_status"));
				roomTypeVO.setRoom_total_person(rs.getInt("room_total_person"));
				roomTypeVO.setRoom_total_score(rs.getInt("room_total_score"));
				roomTypeVO.setRoom_type_price(rs.getInt("room_type_price"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return roomTypeVO;
	}

	@Override
	public List<RoomTypeVO> getAll() {
		List<RoomTypeVO> list = new ArrayList<RoomTypeVO>();
		RoomTypeVO roomTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				roomTypeVO = new RoomTypeVO();
				roomTypeVO.setRoom_type_id(rs.getInt("room_type_id"));
				roomTypeVO.setRoom_type_name(rs.getString("room_type_name"));
				roomTypeVO.setRoom_type_amount(rs.getInt("room_type_amount"));
				roomTypeVO.setRoom_type_content(rs.getString("room_type_content"));
				roomTypeVO.setRoom_type_sale_status(rs.getBoolean("room_type_sale_status"));
				roomTypeVO.setRoom_total_person(rs.getInt("room_total_person"));
				roomTypeVO.setRoom_total_score(rs.getInt("room_total_score"));
				roomTypeVO.setRoom_type_price(rs.getInt("room_type_price"));
				list.add(roomTypeVO); // Store the row in the list
			}

			// Handle any driver errors
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
