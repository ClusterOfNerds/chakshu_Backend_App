package com.chakshu.backend.serviceImpl;

import com.chakshu.backend.dao.UserDao;
import com.chakshu.backend.entity.User;
import com.chakshu.backend.enums.SecurityRole;
import com.chakshu.backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUserName(username);
        if(user == null) {
            return null;
        }
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String roles) {
        return Arrays.asList(roles.split(","))
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
