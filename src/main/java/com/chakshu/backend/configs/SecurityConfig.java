package com.chakshu.backend.configs;

import com.chakshu.backend.entity.ApiRolePermission;
import com.chakshu.backend.service.ApiRolePermissionService;
import com.chakshu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private ApiRolePermissionService apiRolePermissionService;

    @Autowired
    public SecurityConfig (ApiRolePermissionService apiRolePermissionservice) {
        this.apiRolePermissionService = apiRolePermissionservice;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors // Enable CORS with settings from WebMvcConfigurer
                        .configurationSource(request -> {
                            var corss = new CorsConfiguration();
                            corss.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // Allowed origin
                            corss.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed methods
                            corss.setAllowedHeaders(Collections.singletonList("*")); // Allowed headers
                            corss.setAllowCredentials(true); // Allow credentials
                            return corss;
                        })
                )
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    List<ApiRolePermission> rolePermissions = fetchRoleBasedPermission();
                    for(ApiRolePermission apiRolePermission : rolePermissions) {
                        if(Objects.isNull(apiRolePermission.getRolesPermitted())) {
                            apiRolePermission.setRolesPermitted("DEVELOPER");
                        }
                        String[] roles = apiRolePermission.getRolesPermitted().split("\\.");
                        String api  = apiRolePermission.getApi();
                        for (String role : roles) {
                            auth.requestMatchers(api).hasRole(role);
                        }

                    }
                    auth.anyRequest().authenticated();
                }
                )
                .oauth2Login(oauth -> oauth.defaultSuccessUrl("http://localhost:3000/mainPage",true))
                .httpBasic(hb -> hb.disable())
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                )
                .build();
    }

    public WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply CORS settings to all paths
                        .allowedOrigins("http://localhost:3000") // Allow requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager theUserDetailManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailManager.setUsersByUsernameQuery("Select user_id,email from user where email=?");
        theUserDetailManager.setAuthoritiesByUsernameQuery("Select email,role from user where email=?");

        return theUserDetailManager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;

    }

    private List<ApiRolePermission> fetchRoleBasedPermission() {
        return apiRolePermissionService.getAllRoleBasedPermission();
    }

}
