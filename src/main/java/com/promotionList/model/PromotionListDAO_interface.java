package com.promotionList.model;

import java.util.List;

public interface PromotionListDAO_interface {
	public void insert(PromotionListVO promotionListVO);
	public void update(PromotionListVO promotionListVO);
    public void updateDescription(PromotionListVO promotionListVO);
    public void updateStatus(PromotionListVO promotionListVO);
    public void delete(Integer promotion_id);
    public PromotionListVO findByPrimaryKey(Integer promotion_id);
    public List<PromotionListVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
