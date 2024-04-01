package com.foryou.jobs.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.foryou.jobs.entity.User;
import com.foryou.jobs.user.WebUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);

}
