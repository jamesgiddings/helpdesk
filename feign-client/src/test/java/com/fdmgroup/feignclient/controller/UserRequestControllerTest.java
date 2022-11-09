package com.fdmgroup.feignclient.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.feignclient.request.UserRequestService;

@ExtendWith(MockitoExtension.class)
public class UserRequestControllerTest {

    @InjectMocks
    private UserRequestController userRequestController;

    @Mock
    UserRequestService userRequestService;

    Object user1, user2;

    List<Object> users;

    @BeforeEach
    public void setUp() {
        user1 = new Object();
        user2 = new Object();
        users = List.of(user1, user2);
    }

    @Test
    void testDeleteUserById() {
        // given
        Long id = 1L;
        // when
        userRequestController.deleteUserById(id);
        // then
        verify(userRequestService, times(1)).deleteUserById(id);
    }

    @Test
    void testFindAllUsers() {
        // given
        given(userRequestService.findAllUsers()).willReturn(users);
        // when
        assertThat(userRequestController.findAllUsers().getBody()).isEqualTo(users);
        // then
        verify(userRequestService, times(1)).findAllUsers();
    }

    @Test
    void testFindUserById() {
        // given
        given(userRequestService.findUserById(1)).willReturn(user1);
        // when
        userRequestController.findUserById(1);
        // then
        verify(userRequestService, times(1)).findUserById(1);
    }

    @Test
    void testSaveAdmin() {
        // given
        Object admin = new Object();
        // when
        userRequestController.saveAdmin(admin);
        // then
        verify(userRequestService, times(1)).saveAdmin(admin);
    }

    @Test
    void testSaveClient() {
        // given
        Object client = new Object();
        // when
        userRequestController.saveAdmin(client);
        // then
        verify(userRequestService, times(1)).saveAdmin(client);
    }

    @Test
    void testSaveEngineer() {
        // given
        Object engineer = new Object();
        // when
        userRequestController.saveAdmin(engineer);
        // then
        verify(userRequestService, times(1)).saveAdmin(engineer);

    }
}
