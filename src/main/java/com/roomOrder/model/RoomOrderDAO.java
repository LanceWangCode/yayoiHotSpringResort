package com.roomOrder.model;

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





public class RoomOrderDAO implements RoomOrderDAO_interface {
	
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
	"INSERT INTO ROOM_ORDER (mem_id,order_date,room_order_status,room_charge,room_review) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT room_order_id,mem_id,order_date,room_order_status,room_charge,room_review FROM ROOM_ORDER order by room_order_id";
	private static final String GET_ONE_STMT = 
	"SELECT * FROM ROOM_ORDER where room_order_id = ?";
	private static final String DELETE = 
	"DELETE FROM ROOM_ORDER where room_order_id = ?";
	private static final String UPDATE = 
	"UPDATE ROOM_ORDER set mem_id=?, order_date=?, room_order_status=?, room_charge=?, room_review=? where room_order_id = ?";

	@Override
	public void insert(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, roomOrderVO.getMem_id());
			pstmt.setDate(2, roomOrderVO.getOrder_date());
			pstmt.setInt(3, roomOrderVO.getRoom_order_status());
			pstmt.setInt(4, roomOrderVO.getRoom_charge());
			pstmt.setInt(5, roomOrderVO.getRoom_review());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomOrderVO.getMem_id());
			pstmt.setDate(2, roomOrderVO.getOrder_date());
			pstmt.setInt(3, roomOrderVO.getRoom_order_status());
			pstmt.setInt(4, roomOrderVO.getRoom_charge());
			pstmt.setInt(5, roomOrderVO.getRoom_review());
			pstmt.setInt(6, roomOrderVO.getRoom_order_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer room_order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_order_id);

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
	public RoomOrderVO findByPrimaryKey(Integer room_order_id) {
		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderVO.setMem_id(rs.getInt("mem_id"));
				roomOrderVO.setOrder_date(rs.getDate("order_date"));
				roomOrderVO.setRoom_order_status(rs.getInt("room_order_status"));
				roomOrderVO.setRoom_charge(rs.getInt("room_charge"));
				roomOrderVO.setRoom_review(rs.getInt("room_review"));
	
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
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> getAll() {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderVO.setMem_id(rs.getInt("mem_id"));
				roomOrderVO.setOrder_date(rs.getDate("order_date"));
				roomOrderVO.setRoom_order_status(rs.getInt("room_order_status"));
				roomOrderVO.setRoom_charge(rs.getInt("room_charge"));
				roomOrderVO.setRoom_review(rs.getInt("room_review"));
				list.add(roomOrderVO); // Store the row in the list
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
	
	
	

}
