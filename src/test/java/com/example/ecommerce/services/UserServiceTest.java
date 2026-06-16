package com.example.ecommerce.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ecommerce.entities.User;
import com.example.ecommerce.repositories.UserRepository;
import com.example.ecommerce.services.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(1L, "Rifki", "rifki@example.com", "08123456789", "password123");
    }

    @Test
    void testFindAll_ReturnsUserList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(testUser));
        
        List<User> result = userService.findAll();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Rifki", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindById_WhenUserExists_ReturnsUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        
        User result = userService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Rifki", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_WhenUserDoesNotExist_ThrowsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findById(99L);
        });
        verify(userRepository, times(1)).findById(99L);
    }

    @Test
    void testInsert_ReturnsSavedUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        
        User result = userService.insert(testUser);
        
        assertNotNull(result);
        assertEquals("Rifki", result.getName());
        verify(userRepository, times(1)).save(testUser);
    }
}
