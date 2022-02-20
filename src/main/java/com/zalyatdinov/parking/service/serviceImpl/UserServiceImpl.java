package com.zalyatdinov.parking.service.serviceImpl;

import com.zalyatdinov.parking.domain.dto.UserDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.domain.entity.Role;
import com.zalyatdinov.parking.domain.entity.User;
import com.zalyatdinov.parking.repositories.UserRepository;
import com.zalyatdinov.parking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).get();
        return new UserDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User(userDto);
        user.setId(0L);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto, Long userId) {
        User user = new User(userDto);
        if (userDto.getPassword() != null) user.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }


    // не уверен, подумать еще
    @Override
    public void setRole(Long userId, Role role) {
        User user = userRepository.findById(userId).get();
        user.setRoles(Collections.singleton(role));
    }

    @Override
    public String refresh(Authentication authentication) {
        // TODO: генерация jwt токена?
        return null;
    }
}
