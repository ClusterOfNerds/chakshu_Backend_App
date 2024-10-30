package com.chakshu.backend.service;

import com.chakshu.backend.entity.ApiRolePermission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApiRolePermissionService {

    public List<ApiRolePermission> getAllRoleBasedPermission();
    public void saveApiWithRole(ApiRolePermission apiRolePermission);
    public ApiRolePermission editApiWithRole(ApiRolePermission apiRolePermission);
    public String deleteApiWithRole(Integer apiRolePermissionId);
    public ApiRolePermission getRoleBasedPermissionById(Integer apiRolePermissionId);

}
