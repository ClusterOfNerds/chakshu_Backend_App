package com.chakshu.backend.entity;


import com.chakshu.backend.enums.SecurityRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
/*
* CREATE TABLE `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  `mobile` VARCHAR(255)NULL,
  `active` TINYINT NOT NULL,
  `created_at` DATETIME DEFAULT NOW(),
  `modified_at` DATETIME DEFAULT NOW(),
  PRIMARY KEY (`user_id`)
);
* */

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public User(String firstName, String lastName,
                String email,String password,
                String role,String mobile,
                boolean active, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.mobile = mobile;
        this.active = active;
        this.address = address;
    }

    private String mobile;
    private boolean active;
    @JoinColumn(name = "fk_address_id")
    @ManyToOne
    private Address address;



    public Set<SecurityRole> getRoles() {
        return role != null ? new HashSet<>(Arrays.stream(role.split(","))
                .map(SecurityRole::valueOf)
                .toList()) : new HashSet<>();
    }

    public void setRoles(Set<SecurityRole> roles) {
        this.role = roles != null ? String.join(",", roles.stream().map(Enum::name).toArray(String[]::new)) : null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Arrays.asList(role.split(","))
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
