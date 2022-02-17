package com.emplyee.model;

import java.util.List;

public class EmplyeeService {
	private EmplyeeDAO_interface dao;

	public EmplyeeService() {
		dao = new EmplyeeJDBCDAO();
	}

	public EmplyeeVO addEmplyee(Integer empno, Integer roles_id, String emp_name, String emp_password, Boolean emp_status) {

		EmplyeeVO emplyeeVO = new EmplyeeVO();
		emplyeeVO.setEmpno(empno);
		emplyeeVO.setRoles_id(roles_id);
		emplyeeVO.setEmp_name(emp_name);
		emplyeeVO.setEmp_password(emp_password);
		emplyeeVO.setEmp_status(emp_status);
		dao.insert(emplyeeVO);
		return emplyeeVO;
	}
	
	public boolean checkEmp(Integer empno) {

		if (dao.findByPrimaryKey(empno) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public EmplyeeVO updateEmp(Integer empno, Integer roles_id, String emp_name, String emp_password, Boolean emp_status) {

		EmplyeeVO emplyeeVO = new EmplyeeVO();
		emplyeeVO.setEmpno(empno);
		emplyeeVO.setRoles_id(roles_id);
		emplyeeVO.setEmp_name(emp_name);
		emplyeeVO.setEmp_password(emp_password);
		emplyeeVO.setEmp_status(emp_status);
		dao.update(emplyeeVO);
		return emplyeeVO;
	}
	
	public EmplyeeVO getOneMem(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public EmplyeeVO logIn(Integer empno, String emp_password) {
		return dao.selectForLogin(empno, emp_password);
	}
	
	public List<EmplyeeVO> getAll() {
		return dao.getAll();
	}
}
