package com.roles.model;

import java.util.List;

public class RolesService {
	
	private RolesDAO_interface dao;

	public RolesService() {
		dao = new RolesJDBCDAO();
	}

	public List<RolesVO> getAll() {
		return dao.getAll();
	}
	
	public RolesVO getOneRole(Integer roles_id) {
		return dao.findByPrimaryKey(roles_id);
	}

}
