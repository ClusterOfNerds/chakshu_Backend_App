package com.chakshu.backend.dao;

import com.chakshu.backend.entity.ApiRolePermission;

import java.util.List;

    public interface ApiRolePermissionDao {
    public List<ApiRolePermission> getAllRoleBasedPermission();
}
