package com.chakshu.backend.controller;

import com.chakshu.backend.entity.ApiRolePermission;
import com.chakshu.backend.exceptionClass.InvalidDataException;
import com.chakshu.backend.exceptionClass.RoleBasedException;
import com.chakshu.backend.service.ApiRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/apiInfo")
public class ApiRolePermissionController {

    private ApiRolePermissionService apiRolePermissionService;

    @Autowired
    public ApiRolePermissionController(ApiRolePermissionService apiRolePermissionService){
        this.apiRolePermissionService = apiRolePermissionService;
    }

    @GetMapping("/rolesBasedPermission")
    public ResponseEntity<?> getAllRoleBasedPermission(){
        try {
            List<ApiRolePermission> apiRolePermissionList = apiRolePermissionService.getAllRoleBasedPermission();
            if (Objects.isNull(apiRolePermissionList) ||  apiRolePermissionList.isEmpty()) {
                throw new RoleBasedException("Urls are not present for granting permission", null);
            }
            return ResponseEntity.ok(apiRolePermissionList);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Some Error occured while getting Api Roles ",e);
        }
    }

    @PostMapping("/rolesBasedPermission")
    public ResponseEntity<?> saveRoleBasedPermissionForUrl(@RequestBody ApiRolePermission rolePermission) {
        try {
            System.out.println(rolePermission.getRolesPermitted());
            apiRolePermissionService.saveApiWithRole(rolePermission);
            return ResponseEntity.status(HttpStatus.CREATED).body("Url created with the given role");
        } catch (Exception e) {
            throw new RuntimeException("Some error occured while save api role",e);
        }
    }

    @PutMapping("/rolesBasedPermission")
    public ResponseEntity<?> editRoleBasedPermissionForUrl(@RequestBody ApiRolePermission rolePermission) {
        try {
            ApiRolePermission apiRolePermission = apiRolePermissionService.editApiWithRole(rolePermission);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiRolePermission);
        } catch (Exception e) {
            throw new RuntimeException("Some error occured while save api role",e);
        }
    }

    @PutMapping("/rolesBasedPermission/delete")
    public ResponseEntity<?> deleteRoleBasedPermissionForUrl(@RequestParam Integer rolePermissionId) {
        try {
            if(Objects.isNull(rolePermissionId) || rolePermissionId <= 0) {
                throw new InvalidDataException("Data provided for deletion is not valid",null);
            }
            String message = apiRolePermissionService.deleteApiWithRole(rolePermissionId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("message");
        } catch (Exception e) {
            throw new RuntimeException("Some error occured while save api role",e);
        }
    }

}
