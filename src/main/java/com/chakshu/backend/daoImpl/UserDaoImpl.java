package com.chakshu.backend.daoImpl;

import com.chakshu.backend.dao.UserDao;
import com.chakshu.backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public User findUserByUserName(String email) {
        Query query = entityManager.createQuery("Select user from User user where email=:email");
        query.setParameter("email",email);
        List<User> user = query.getResultList();
        if(user.isEmpty()) {
            return null;
        }
        return user.get(0);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }


}
