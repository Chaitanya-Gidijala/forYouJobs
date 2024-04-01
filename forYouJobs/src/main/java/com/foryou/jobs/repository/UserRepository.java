package com.foryou.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foryou.jobs.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);
}
