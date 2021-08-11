package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.User;
import com.geekbrains.demoboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
    }

}
