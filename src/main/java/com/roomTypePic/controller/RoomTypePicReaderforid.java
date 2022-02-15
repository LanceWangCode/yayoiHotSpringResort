package com.roomTypePic.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//@WebServlet("/RoomTypePicReader")
public class RoomTypePicReaderforid extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String id = req.getParameter("room_photo_id");
			ResultSet rs = stmt.executeQuery(
//				"SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
			    "SELECT room_pic FROM ROOM_TYPE_PIC WHERE room_photo_id ="+id);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("room_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			}  else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
//				InputStream in = getServletContext().getResourceAsStream("");
//				byte[] b = new byte[in.available()];
//				in.read(b);
//				out.write(b);
//				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
//			InputStream in = getServletContext().getResourceAsStream("/front-end/mem/images/null.jpg");
//			byte[] b = new byte[in.available()];
//			in.read(b);
//			out.write(b);
//			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA104G1");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}