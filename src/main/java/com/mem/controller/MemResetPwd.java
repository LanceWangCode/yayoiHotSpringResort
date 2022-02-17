package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import util.MailService;
import util.RamdomCode;


@WebServlet("/MemResetPwd")
public class MemResetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		JSONObject jsonObject = new JSONObject();
		try {
			String mem_email = req.getParameter("mem_email");
			String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			if (mem_email == null || mem_email.trim().length() == 0) {
				jsonObject.put("error","電子郵件地址: 請勿空白");
			} else if (!mem_email.trim().matches(emailReg)) { // 以下練習正則(規)表示式(regular-expression)
				jsonObject.put("error","電子郵件地址格式錯誤");
			}
			
			if (!(jsonObject.isNull("error"))) {
				PrintWriter out = res.getWriter();
				out.print(jsonObject.toString());
		        return;
			}
			
			MemVO memVO = new MemVO();
			memVO.setMem_email(mem_email);				
			MemService memSvc = new MemService();
			if (memSvc.checkMem(mem_email)) {
				jsonObject.put("error","該Email並沒有註冊過會員!");
			}
			// 我要怎麼寫出資料到前端
			if (!(jsonObject.isNull("error"))) {
				PrintWriter out = res.getWriter();
				out.print(jsonObject.toString());
		        return;
			}
			RamdomCode code = new RamdomCode();
			String pwd = code.genAuthCode();

			memVO = memSvc.resetPwd(mem_email, pwd);
			
			new Thread(() -> {
				MailService mailService = new MailService();
				String subject = "變更密碼通知";
				String messageText = "親愛的會員您好: " + "\n" + "這是您的新密碼:" + pwd + "\n" + "請用此密碼登入後再變更您的密碼!";
				mailService.sendMail(mem_email, subject, messageText);
			}).start();
			
			jsonObject.put("scucesss", "我們已成功寄送新的密碼到您的信箱");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
