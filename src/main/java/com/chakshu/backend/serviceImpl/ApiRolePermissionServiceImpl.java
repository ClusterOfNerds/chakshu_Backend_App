package com.chakshu.backend.serviceImpl;

import com.chakshu.backend.dao.ApiRolePermissionDao;
import com.chakshu.backend.entity.ApiRolePermission;
import com.chakshu.backend.service.ApiRolePermissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ApiRolePermissionServiceImpl implements ApiRolePermissionService {
    private ApiRolePermissionDao apiRolePermissionDao;

    @Autowired
    public ApiRolePermissionServiceImpl(ApiRolePermissionDao apiRolePermissionDao) {
        this.apiRolePermissionDao = apiRolePermissionDao;
    }

    @Override
    public List<ApiRolePermission> getAllRoleBasedPermission() {
        return apiRolePermissionDao.getAllRoleBasedPermission();
    }

    @Override
    public void saveApiWithRole(ApiRolePermission apiRolePermission) {
         apiRolePermissionDao.saveApiWithRole(apiRolePermission);
    }

    public ApiRolePermission editApiWithRole(ApiRolePermission apiRolePermission) {
        return apiRolePermissionDao.editApiWithRole(apiRolePermission);
    }

    @Override
    public String deleteApiWithRole(Integer apiRolePermission) {
        return apiRolePermissionDao.deleteApiWithRole(apiRolePermission);
    }

    @Override
    public ApiRolePermission getRoleBasedPermissionById(Integer apiRolePermissionId) {
        return apiRolePermissionDao.getRoleBasedPermissionById(apiRolePermissionId);
    }

}
