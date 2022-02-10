package com.qalist.model;

import java.io.Serializable;

public class QAListVO implements Serializable{
	private Integer qa_id;
	private Integer no;
	private String question;
	private String answer;
	private Integer status;
	
	
	public Integer getQa_id() {
		return qa_id;
	}
	public void setQa_id(Integer qa_id) {
		this.qa_id = qa_id;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
