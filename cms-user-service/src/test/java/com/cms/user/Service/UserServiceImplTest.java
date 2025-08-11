package com.cms.user.Service;

import com.cms.user.dao.UserDao;

import com.cms.user.entity.User;
import com.cms.user.service.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetUserById_WhenUserExists() {
        User user = new User();
        user.setId(1);
        user.setEmailId("john@example.com");

        when(userDao.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("john@example.com", result.getEmailId());

        verify(userDao, times(1)).findById(1);
    }

    @Test
    void testGetUserById_WhenUserNotFound() {
        when(userDao.findById(99)).thenReturn(Optional.empty());

        User result = userService.getUserById(99);

        assertNull(result);
        verify(userDao, times(1)).findById(99);
    }
   
}
