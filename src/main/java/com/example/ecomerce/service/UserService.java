package com.example.ecomerce.service;

import com.example.ecomerce.model.User;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


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

    public void createUser(User user){
        userRep.save(user);
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
