package com.roles.model;

import java.util.List;

public interface RolesDAO_interface {
	public void insert(RolesVO rolesVO);
    public void update(RolesVO rolesVO);
    public RolesVO findByPrimaryKey(Integer roles_id);
    public List<RolesVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
