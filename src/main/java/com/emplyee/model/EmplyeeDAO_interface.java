package com.emplyee.model;

import java.util.List;

import com.mem.model.MemVO;

public interface EmplyeeDAO_interface {
	public void insert(EmplyeeVO emplyeeVO);
	public void update(EmplyeeVO emplyeeVO);
	public void updateStatus(EmplyeeVO emplyeeVO);
	public void delete(Integer empno);
	public EmplyeeVO findByPrimaryKey(Integer empno);
	public EmplyeeVO selectForLogin(Integer empno, String emp_password);
	public List<EmplyeeVO> getAll();

}
