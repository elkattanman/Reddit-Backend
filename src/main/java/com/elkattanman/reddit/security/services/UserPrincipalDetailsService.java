package com.elkattanman.reddit.security.services;


import com.elkattanman.reddit.domain.User;
import com.elkattanman.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        User user = userRepository.findByUsername(s)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", s)));
        Optional<User> byUsername = userRepository.findByUsername(s);
        Optional<User>  byEmail= userRepository.findByEmail(s);

        if(byUsername.isPresent()){
            return new UserPrincipal(byUsername.get());
        }else if (byEmail.isPresent()){
            return new UserPrincipal(byEmail.get());
        }

        throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", s));
    }
}
