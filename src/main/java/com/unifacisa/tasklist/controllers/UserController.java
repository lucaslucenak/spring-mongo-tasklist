//package com.unifacisa.tasklist.controllers;
//
//import com.unifacisa.tasklist.models.UserModel;
//import com.unifacisa.tasklist.services.UserService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/user")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @GetMapping(value = "/{userId}")
//    public ResponseEntity<UserModel> getUserById(@PathVariable String userId) {
//        return ResponseEntity.ok().body(userService.findUserById(userId));
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<UserModel>> getAllUsers(Pageable pageable) {
//        List<UserModel> userReturnDtos = new ArrayList<>();
//        for (UserModel i : userService.findAllUsersPaginated(pageable)) {
//            userReturnDtos.add(i);
//        }
//        Page<UserModel> userReturnDtoPage = new PageImpl<>(userReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), userReturnDtos.size());
//        return ResponseEntity.ok().body(userReturnDtoPage);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserModel userPostDto) {
//        return ResponseEntity.ok().body(userService.saveUser(userPostDto));
//    }
//
//    @PutMapping(value = "/{userId}")
//    public ResponseEntity<UserModel> updateUser(@PathVariable String userId, @RequestBody @Valid UserModel userPostDto) {
//        return ResponseEntity.ok().body(userService.updateUser(userId, userPostDto));
//    }
//
//    @DeleteMapping(value = "/{userId}")
//    public ResponseEntity<UserModel> deleteUserById(@PathVariable String userId) {
//        userService.deleteUserById(userId);
//        return ResponseEntity.noContent().build();
//    }
//}
