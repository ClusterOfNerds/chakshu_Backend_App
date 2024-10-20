package com.chakshu.backend.dao;


import com.chakshu.backend.entity.User;

public interface UserDao {

    public User findUserByUserName(String userName);

    public void saveUser(User user);

}
