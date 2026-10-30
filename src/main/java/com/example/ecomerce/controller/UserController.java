package com.example.ecomerce.controller;

import com.example.ecomerce.dto.MeResponse;
import com.example.ecomerce.dto.UserDTO;
import com.example.ecomerce.model.User;
import com.example.ecomerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController

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
    @Operation(summary = "Creates a new user", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "The new user was created")
    @ApiResponse(responseCode = "401", description = "Not authenticated")
    public void createUser(@RequestBody UserDTO dto){
        service.createUser(dto);
    }

    @DeleteMapping("/user/delete/{id}")
    @Operation(summary = "Deletes a user", security = @SecurityRequirement(name = "basicAuth"))
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

    @GetMapping("/api/auth/me")
    @Operation(summary = "Returns the authenticated user's profile",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "Profile returned successfully")
    @ApiResponse(responseCode = "401", description = "Not authenticated")
    public MeResponse getCurrentUser(Principal principal) {
        return service.getCurrentUser(principal.getName());
    }

}
