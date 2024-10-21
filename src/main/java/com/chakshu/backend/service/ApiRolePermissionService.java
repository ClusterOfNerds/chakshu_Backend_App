package com.chakshu.backend.service;

import com.chakshu.backend.entity.ApiRolePermission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApiRolePermissionService {

    public List<ApiRolePermission> getAllRoleBasedPermission();

}
