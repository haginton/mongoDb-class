package com.ada.mongodb.adaMongoDBClass.controller;

import com.ada.mongodb.adaMongoDBClass.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ada.mongodb.adaMongoDBClass.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable("idUser") String idUser) {
        return new ResponseEntity<>(userService.findUserById(idUser), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }


    @PutMapping("/{idUser}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("idUser") String idUser, @RequestBody UserRequestDto user) {
        return new ResponseEntity<>(userService.updateUser(idUser, user), HttpStatus.OK);
    }


    @DeleteMapping("/{idUser}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("idUser") String idUser) {
        return new ResponseEntity<>(userService.deleteUser(idUser), HttpStatus.OK);
    }
}