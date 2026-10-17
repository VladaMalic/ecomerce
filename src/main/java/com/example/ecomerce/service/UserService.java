package com.example.ecomerce.service;

import com.example.ecomerce.model.User;
import com.example.ecomerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRep;

    public UserService(UserRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Objects.requireNonNull(
                userRep.findUserByUsername(username), "Username does not exists: " + username
        );
    }

    public void createUser(User user){
        userRep.save(user);
    }

    public User getUserById(Long id){
        return userRep.findById(id).orElseThrow();
    }

    public void deleteUser(Long id){
        userRep.deleteById(id);
    }

    public User updateUser(Long id, String email){
        User curentUser = getUserById(id);
        curentUser.setEmail(email);
        return userRep.save(curentUser);
    }
}
