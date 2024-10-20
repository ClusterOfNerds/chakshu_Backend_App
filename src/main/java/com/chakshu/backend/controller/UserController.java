package com.chakshu.backend.controller;


import com.chakshu.backend.entity.User;
import com.chakshu.backend.enums.SecurityRole;
import com.chakshu.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> login(@AuthenticationPrincipal OAuth2User principal, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String first_name = principal.getAttribute("given_name");
        String last_name = principal.getAttribute("family_name");
        String email = principal.getAttribute("email");

        User user = (User) userService.loadUserByUsername(email);
        if(user == null) {
            user = User.builder()
                    .firstName(first_name)
                    .lastName(last_name)
                    .email(email)
                    .role(SecurityRole.USER.name())
                    .active(true)
                    .build();
            userService.saveUser(user);
        }
        return ResponseEntity.ok(user);
    }

}
