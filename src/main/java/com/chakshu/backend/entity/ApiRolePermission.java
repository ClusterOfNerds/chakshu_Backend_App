package com.chakshu.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_role_permission", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"api", "requestType"}) // Ensure unique combination
})
public class ApiRolePermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer apiRolePermissionId;
    private String api;
    private String requestType;
    private String rolesPermitted;
    private boolean isDeleted;

}
