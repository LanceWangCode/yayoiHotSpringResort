package com.qalist.model;

import java.util.List;

public class QAListService {
	private QAListDAO_interface dao;

	public  QAListService() {
		dao = new QAListDAO();
	}

	public QAListVO addQA(Integer no,  String question, String answer,
			Boolean status) {
		QAListVO qaListVO = new QAListVO();

		qaListVO.setNo(no);
		qaListVO.setQuestion(question);
		qaListVO.setAnswer(answer);
		qaListVO.setStatus(status);
		dao.insert(qaListVO);

		return qaListVO;
	}

	public QAListVO updateQA(Integer qa_id, Integer no,  String question, String answer,
			Boolean status) {

		QAListVO qaListVO = new QAListVO();
		
		qaListVO.setQa_id(qa_id);
		qaListVO.setNo(no);
		qaListVO.setQuestion(question);
		qaListVO.setAnswer(answer);
		qaListVO.setStatus(status);
		
		dao.update(qaListVO);

		return qaListVO;
	}

	public void deleteRoom(Integer qa_id) {
		dao.delete(qa_id);
	}

	public QAListVO getOneRoom(Integer qa_id) {
		return dao.findByPK(qa_id);
	}

	public List<QAListVO> getAll() {
		return dao.getAll();
	}
}
