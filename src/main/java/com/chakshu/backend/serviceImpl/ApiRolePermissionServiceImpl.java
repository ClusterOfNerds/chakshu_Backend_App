package com.chakshu.backend.serviceImpl;

import com.chakshu.backend.dao.ApiRolePermissionDao;
import com.chakshu.backend.entity.ApiRolePermission;
import com.chakshu.backend.service.ApiRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
