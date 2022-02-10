package com.rolesAccessRight.model;

import java.util.List;

public interface RolesAccessRightDAO_interface {
	public void insert(RolesAccessRightVO rolesAccessRightVO);
    public void update(RolesAccessRightVO rolesAccessRightVO);
    public void delete(Integer roles_id, Integer fuction_id);
    public RolesAccessRightVO findByPrimaryKey(Integer roles_id, Integer fuction_id);
    public List<RolesAccessRightVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
