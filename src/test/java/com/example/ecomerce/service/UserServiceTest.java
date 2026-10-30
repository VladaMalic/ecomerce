package com.example.ecomerce.service;

import com.example.ecomerce.dto.MeResponse;
import com.example.ecomerce.dto.UserDTO;
import com.example.ecomerce.model.User;
import com.example.ecomerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getCurrentUserReturnsTypedProfileWithoutPassword() {
        User user = new User();
        user.setId(42L);
        user.setUsername("alice");
        user.setEmail("alice@example.com");
        user.setPassword("secret");
        user.setRole("USER");
        when(userRepository.findUserByUsername("alice")).thenReturn(user);

        MeResponse response = userService.getCurrentUser("alice");

        assertEquals(new MeResponse(42L, "alice", "alice@example.com", "USER"), response);
    }

    @Test
    void getCurrentUserReturnsNotFoundWhenUserDoesNotExist() {
        when(userRepository.findUserByUsername("missing")).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> userService.getCurrentUser("missing"));
    }

    @Test
    void createUserAppliesDefaultRoleAndCreationDate() {
        UserDTO dto = new UserDTO("alice@example.com", "secret", "alice");
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        userService.createUser(dto);

        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals("alice", savedUser.getUsername());
        assertEquals("alice@example.com", savedUser.getEmail());
        assertEquals("secret", savedUser.getPassword());
        assertEquals("USER", savedUser.getRole());
        assertNotNull(savedUser.getDate());
    }
}
