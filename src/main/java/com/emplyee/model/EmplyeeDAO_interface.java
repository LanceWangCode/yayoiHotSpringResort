package com.emplyee.model;

import java.util.List;

public interface EmplyeeDAO_interface {
	public void insert(EmplyeeVO emplyeeVO);
	public void update(EmplyeeVO emplyeeVO);
	public void updateStatus(EmplyeeVO emplyeeVO);
	public void delete(Integer empno);
	public EmplyeeVO findByPrimaryKey(Integer empno);
	public List<EmplyeeVO> getAll();

}
