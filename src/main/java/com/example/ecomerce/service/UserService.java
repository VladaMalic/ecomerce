package com.example.ecomerce.service;

import com.example.ecomerce.dto.MeResponse;
import com.example.ecomerce.dto.UserDTO;
import com.example.ecomerce.model.User;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;


@Service

public class UserService implements UserDetailsService {

    private final UserRepository userRep;

    public UserService(UserRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    public void createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        user.setDate(LocalDateTime.now());
        userRep.save(user);
    }

    public MeResponse getCurrentUser(String username) {
        User user = userRep.findUserByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + username);
        }
        return new MeResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public User getUserById(Long id) {
        return userRep.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + id));
    }

    public void deleteUser(Long id) {
        userRep.delete(getUserById(id));
    }

    public User updateUser(Long id, String email){
        User curentUser = getUserById(id);
        curentUser.setEmail(email);
        return userRep.save(curentUser);
    }
}
