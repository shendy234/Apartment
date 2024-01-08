package com.enigma.apartment.service.impl;

import com.enigma.apartment.entity.AppUser;
import com.enigma.apartment.entity.UserCredential;
import com.enigma.apartment.repository.UserCredentialRepository;
import com.enigma.apartment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepository.findById(id).orElseThrow(()-> new
                UsernameNotFoundException("invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }
}