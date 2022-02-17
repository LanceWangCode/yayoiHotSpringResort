package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
	public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void updateAll(MemVO memVO);
    public void updatePic(MemVO memVO);
    public void updatePWD(MemVO memVO);
    public void resetPWD(MemVO memVO);
    public void updateStatus(MemVO memVO);
    public MemVO findByPrimaryKey(Integer mem_id);
    public MemVO selectByEmail(String mem_email);
    public MemVO selectForLogin(String mem_email, String mem_password);
    public MemVO selectForPwd(Integer mem_id, String mem_password);
    public List<MemVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
	
}
