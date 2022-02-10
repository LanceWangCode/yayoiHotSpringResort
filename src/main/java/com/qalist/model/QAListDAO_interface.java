package com.qalist.model;

import java.util.List;

public interface QAListDAO_interface {
	
	public void insert(QAListVO qwListVO);
	public void update(QAListVO qwListVO);
	public void delete(Integer qa_id);
	public QAListVO findByPK(Integer qa_id);
	public List<QAListVO> getAll();
}
