package com.zalyatdinov.parking.service;

import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.dto.UserDto;
import com.zalyatdinov.parking.domain.entity.Role;
import com.zalyatdinov.parking.domain.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    void deleteUser(Long userId);

    User createUser(UserDto userDto);

    User updateUser(UserDto userDto, Long userId);

    boolean existsByUsername(String username);

    boolean existsById(Long id);

    void setRole(Long userId, Role role);

    String refresh(Authentication authentication);
}
