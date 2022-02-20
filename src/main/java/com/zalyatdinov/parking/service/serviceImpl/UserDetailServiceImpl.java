package com.zalyatdinov.parking.service.serviceImpl;

import com.zalyatdinov.parking.domain.entity.User;
import com.zalyatdinov.parking.repositories.UserRepository;
import com.zalyatdinov.parking.security.domain.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    // TODO: дальше куда в jwt токен?
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username : " + username));
        return UserPrinciple.build(user);
    }
}
