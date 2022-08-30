package com.mehmetkicirti.blogapplication.service;

import com.mehmetkicirti.blogapplication.entity.concrete.Role;
import com.mehmetkicirti.blogapplication.entity.concrete.User;
import com.mehmetkicirti.blogapplication.repository.UserRepository;
import com.mehmetkicirti.blogapplication.utility.constant.ExceptionConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException(ExceptionConstants.USER_NOT_FOUND_EXCEPTION + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
