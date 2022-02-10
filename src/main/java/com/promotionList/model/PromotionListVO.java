package com.promotionList.model;

import java.io.Serializable;
import java.sql.Date;

public class PromotionListVO implements Serializable{
	private Integer promotion_id;
	private String promotion_title;
	private String promotion_description;
	private Date promotion_date;
	private Boolean promotion_status;
	
	public Integer getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(Integer promotion_id) {
		this.promotion_id = promotion_id;
	}
	public String getPromotion_title() {
		return promotion_title;
	}
	public void setPromotion_title(String promotion_title) {
		this.promotion_title = promotion_title;
	}
	public String getPromotion_description() {
		return promotion_description;
	}
	public void setPromotion_description(String promotion_description) {
		this.promotion_description = promotion_description;
	}
	public Date getPromotion_date() {
		return promotion_date;
	}
	public void setPromotion_date(Date promotion_date) {
		this.promotion_date = promotion_date;
	}
	public Boolean getPromotion_status() {
		return promotion_status;
	}
	public void setPromotion_status(Boolean promotion_status) {
		this.promotion_status = promotion_status;
	}
	
}
