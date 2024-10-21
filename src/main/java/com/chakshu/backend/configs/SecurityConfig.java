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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                .sessionManagement((session) -> session
                         .sessionFixation((sessionFixation) -> sessionFixation
                                 .newSession()
                         )
                         .maximumSessions(1)
                         .expiredUrl("/Error")

                )
                .authorizeHttpRequests(auth -> {
                    List<ApiRolePermission> rolePermissions = fetchRoleBasedPermission();
                    for(ApiRolePermission apiRolePermission : rolePermissions) {
                        String role = apiRolePermission.getRolesPermitted();
                        String api  = apiRolePermission.getApi();
                        Arrays.asList(role.split("\\.")).stream().forEach(r ->
                            auth.requestMatchers(api).hasRole(r)
                        );

                    }
                    auth.anyRequest().authenticated();
                }
                )
                .oauth2Login(oauth -> oauth.defaultSuccessUrl("/getUser",true))
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
