package com.chakshu.backend.dao;

import com.chakshu.backend.entity.ApiRolePermission;

import java.util.List;

    public interface ApiRolePermissionDao {
    public List<ApiRolePermission> getAllRoleBasedPermission();
    public void saveApiWithRole(ApiRolePermission apiRolePermission);

    public ApiRolePermission editApiWithRole(ApiRolePermission apiRolePermission);

    public String deleteApiWithRole(Integer apiRolePermissionId);

    public ApiRolePermission getRoleBasedPermissionById(Integer apiRolePermissionId);
}
