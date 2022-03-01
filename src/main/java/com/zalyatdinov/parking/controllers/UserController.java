package com.zalyatdinov.parking.controllers;

import com.zalyatdinov.parking.domain.dto.UserDto;
import com.zalyatdinov.parking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{user-id}")
    public ResponseEntity<?> findById(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(new UserDto(userService.createUser(userDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{user-id}")
    public ResponseEntity<?> updateUser(@PathVariable("user-id") Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(new UserDto(userService.updateUser(userDto, userId)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{user-id}")
    public void deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
    }

}
