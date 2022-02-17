package com.mem.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemJDBCDAO();
	}

	public MemVO addMem(String mem_email, String mem_password, String mem_name, String mem_phone, String mem_address) {

		MemVO memVO = new MemVO();
		memVO.setMem_email(mem_email);
		memVO.setMem_password(mem_password);
		memVO.setMem_name(mem_name);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_address(mem_address);
		dao.insert(memVO);
		return memVO;
	}
	
	public MemVO addMemPic(String mem_email, byte[] mem_pic) {

		MemVO memVO = new MemVO();
		memVO.setMem_email(mem_email);
		memVO.setMem_pic(mem_pic);
		dao.updatePic(memVO);
		return memVO;
	}

	public byte[] getPictureByteArray(InputStream in) throws IOException {
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		in.close();
		return buffer;
	}

	public boolean checkMem(String mem_email) {

		if (dao.selectByEmail(mem_email) == null) {
			return true;
		} else {
			return false;
		}

	}
	
	public MemVO updateMem(Integer mem_id, String mem_name, String mem_phone, String mem_address) {

		MemVO memVO = new MemVO();
		memVO.setMem_id(mem_id);
		memVO.setMem_name(mem_name);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_address(mem_address);
		dao.update(memVO);
		return memVO;
	}
	
	public MemVO updatePwd(Integer mem_id, String mem_password) {

		MemVO memVO = new MemVO();
		memVO.setMem_id(mem_id);
		memVO.setMem_password(mem_password);
		dao.updatePWD(memVO);
		return memVO;
	}
	
	public MemVO resetPwd(String mem_email, String newpwd) {

		MemVO memVO = new MemVO();
		memVO.setMem_email(mem_email);
		memVO.setMem_password(newpwd);
		dao.resetPWD(memVO);
		return memVO;
	}

	public MemVO updateMemStatus(String mem_email, Byte mem_status) {

		MemVO memVO = new MemVO();
		memVO.setMem_email(mem_email);
		memVO.setMem_status(mem_status);
		dao.updateStatus(memVO);
		return memVO;
	}
	
	public MemVO updateMemAllData(Integer mem_id, String mem_name, String mem_phone, String mem_address, Byte mem_status) {

		MemVO memVO = new MemVO();
		memVO.setMem_id(mem_id);
		memVO.setMem_name(mem_name);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_address(mem_address);
		memVO.setMem_status(mem_status);
		dao.updateAll(memVO);
		return memVO;
	}

	public MemVO getOneMem(Integer mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}

	public MemVO logIn(String mem_email, String mem_password) {
		return dao.selectForLogin(mem_email, mem_password);
	}
	
	public MemVO checkPwd(Integer mem_id, String mem_password) {
		return dao.selectForPwd(mem_id, mem_password);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}

}
