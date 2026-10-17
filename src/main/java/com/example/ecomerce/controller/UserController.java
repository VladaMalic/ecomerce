package com.example.ecomerce.controller;

import com.example.ecomerce.dto.UserDTO;
import com.example.ecomerce.model.User;
import com.example.ecomerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users", description = "Managing users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public User getUserAPI(@PathVariable(name = "id") Long id){
        return service.getUserById(id);
    }

    @PostMapping("/user/add")
    @Operation(summary = "Creates a new user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "The new user was created")
    @ApiResponse(responseCode = "400", description = "The name is empty")
    @ApiResponse(responseCode = "401", description = "Not authenticated")
    @ApiResponse(responseCode = "409", description = "User already exists")
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
    @Operation(summary = "Deletes a user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "The user was deleted")
    @ApiResponse(responseCode = "404", description = "The user does not exist")
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
