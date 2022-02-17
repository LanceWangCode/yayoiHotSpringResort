package com.roomSchedule.model;

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





public class RoomScheduleDAO implements RoomScheduleDAO_interface {
	
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
	"INSERT INTO ROOM_SCHEDULE (room_type_id,room_schedule_date,room_amount,room_rsv_booked) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT room_schedule_id,room_type_id,room_schedule_date,room_amount,room_rsv_booked FROM ROOM_SCHEDULE order by room_schedule_id";
	private static final String GET_ONE_STMT = 
	"SELECT room_schedule_id,room_type_id,room_schedule_date,room_amount,room_rsv_booked FROM ROOM_SCHEDULE where room_schedule_id = ?";
	private static final String DELETE = 
	"DELETE FROM ROOM_SCHEDULE where room_schedule_id = ?";
	private static final String UPDATE = 
	"UPDATE ROOM_SCHEDULE set room_schedule_id=?,room_type_id=?, room_schedule_date=?, room_amount=?, room_rsv_booked=? where room_schedule_id=?";

	@Override
	public void insert(RoomScheduleVO room_scheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, room_scheduleVO.getRoom_type_id());
			pstmt.setDate(2, room_scheduleVO.getRoom_schedule_date());
			pstmt.setInt(3, room_scheduleVO.getRoom_amount());
			pstmt.setInt(4, room_scheduleVO.getRoom_rsv_booked());

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
	public void update(RoomScheduleVO room_scheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, room_scheduleVO.getRoom_type_id());
			pstmt.setDate(2, room_scheduleVO.getRoom_schedule_date());
			pstmt.setInt(3, room_scheduleVO.getRoom_amount());
			pstmt.setInt(4, room_scheduleVO.getRoom_rsv_booked());
			pstmt.setInt(5, room_scheduleVO.getRoom_schedule_id());

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
	public void delete(Integer room_schedule_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_schedule_id);

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
	public RoomScheduleVO findByPrimaryKey(Integer room_schedule_id) {
		RoomScheduleVO room_scheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_schedule_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_scheduleVO = new RoomScheduleVO();
				room_scheduleVO.setRoom_schedule_id(rs.getInt("room_schedule_id"));
				room_scheduleVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_scheduleVO.setRoom_schedule_date(rs.getDate("room_schedule_date"));
				room_scheduleVO.setRoom_amount(rs.getInt("room_amount"));
				room_scheduleVO.setRoom_rsv_booked(rs.getInt("room_rsv_booked"));
	
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
		return room_scheduleVO;
	}

	@Override
	public List<RoomScheduleVO> getAll() {
		List<RoomScheduleVO> list = new ArrayList<RoomScheduleVO>();
		RoomScheduleVO room_scheduleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				room_scheduleVO = new RoomScheduleVO();
				room_scheduleVO.setRoom_schedule_id(rs.getInt("room_schedule_id"));
				room_scheduleVO.setRoom_type_id(rs.getInt("room_type_id"));
				room_scheduleVO.setRoom_schedule_date(rs.getDate("room_schedule_date"));
				room_scheduleVO.setRoom_amount(rs.getInt("room_amount"));
				room_scheduleVO.setRoom_rsv_booked(rs.getInt("room_rsv_booked"));
				list.add(room_scheduleVO); // Store the row in the list
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
