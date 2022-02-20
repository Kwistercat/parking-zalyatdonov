package com.zalyatdinov.parking.controllers;

import com.zalyatdinov.parking.domain.dto.UserDto;
import com.zalyatdinov.parking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<?> findById(@PathVariable("user-id") Long userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<>(" Fail -> Not found username!",
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    // TODO: подумать над @PreAuthorize(), везде указывать?
    @PostMapping
    @PreAuthorize("hasAuthority(\"ADMIN\")")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsById(userDto.getId())) {
            return new ResponseEntity<String>("Fail -> Id is already in use!",
                    HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().body(new UserDto(userService.createUser(userDto)));
    }

    @PutMapping("/{user-id}")
    public ResponseEntity<?> updateUser(@PathVariable("user-id") Long userId, @RequestBody UserDto userDto) {
        UserDto fromDb = userService.findById(userId);

        if (!userService.existsById(userId) || !fromDb.getId().equals(userId)) {
            return new ResponseEntity<>("Fail -> Not found user!",
                    HttpStatus.NOT_FOUND);
        }

        if (userService.existsByUsername(userDto.getUsername()) && !fromDb.getUsername().equals(userDto.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is used!",
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(new UserDto(userService.updateUser(userDto, userId)));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user-id") Long userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>("Fail -> Not found user!",
                    HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(userId);

        return ResponseEntity.ok().body("User was deleted successfully!");
    }
}
