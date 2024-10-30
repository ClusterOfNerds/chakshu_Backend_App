package com.chakshu.backend.daoImpl;

import com.chakshu.backend.dao.ApiRolePermissionDao;
import com.chakshu.backend.entity.ApiRolePermission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

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

    @Override
    public ApiRolePermission getRoleBasedPermissionById(Integer apiRolePermissionId) {
        ApiRolePermission apiRolePermission = entityManager.find(ApiRolePermission.class,apiRolePermissionId);
        return apiRolePermission;
    }

    @Override
    public void saveApiWithRole(ApiRolePermission apiRolePermission) {
        entityManager.persist(apiRolePermission);
    }

    @Override
    public ApiRolePermission editApiWithRole(ApiRolePermission apiRolePermission) {
        if(Objects.isNull(apiRolePermission.getApiRolePermissionId())) {
            entityManager.persist(apiRolePermission);
        } else {
           return entityManager.merge(apiRolePermission);
        }
        return apiRolePermission;
    }

    @Override
    public String deleteApiWithRole(Integer apiRolePermissionId) {
        StringBuilder message = new StringBuilder();
        ApiRolePermission apiRolePermission = getRoleBasedPermissionById(apiRolePermissionId);
        if(Objects.nonNull(apiRolePermission)) {
            apiRolePermission.setDeleted(true);
            entityManager.merge(apiRolePermission);
        }
        else
            return message.append( "Api Record is not valid with Id :: ").append(apiRolePermissionId).toString();
        return message.append("Successfully deleted entry").toString();
    }
}
