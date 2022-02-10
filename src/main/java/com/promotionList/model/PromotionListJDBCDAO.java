package com.promotionList.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionListJDBCDAO implements PromotionListDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/CFA104G1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = "INSERT INTO PROMOTION_LIST (PROMOTION_TITLE,PROMOTION_DATE) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT PROMOTION_ID, PROMOTION_TITLE, PROMOTION_DATE, PROMOTION_STATUS FROM PROMOTION_LIST ORDER BY PROMOTION_ID";
	private static final String GET_ONE_STMT = "SELECT PROMOTION_ID, PROMOTION_TITLE, PROMOTION_DATE, PROMOTION_STATUS FROM PROMOTION_LIST WHERE PROMOTION_ID = ?";
	private static final String DELETE = "DELETE FROM PROMOTION_LIST WHERE PROMOTION_ID = ?";
	private static final String UPDATE = "UPDATE PROMOTION_LIST SET PROMOTION_TITLE=?, PROMOTION_DATE=? WHERE PROMOTION_ID = ?";
	private static final String UPDATE_DESCRIPTION = "UPDATE PROMOTION_LIST SET PROMOTION_DESCRIPTION=? WHERE PROMOTION_ID = ?";
	private static final String UPDATE_STATUS = "UPDATE PROMOTION_LIST SET PROMOTION_STATUS=? WHERE PROMOTION_ID = ?";

	@Override
	public void insert(PromotionListVO promotionListVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promotionListVO.getPromotion_title());
			pstmt.setDate(2, promotionListVO.getPromotion_date());
			
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
	public void update(PromotionListVO promotionListVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setString(1, promotionListVO.getPromotion_title());
			pstmt.setDate(2, promotionListVO.getPromotion_date());
			pstmt.setInt(3, promotionListVO.getPromotion_id());
			
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
	public void updateDescription(PromotionListVO promotionListVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_DESCRIPTION);

			pstmt.setString(1, promotionListVO.getPromotion_description());
			pstmt.setInt(2, promotionListVO.getPromotion_id());
			
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
	public void updateStatus(PromotionListVO promotionListVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STATUS);

			pstmt.setBoolean(1, promotionListVO.getPromotion_status());
			pstmt.setInt(2, promotionListVO.getPromotion_id());
			
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
	public void delete(Integer promotion_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE);

			pstmt.setInt(1, promotion_id);

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
	public PromotionListVO findByPrimaryKey(Integer promotion_id) {
		PromotionListVO promotionListVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, promotion_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotionListVO = new PromotionListVO();
				promotionListVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionListVO.setPromotion_title(rs.getString("promotion_title"));
				promotionListVO.setPromotion_date(rs.getDate("promotion_date"));
				promotionListVO.setPromotion_status(rs.getBoolean("promotion_status"));
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
		return promotionListVO;
	}
	
	@Override
	public List<PromotionListVO> getAll() {
		List<PromotionListVO> list = new ArrayList<PromotionListVO>();
		PromotionListVO promotionListVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotionListVO = new PromotionListVO();
				promotionListVO.setPromotion_id(rs.getInt("promotion_id"));
				promotionListVO.setPromotion_title(rs.getString("promotion_title"));
				promotionListVO.setPromotion_date(rs.getDate("promotion_date"));
				promotionListVO.setPromotion_status(rs.getBoolean("promotion_status"));
				list.add(promotionListVO); // Store the row in the list
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
	
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}
	
	public static void main(String[] args) {

		PromotionListJDBCDAO dao = new PromotionListJDBCDAO();

		// 新增
//		promotionListVO1.setPromotion_title("JI3GG");
//        Date date = new Date(System.currentTimeMillis());
//		promotionListVO1.setPromotion_date(date);
//		dao.insert(promotionListVO1);
		
		// 修改
//		PromotionListVO promotionListVO2 = new PromotionListVO();
//		promotionListVO1.setPromotion_id(3);
//		promotionListVO1.setPromotion_title("AAAAA");
//        Date date = new Date(System.currentTimeMillis());
//		promotionListVO1.setPromotion_date(date);
//		dao.update(promotionListVO1);
		
		// 修改內文
//		PromotionListVO promotionListVO3 = new PromotionListVO();
//		promotionListVO1.setPromotion_id(3);
//		try {
//			String str = getLongString("C:/Users/Tibame_T14/Documents/asd.txt");
//			promotionListVO1.setPromotion_description(str);
//			dao.updateDescription(promotionListVO1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 修改狀態
		PromotionListVO promotionListVO4 = new PromotionListVO();
		promotionListVO4.setPromotion_id(2);
		promotionListVO4.setPromotion_status(true);
		dao.updateStatus(promotionListVO4);
		
//		// 刪除
//		PromotionListVO promotionListVO5 = new PromotionListVO();
//		promotionListVO1.setPromotion_id(3);
//		dao.delete(3);
		
		// 主鍵查詢
//		PromotionListVO promotionListVO6 = dao.findByPrimaryKey(1);
//		System.out.print(promotionListVO6.getPromotion_id() + ",");
//		System.out.print(promotionListVO6.getPromotion_title()+ ",");
//		System.out.print(promotionListVO6.getPromotion_date()+ ",");
//		System.out.println(promotionListVO6.getPromotion_status());
//		System.out.println("---------------------");
		
		// 主鍵全部
//		List<PromotionListVO> list = dao.getAll();
//		for (PromotionListVO aPromotionListVO : list) {
//			System.out.print(aPromotionListVO.getPromotion_id() + ",");
//			System.out.print(aPromotionListVO.getPromotion_title() + ",");
//			System.out.print(aPromotionListVO.getPromotion_date()+ ",");
//			System.out.println(aPromotionListVO.getPromotion_status());
//			System.out.println();
//		}
		
	}
	
}
