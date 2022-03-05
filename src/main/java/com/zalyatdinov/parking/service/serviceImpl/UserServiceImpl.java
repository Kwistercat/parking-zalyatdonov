package com.zalyatdinov.parking.service.serviceImpl;

import com.zalyatdinov.parking.domain.dto.UserDto;
import com.zalyatdinov.parking.domain.entity.Role;
import com.zalyatdinov.parking.domain.entity.User;
import com.zalyatdinov.parking.exception.NotFoundException;
import com.zalyatdinov.parking.repositories.UserRepository;
import com.zalyatdinov.parking.service.UserService;
import lombok.RequiredArgsConstructor;
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
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new UserDto(userOptional.get());
        } else throw new NotFoundException("Fail -> Not found user!");
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else throw new NotFoundException("Fail -> Not found user!");
    }

    @Override
    public User createUser(UserDto userDto) {
        if (existsByUsername(userDto.getUsername())) {
            throw new NotFoundException("Fail -> Username is already taken!");
        }
        if (existsById(userDto.getId())) {
            throw new NotFoundException("Fail -> Id is already in use!");
        }
        User user = new User(userDto);
        user.setId(0L);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto, Long userId) {
        User user = new User(userDto);
        if (!existsById(userId) || !userDto.getId().equals(userId)) {
            throw new NotFoundException("Fail -> Not found user!");
        }
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

    @Override
    public void setRole(Long userId, Role role) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userOptional.get().setRoles(Collections.singleton(role));
        } else throw new NotFoundException("Fail -> Not found user!");
    }
}
