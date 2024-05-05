package com.foryou.jobs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foryou.jobs.entity.SubscribedUser;
import com.foryou.jobs.repository.SubscribedUserRepository;

@Service
public class SubscribedUserService {
	@Autowired
	private SubscribedUserRepository subscribedUserRepository;

	public List<SubscribedUser> getAllSubscribedUsers() {
		return subscribedUserRepository.findAll();
	}

	public void saveSubscribedUser(SubscribedUser user) {
		subscribedUserRepository.save(user);
	}
}