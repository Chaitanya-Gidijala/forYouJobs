package com.foryou.jobs.dao;

import com.foryou.jobs.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
