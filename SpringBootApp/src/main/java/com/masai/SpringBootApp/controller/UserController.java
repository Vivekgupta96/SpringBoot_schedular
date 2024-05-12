package com.masai.SpringBootApp.controller;

import com.masai.SpringBootApp.Service.UserService;
import com.masai.SpringBootApp.model.User;
import com.masai.SpringBootApp.modelDto.userDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(
            @Valid @RequestBody userDto user) {

        User addedUser = userService.addUser(user);
        return ResponseEntity.ok(addedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") Integer userId) {
        User user = userService.getUserDetails(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
       return new ResponseEntity<>("User deleted Succesfully ",HttpStatus.OK);
    }

    @GetMapping("/get-all-user")
    public ResponseEntity<Page<?>> getAllUserDetails(
            @RequestParam(required = false, defaultValue = "1") Integer _page,
            @RequestParam(required = false, defaultValue = "5") Integer _size

    ) {
        Page<?> usersList = userService.getAllUserDetails(_page,_size);
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }


}
