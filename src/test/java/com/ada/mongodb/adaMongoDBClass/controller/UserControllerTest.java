package com.ada.mongodb.adaMongoDBClass.controller;

import com.ada.mongodb.adaMongoDBClass.dto.UserResponseDto;
import com.ada.mongodb.adaMongoDBClass.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void get_all_users_from_not_empty_list_test(){
        //organizar
        List<UserResponseDto> usersMock = new ArrayList<>();
        usersMock.add(new UserResponseDto("1a", "juanito", "juanito@mail.com", LocalDateTime.now()));
        usersMock.add(new UserResponseDto("1b", "maria", "maria@mail.com", LocalDateTime.now()));
        Mockito.when(userService.getAllUsers()).thenReturn(usersMock);
        //actuar
        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        //afirmar

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, response.getBody().size());
    }

    @Test
    public void get_all_users_from_empty_list_test(){
        //organizar
        Mockito.when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        //actuar
        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        //afirmar

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void get_all_users_server_error_test(){
        //organizar
        Mockito.when(userService.getAllUsers()).thenThrow(new RuntimeException("An error has occurred during the execution"));
        //actuar
        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        //afirmar

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}