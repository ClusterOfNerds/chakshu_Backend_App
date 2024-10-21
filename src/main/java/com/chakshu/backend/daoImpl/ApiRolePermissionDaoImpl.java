package com.chakshu.backend.daoImpl;

import com.chakshu.backend.dao.ApiRolePermissionDao;
import com.chakshu.backend.entity.ApiRolePermission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApiRolePermissionDaoImpl implements ApiRolePermissionDao {

    private EntityManager entityManager;

    @Autowired
    public ApiRolePermissionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ApiRolePermission> getAllRoleBasedPermission() {
        Query query = entityManager.createQuery("Select arp from ApiRolePermission arp where isDeleted=false");
        List<ApiRolePermission> apiRolePermissionList = query.getResultList();
        return apiRolePermissionList;
    }
}
