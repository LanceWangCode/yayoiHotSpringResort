package com.roomOrderList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;








public class RoomOrderListJDBCDAO implements RoomOrderListDAO_interface  {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "qazwsx";

	private static final String INSERT_STMT = 
	"INSERT INTO CFA104G1.ROOM_ORDER_LIST (room_id,room_order_id,number_of_people,arrival_date,departure_date,special_req,room_price,service_order_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT room_order_list_id,room_id,room_order_id,number_of_people,arrival_date,departure_date,special_req,room_price,service_order_id FROM CFA104G1.ROOM_ORDER_LIST order by room_order_list_id";
	private static final String GET_ONE_STMT = 
	"SELECT room_order_list_id,room_id,room_order_id,number_of_people,arrival_date,departure_date,special_req,room_price,service_order_id FROM CFA104G1.ROOM_ORDER_LIST where room_order_list_id = ?";
	private static final String DELETE = 
	"DELETE FROM CFA104G1.ROOM_ORDER_LIST where room_order_list_id = ?";
	private static final String UPDATE = 
	"UPDATE CFA104G1.ROOM_ORDER_LIST set room_id=?,room_order_id=?, number_of_people=?, arrival_date=?, departure_date=?, special_req=?,room_price=?,service_order_id=? where room_order_list_id = ?";

	@Override
	public void insert(RoomOrderListVO roomOrderListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, roomOrderListVO.getRoom_id());
			pstmt.setInt(2, roomOrderListVO.getRoom_order_id());
			pstmt.setInt(3, roomOrderListVO.getNumber_of_people());
			pstmt.setDate(4, roomOrderListVO.getArrival_date());
			pstmt.setDate(5, roomOrderListVO.getDeparture_date());
			pstmt.setString(6, roomOrderListVO.getSpecial_req());
			pstmt.setInt(7, roomOrderListVO.getRoom_price());
			pstmt.setInt(8, roomOrderListVO.getService_order_id());
			pstmt.setInt(9, roomOrderListVO.getRoom_type_id());
			

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
	public void update(RoomOrderListVO roomOrderListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
	

			
			pstmt.setInt(1, roomOrderListVO.getRoom_id());
			pstmt.setInt(2, roomOrderListVO.getRoom_order_id());
			pstmt.setInt(3, roomOrderListVO.getNumber_of_people());
			pstmt.setDate(4, roomOrderListVO.getArrival_date());
			pstmt.setDate(5, roomOrderListVO.getDeparture_date());
			pstmt.setString(6, roomOrderListVO.getSpecial_req());
			pstmt.setInt(7, roomOrderListVO.getRoom_price());
			pstmt.setInt(8, roomOrderListVO.getService_order_id());
			pstmt.setInt(9, roomOrderListVO.getRoom_type_id());
			pstmt.setInt(10, roomOrderListVO.getRoom_order_list_id());
		
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
	public void delete(Integer room_order_list_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, room_order_list_id);

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
	public RoomOrderListVO findByPrimaryKey(Integer room_order_list_id) {
		RoomOrderListVO roomOrderListVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, room_order_list_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderListVO = new RoomOrderListVO();
				roomOrderListVO.setRoom_order_list_id(rs.getInt("room_order_list_id"));
				roomOrderListVO.setRoom_id(rs.getInt("room_id"));
				roomOrderListVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderListVO.setNumber_of_people(rs.getInt("number_of_people"));
				roomOrderListVO.setArrival_date(rs.getDate("arrival_date"));
				roomOrderListVO.setDeparture_date(rs.getDate("departure_date"));
				roomOrderListVO.setSpecial_req(rs.getString("special_req"));
				roomOrderListVO.setRoom_price(rs.getInt("room_price"));
				roomOrderListVO.setService_order_id(rs.getInt("service_order_id"));
				roomOrderListVO.setRoom_type_id(rs.getInt("room_order_list_id"));
				
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
		return roomOrderListVO;
	}

	@Override
	public List<RoomOrderListVO> getAll() {
		List<RoomOrderListVO> list = new ArrayList<RoomOrderListVO>();
		RoomOrderListVO roomOrderListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderListVO = new RoomOrderListVO();
				roomOrderListVO.setRoom_order_list_id(rs.getInt("room_order_list_id"));
				roomOrderListVO.setRoom_id(rs.getInt("room_id"));
				roomOrderListVO.setRoom_order_id(rs.getInt("room_order_id"));
				roomOrderListVO.setNumber_of_people(rs.getInt("number_of_people"));
				roomOrderListVO.setArrival_date(rs.getDate("arrival_date"));
				roomOrderListVO.setDeparture_date(rs.getDate("departure_date"));
				roomOrderListVO.setSpecial_req(rs.getString("special_req"));
				roomOrderListVO.setRoom_price(rs.getInt("room_price"));
				roomOrderListVO.setService_order_id(rs.getInt("service_order_id"));
				roomOrderListVO.setRoom_type_id(rs.getInt("room_order_list_id"));
				list.add(roomOrderListVO); // Store the row in the list
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
		RoomOrderListJDBCDAO dao = new RoomOrderListJDBCDAO();

		// 新增
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setProduct_describtion("商品描述");
//		productVO1.setProduct_price(199);
//		productVO1.setProduct_name("商品名稱");
//		productVO1.setProduct_quantity(3);
//		productVO1.setProduct_status(1);
//		dao.insert(productVO1);

		// 修改
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setProduct_id(7001);
//		productVO2.setProduct_describtion("修改商品描述");
//		productVO2.setProduct_price(999);
//		productVO2.setProduct_name("修改商品名稱");
//		productVO2.setProduct_quantity(1);
//		productVO2.setProduct_status(0);
//		dao.update(productVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		Room_order_listVO room_order_listVO = dao.findByPrimaryKey(1);
//		System.out.print(room_order_listVO.getRoom_order_list_id() + ",");
//		System.out.print(room_order_listVO.getRoom_order_list_id() + ",");
//		ProductVO productVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(productVO3.getProduct_id() + ",");
//		System.out.print(productVO3.getProduct_describtion() + ",");
//		System.out.print(productVO3.getProduct_price() + ",");
//		System.out.print(productVO3.getProduct_name() + ",");
//		System.out.print(productVO3.getProduct_quantity() + ",");
//		System.out.print(productVO3.getProduct_status() + ",");
	//
//		System.out.println("---------------------");

		// 查詢
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProduct : list) {
//			System.out.print(aProduct.getProduct_id() + ",");
//			System.out.print(aProduct.getProduct_describtion() + ",");
//			System.out.print(aProduct.getProduct_price() + ",");
//			System.out.print(aProduct.getProduct_name() + ",");
//			System.out.print(aProduct.getProduct_quantity() + ",");
//			System.out.print(aProduct.getProduct_status() + ",");
//			System.out.println();
//		}
		
	}


	
	
	

}



