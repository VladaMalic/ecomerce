package com.example.ecomerce.controller;

import com.example.ecomerce.dto.UserDTO;
import com.example.ecomerce.model.User;
import com.example.ecomerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/user/{id}")
    public User getUserAPI(@PathVariable(name = "id") Long id){
        return service.getUserById(id);
    }

    @PostMapping("/user/add")
    public void createUser(@RequestBody UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        user.setDate(LocalDateTime.now());
        service.createUser(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUserAPI(@PathVariable(name = "id") Long id){
        service.deleteUser(id);
    }

    @PostMapping("/user/update/{id}/{email}")
    public void updateUser(@PathVariable(name = "id") Long id, @PathVariable(name = "email") String email) {
        service.updateUser(id, email);
    }

    @GetMapping("/user/me")
    public void getInfo(Principal principal, HttpServletResponse response) throws IOException {
        System.out.println(principal);
        response.sendRedirect("/");
    }

}
