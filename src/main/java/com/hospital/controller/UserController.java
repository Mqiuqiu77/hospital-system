package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.dto.LoginDTO;
import com.hospital.dto.LoginResponse;
import com.hospital.dto.UserInfoResponse;
import com.hospital.entity.User;
import com.hospital.service.UserService1;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService1 userService;
    private final HttpSessionSecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public UserController(UserService1 userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO,
                                       HttpServletRequest request, HttpServletResponse response) {
        userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        request.getSession(true);
        request.changeSessionId();
        var authentication = UsernamePasswordAuthenticationToken.authenticated(
                loginDTO.getUsername().trim(), null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return Result.success(new LoginResponse(authentication.getName(), authentication.getName()));
    }

    @GetMapping("/me")
    public Result<LoginResponse> me(java.security.Principal principal) {
        return Result.success(new LoginResponse(principal.getName(), principal.getName()));
    }

    @GetMapping("/info")
    public Result<UserInfoResponse> getInfo(@RequestParam Integer id, @RequestParam String name) {
        UserInfoResponse result = new UserInfoResponse(id, name);
        return Result.success(result);
    }

    @GetMapping("/detail")
    public User userdetail() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Tom");
        return user;
    }

    @GetMapping("/test1")
    public String test(){
        return "Hello Spring";
    }
}
