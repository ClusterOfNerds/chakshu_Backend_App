package com.chakshu.backend.controller;

import com.chakshu.backend.entity.ApiRolePermission;
import com.chakshu.backend.service.ApiRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiInfo")
public class ApiRolePermissionController {

    private ApiRolePermissionService apiRolePermissionService;

    @Autowired
    public ApiRolePermissionController(ApiRolePermissionService apiRolePermissionService){
        this.apiRolePermissionService = apiRolePermissionService;
    }

    @GetMapping("/getRolesBasedPermission")
    public ResponseEntity<?> getAllRoleBasedPermission(){

        List<ApiRolePermission> apiRolePermissionList = apiRolePermissionService.getAllRoleBasedPermission();

        return ResponseEntity.ok(apiRolePermissionList);
    }

}
