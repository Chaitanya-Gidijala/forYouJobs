package com.foryou.jobs.dao;

import com.foryou.jobs.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
