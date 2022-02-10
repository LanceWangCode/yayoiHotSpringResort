package com.functionAccessRight.model;

import java.util.List;

public interface FunctionAccessRightDAO_interface {

	public void insert(FunctionAccessRightVO functionAccessRightVO);
    public void update(FunctionAccessRightVO functionAccessRightVO);
    public void delete(Integer function_id);
    public FunctionAccessRightVO findByPrimaryKey(Integer function_id);
    public List<FunctionAccessRightVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
