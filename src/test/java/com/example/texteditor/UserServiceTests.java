package com.example.texteditor;

import com.example.texteditor.server.model.User;
import com.example.texteditor.server.repository.UserRepository;
import com.example.texteditor.server.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Captor
    private ArgumentCaptor<User> captor;

    private User user1, user2, user3;
    @BeforeEach
    void init() {
        user1 = new User();
        user1.setId(1L);
        user1.setName("user1");
        user1.setPassword("password");

        user2 = new User();
        user2.setId(2L);
        user2.setName("user2");
        user2.setPassword("password");

        user3 = new User();
        user3.setId(3L);
        user3.setName("user3");
        user3.setPassword("password");
    }
    @Test
    void getUserByUsername() {
        Mockito.when(userRepository.findByName("user2")).thenReturn(user2);
        assertEquals(user2, userRepository.findByName("user2"));
    }
    @Test
    void create() {
        userService.create("user4", "password");
        Mockito.verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        assertEquals("user4", captured.getUsername());
    }
    @Test
    void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2, user3));
        assertEquals(List.of(user1, user2, user3), userRepository.findAll());
    }
    @Test
    void deleteUser() {
        userService.delete("user1");
        Mockito.verify(userRepository).deleteByName("user1");
    }
}
