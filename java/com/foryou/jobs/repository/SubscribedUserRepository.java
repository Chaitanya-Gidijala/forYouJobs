package com.foryou.jobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foryou.jobs.entity.SubscribedUser;

@Repository
public interface SubscribedUserRepository extends JpaRepository<SubscribedUser, Long> {
    // Custom queries if needed
}