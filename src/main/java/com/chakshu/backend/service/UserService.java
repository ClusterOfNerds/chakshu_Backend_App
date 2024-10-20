package com.chakshu.backend.service;

import com.chakshu.backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    public void saveUser(User user);

}
